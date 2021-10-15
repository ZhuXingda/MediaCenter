package mc.user.service.vo;

import lombok.Data;
import mc.cas.center.dto.UserInfoDTO;

/**
 * @author: ZhuXingda
 * @Date: 2021/8/10 17:19
 */
@Data
public class HeaderInfoVO {
    private boolean hasLogin;
    private String id;
    private String email;
    private String userName;
    private String avatarSrc;
    private int messageCount;

    public HeaderInfoVO(UserInfoDTO userInfoDTO) {
        this.hasLogin = true;
        this.id = userInfoDTO.getId();
        this.email = userInfoDTO.getEmail();
        this.userName = userInfoDTO.getUserName();
        this.avatarSrc = userInfoDTO.getAvatarSrc();
    }

    public HeaderInfoVO() {
    }
}
