package mc.cas.center.rpc;

import mc.cas.center.dto.LoginTokenDTO;
import mc.cas.center.dto.TicketValidationDTO;
import mc.cas.center.dto.UserInfoDTO;

public interface LoginStatusProvider {
    /**
     * 校验用户提供的ticket是否有效
     */
    public TicketValidationDTO checkTicketValidation(String ticket);

    public String registerTicket(String id);
    /**
     * 校验Cookie保存的token是否有效
     */
    public boolean checkTokenValidation(String token);

    /**
     * 缓存登录记录
     */
    public LoginTokenDTO registerLogin(UserInfoDTO userInfoDTO, boolean isRemember);

    public void deleteLogin(String token);
}
