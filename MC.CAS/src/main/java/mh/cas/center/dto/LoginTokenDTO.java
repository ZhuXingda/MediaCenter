package mh.cas.center.dto;

import lombok.Data;

@Data
public class LoginTokenDTO {
    private String token;
    private String ticket;

    public LoginTokenDTO(String token, String ticket) {
        this.token = token;
        this.ticket = ticket;
    }
}
