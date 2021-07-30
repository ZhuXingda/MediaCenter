package mh.cas.center.service;

import mh.cas.center.dto.LoginTokenDTO;
import mh.cas.center.utils.Base64Util;
import mh.cas.center.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class LoginStatusService {
    private final String TOKEN_PREFIX = "login.token:login.ticket:";
    private final String TICKET_PREFIX = "login.ticket:";
    private final int EXPIRE_TIME = 60*60*12;
    private final int EXPIRE_TIME_WEEK = 60*60*24*7;
    @Autowired
    private JedisPool jedisPool;

    public boolean checkLogin(String TGC){
        try (Jedis jedis = jedisPool.getResource()){
            String exist = jedis.get(TOKEN_PREFIX + TGC);
            return !StringUtils.isNullOrWhiteSpace(exist);
        }catch (Exception e){
            return false;
        }
    }

    public LoginTokenDTO registerLogin(Long id, boolean isRemember){
        String userId = String.valueOf(id);
        String token = Base64Util.encode(userId);
        String ticket = Base64Util.encode(userId);
        try (Jedis jedis = jedisPool.getResource()){
            jedis.setex(TOKEN_PREFIX +token,isRemember?EXPIRE_TIME:EXPIRE_TIME_WEEK,ticket);
            jedis.setex(TICKET_PREFIX +ticket,isRemember?EXPIRE_TIME:EXPIRE_TIME_WEEK,"registered");
        }
        return new LoginTokenDTO(token,ticket);
    }
}
