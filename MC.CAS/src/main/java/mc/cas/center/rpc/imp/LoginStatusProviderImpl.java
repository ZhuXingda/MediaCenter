package mc.cas.center.rpc.imp;

import mc.cas.center.dto.LoginTokenDTO;
import mc.cas.center.dto.TicketValidationDTO;
import mc.cas.center.dto.UserInfoDTO;
import mc.cas.center.rpc.LoginStatusProvider;
import mc.cas.center.service.LoginStatusService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;


@DubboService
public class LoginStatusProviderImpl implements LoginStatusProvider {
    @Autowired
    LoginStatusService loginStatusService;

    //TODO provider做参数校验

    @Override
    public TicketValidationDTO checkTicketValidation(String ticket) {
        return loginStatusService.checkTicketValidation(ticket);
    }

    @Override
    public String registerTicket(String id) {
        return loginStatusService.registerTicket(id);
    }

    @Override
    public boolean checkTokenValidation(String token) {
        return loginStatusService.checkTokenValidation(token);
    }

    @Override
    public LoginTokenDTO registerLogin(UserInfoDTO userInfoDTO, boolean isRemember) {
        return loginStatusService.registerLogin(userInfoDTO, isRemember);
    }

    @Override
    public void deleteLogin(String token) {
        loginStatusService.deleteLogin(token);
    }
}
