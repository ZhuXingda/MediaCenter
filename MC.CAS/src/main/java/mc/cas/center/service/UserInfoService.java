package mc.cas.center.service;

import mc.cas.center.mapper.UserinfoMapper;
import mc.cas.center.pojo.UserInfoPojo;
import mc.cas.center.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import javax.annotation.PostConstruct;
import java.util.List;


@Service
public class UserInfoService {
    private static final int BUFFER = 2000;
    private static final String EMAIL_ID_PREFIX = "user.email:user.id:";
    @Value("default.user.avatar.src")
    private String DEFAULT_AVATAR_SRC;

    @Autowired
    TransactionManager transactionManager;
    @Autowired
    private UserinfoMapper userInfoMapper;
    @Autowired
    private JedisPool jedisPool;

    @PostConstruct
    public void initUserInfoCache(){
        try (Jedis jedis = jedisPool.getResource()){
            int total = userInfoMapper.getUserInfoCount();
            Pipeline pipelined = jedis.pipelined();
            for (int start = 0; start < total; start+=BUFFER) {
                List<UserInfoPojo> subList = userInfoMapper.getUserInfoList(start, Math.min(total - start, BUFFER));
                subList.forEach(userInfoPojo -> pipelined.set(EMAIL_ID_PREFIX + userInfoPojo.getEmail(),String.valueOf(userInfoPojo.getId())));
                pipelined.sync();
            }
            pipelined.close();
        }
    }

    public UserInfoPojo checkIdAndPassword(String id, String passWord){
        UserInfoPojo userInfo = userInfoMapper.getUserInfoById(Long.parseLong(id));
        if(userInfo.getPassword().equals(passWord)){
            return userInfo;
        }
        return null;
    }

    public String checkEmailRegistered(String email){
        try (Jedis jedis = jedisPool.getResource()){
            if(jedis.exists(EMAIL_ID_PREFIX + email)){
                return jedis.get(EMAIL_ID_PREFIX + email);
            }
        }
        return null;
    }

    public UserInfoPojo getUserInfoById(String id){
        return userInfoMapper.getUserInfoById(Long.parseLong(id));
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
