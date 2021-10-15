package mc.cas.center.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginTokenDTO implements Serializable {
    private String token;
    private String ticket;
    private long expireTime;

    public LoginTokenDTO(String token, String ticket, long expireTime) {
        this.token = token;
        this.ticket = ticket;
        this.expireTime = expireTime;
    }
}
