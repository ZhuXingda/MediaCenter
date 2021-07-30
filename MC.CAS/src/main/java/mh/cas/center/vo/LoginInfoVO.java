package mh.cas.center.vo;

import lombok.Data;

@Data
public class LoginInfoVO {
    /**
     * 用户email
     */
    private String email;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 验证码
     */
    private String uncheckedKey;
    /**
     * 免登陆
     */
    private boolean remember;
}
