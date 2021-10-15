package mc.cas.center.service;

import mc.cas.center.dto.LoginTokenDTO;
import mc.cas.center.dto.TicketValidationDTO;
import mc.cas.center.dto.UserInfoDTO;
import mc.cas.center.utils.Base64Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class LoginStatusService {
    private static final String TOKEN_PREFIX = "login.token:expire.time:";
    private static final String TICKET_PREFIX = "login.ticket:user.id:";
    private final int EXPIRE_TIME = 60*60*12;
    private final int EXPIRE_TIME_WEEK = 60*60*24*7;
    @Autowired
    private JedisPool jedisPool;

    public LoginTokenDTO registerLogin(UserInfoDTO userInfoDTO, boolean isRemember){
        int validateDuration = isRemember ? EXPIRE_TIME : EXPIRE_TIME_WEEK;
        long expireTime = (System.currentTimeMillis() / 1000 + validateDuration);
        String ticket = registerTicket(userInfoDTO.getId());
        try (Jedis jedis = jedisPool.getResource()){
            String token = Base64Util.encode(userInfoDTO.getId());
            jedis.setex(TOKEN_PREFIX + token, validateDuration, String.valueOf(expireTime));
            return new LoginTokenDTO(token, ticket, expireTime);
        }
    }

    public void deleteLogin(String token){
        try (Jedis jedis = jedisPool.getResource()){
            jedis.del(TOKEN_PREFIX + token);
        }
    }

    public boolean checkTokenValidation(String token){
        try (Jedis jedis = jedisPool.getResource()){
            return jedis.exists(TOKEN_PREFIX + token);
        }
    }

    public String registerTicket(String id){
        String timePrefix = String.valueOf(System.currentTimeMillis());
        //加10位的时间戳
        String key = id + timePrefix.substring(timePrefix.length()-11, timePrefix.length()-1);
        String ticket = Base64Util.encode(key);
        try (Jedis jedis = jedisPool.getResource()){
            jedis.setex(TICKET_PREFIX + ticket, 100, id);
        }
        return ticket;
    }

    public TicketValidationDTO checkTicketValidation(String ticket){
        TicketValidationDTO ticketValidationDTO = new TicketValidationDTO();
        ticketValidationDTO.setTicket(ticket);
        ticketValidationDTO.setValidation(false);
        try (Jedis jedis = jedisPool.getResource()){
            if(jedis.exists(TICKET_PREFIX + ticket)){
                long id = Long.parseLong(jedis.get(TICKET_PREFIX + ticket));
                jedis.del(TICKET_PREFIX + ticket);
                ticketValidationDTO.setId(id);
                ticketValidationDTO.setValidation(true);
            }
        }
        return ticketValidationDTO;
    }
}
