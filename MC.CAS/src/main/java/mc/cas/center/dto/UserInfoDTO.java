package mc.cas.center.dto;

import lombok.Data;
import mc.cas.center.pojo.UserInfoPojo;

import java.io.Serializable;

/**
 * @author: ZhuXingda
 * @Date: 2021/8/4 18:38
 */
@Data
public class UserInfoDTO implements Serializable {
    private String id;
    private String email;
    private String userName;
    private String avatarSrc;

    public UserInfoDTO(String id, String email, String userName, String avatarSrc) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.avatarSrc = avatarSrc;
    }

    public UserInfoDTO(UserInfoPojo userInfoPojo) {
        this.id = String.valueOf(userInfoPojo.getId());
        this.email = userInfoPojo.getEmail();
        this.userName = userInfoPojo.getUserName();
        this.avatarSrc = userInfoPojo.getAvatarSrc();
    }

    public UserInfoDTO() {
    }
}
