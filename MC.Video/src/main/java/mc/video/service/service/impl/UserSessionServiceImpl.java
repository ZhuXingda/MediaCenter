package mc.video.service.service.impl;


import mc.video.service.service.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;

@Service
public class UserSessionServiceImpl implements UserSessionService {
    private final String LOGIN_STATUS_PREFIX = "mc.video:session.id:expire.time:";

    @Autowired
    JedisPool jedisPool;

    @Override
    public boolean checkToken(Cookie token){
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.exists(LOGIN_STATUS_PREFIX + token.getValue());
        }
    }

    @Override
    public void registerToken(String cookieValue, int expireTime){
        try (Jedis jedis = jedisPool.getResource()){
            jedis.setex(LOGIN_STATUS_PREFIX + cookieValue, expireTime, "registered");
        }
    }
}
