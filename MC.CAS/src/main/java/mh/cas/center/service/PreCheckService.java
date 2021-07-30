package mh.cas.center.service;

import mh.cas.center.constant.ReturnCode;
import mh.cas.center.exception.LoginCheckFailException;
import mh.cas.center.utils.CheckImage;
import mh.cas.center.utils.CheckImageUtil;
import mh.cas.center.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class PreCheckService {
    private final String IDENTIFY_IMAGE_PREFIX = "session.id:identify.image:";
    private final String LOGIN_ATTEMPT_PREFIX = "session.id:attempt.sum:";
    private final String REGISTER_ATTEMPT_PREFIX = "session.id:register.sum:";

    @Autowired
    JedisPool jedisPool;

    public CheckImage getIdentifyImage(String sessionId){
        CheckImage checkImage = CheckImageUtil.createImage();
        try (Jedis jedis = jedisPool.getResource();){
            //2分钟内未提交，验证码过期
            jedis.setex(IDENTIFY_IMAGE_PREFIX +sessionId,60*2,checkImage.getImageKey());
        }
        return checkImage;
    }

    public void checkLoginAttempt(String sessionId){
        try (Jedis jedis = jedisPool.getResource()) {
            Long attemptTimes = jedis.incr(LOGIN_ATTEMPT_PREFIX + sessionId);
            //登录次数超过10次，冷却20分钟
            if(attemptTimes>10){
                jedis.expire(LOGIN_ATTEMPT_PREFIX + sessionId,60*20);
                throw new LoginCheckFailException(ReturnCode.ATTEMPT_OUT_LIMIT.getCode(),"登录次数超出限制,请20分钟后重试");
            }
        }
    }

    public void checkRegisterAttempt(String sessionId){
        try (Jedis jedis = jedisPool.getResource()) {
            Long attemptTimes = jedis.incr(REGISTER_ATTEMPT_PREFIX + sessionId);
            //登录次数超过10次，冷却20分钟
            if(attemptTimes>5){
                jedis.expire(LOGIN_ATTEMPT_PREFIX + sessionId,60*60*24);
                throw new LoginCheckFailException(ReturnCode.ATTEMPT_OUT_LIMIT.getCode(),"注册次数超过上限,请24小时后重试");
            }
        }
    }

    public void checkIdentifyImage(String sessionId, String unCheckedKey) throws LoginCheckFailException {
        try (Jedis jedis = jedisPool.getResource()) {
            String imageKey = jedis.get(IDENTIFY_IMAGE_PREFIX + sessionId);
            if (StringUtils.isNullOrWhiteSpace(imageKey)||!imageKey.equals(unCheckedKey)){
                throw new LoginCheckFailException(ReturnCode.IDENTIFY_IMAGE_ERROR.getCode(),"验证码错误");
            }
        }
    }
}
