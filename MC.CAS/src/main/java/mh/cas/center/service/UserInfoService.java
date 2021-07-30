package mh.cas.center.service;

import mh.cas.center.constant.ReturnCode;
import mh.cas.center.exception.LoginCheckFailException;
import mh.cas.center.mapper.UserInfoMapper;
import mh.cas.center.pojo.UserInfoPojo;
import mh.cas.center.utils.StringUtils;
import mh.cas.center.vo.LoginInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class UserInfoService {
    private final int BUFFER = 2000;
    private final String EMAIL_ID_PREFIX = "user.email:user.id:";
    @Value("default.user.avatar.src")
    private String DEFAULT_AVATAR_SRC;
    @Autowired
    TransactionManager transactionManager;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private JedisPool jedisPool;

    @PostConstruct
    public void initUserInfoCache(){
        try (Jedis jedis = jedisPool.getResource()){
            int total = userInfoMapper.getUserCount();
            Pipeline pipelined = jedis.pipelined();
            for (int start = 0; start < total; start+=BUFFER) {
                List<UserInfoPojo> subList = userInfoMapper.getUserInfoList(start, Math.min(total - start, BUFFER));
                subList.forEach(userInfoPojo -> pipelined.set(EMAIL_ID_PREFIX + userInfoPojo.getEmail(),String.valueOf(userInfoPojo.getId())));
                pipelined.sync();
            }
            pipelined.close();
        }
    }

    public UserInfoPojo checkEmailAndPassword(LoginInfoVO loginInfoVO){
        try (Jedis jedis = jedisPool.getResource()){
            String id = jedis.get(EMAIL_ID_PREFIX + loginInfoVO.getEmail());
            if(!StringUtils.isNullOrWhiteSpace(id)){
                UserInfoPojo userInfo = userInfoMapper.getUserInfoById(Long.parseLong(id));
                if(userInfo.getPassword().equals(loginInfoVO.getPassword())){
                    return userInfo;
                }
            }
        }
        throw new LoginCheckFailException(ReturnCode.EMAIL_PASSWORD_ERROR.getCode(),"邮箱不存在或密码验证失败");
    }

    public void checkEmailRegistered(String email){
        try (Jedis jedis = jedisPool.getResource()){
            String id = jedis.get(EMAIL_ID_PREFIX + email);
            if(!StringUtils.isNullOrWhiteSpace(id)){
                throw new LoginCheckFailException(ReturnCode.EMAIL_ALREADY_REGISTERED.getCode(),"该邮箱已注册");
            }
        }
    }

    @Transactional
    public UserInfoPojo createUserInfo(String email, String password){
        UserInfoPojo userInfoPojo = new UserInfoPojo();
        userInfoPojo.setUserName("DEFAULT_"+email);
        userInfoPojo.setAvatarSrc(DEFAULT_AVATAR_SRC);
        userInfoPojo.setEmail(email);
        userInfoPojo.setPassword(password);
        userInfoMapper.createUserInfo(userInfoPojo);
        try (Jedis jedis = jedisPool.getResource()){
            jedis.set(EMAIL_ID_PREFIX + userInfoPojo.getEmail(),String.valueOf(userInfoPojo.getId()));
        }
        return userInfoPojo;
    }
}
