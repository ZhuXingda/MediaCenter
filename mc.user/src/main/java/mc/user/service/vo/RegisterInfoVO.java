package mc.user.service.vo;

import lombok.Data;

@Data
public class RegisterInfoVO {
    /**
     * 新用户email
     */
    private String email;
    /**
     * 新用户密码
     */
    private String password;
    /**
     * 验证码
     */
    private String uncheckedKey;
}
