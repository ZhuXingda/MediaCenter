package mh.cas.center.pojo;

import lombok.Data;

@Data
public class UserInfoPojo {
    private long id;
    private String email;
    private String userName;
    private String password;
    private String avatarSrc;
}
