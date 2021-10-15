package mc.cas.center.rpc.imp;

import mc.cas.center.dto.UserInfoDTO;
import mc.cas.center.pojo.UserInfoPojo;
import mc.cas.center.rpc.UserInfoProvider;
import mc.cas.center.service.UserInfoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: ZhuXingda
 * @Date: 2021/8/4 17:49
 */
@DubboService
public class UserInfoProviderImpl implements UserInfoProvider {
    @Autowired
    UserInfoService userInfoService;

    @Override
    public UserInfoDTO checkEmailAndPassword(String email, String passWord) {
        String id = userInfoService.checkEmailRegistered(email);
        if (id != null) {
            UserInfoPojo userInfoPojo = userInfoService.checkIdAndPassword(id, passWord);
            if( userInfoPojo != null){
                return new UserInfoDTO(userInfoPojo);
            }
        }
        return null;
    }

    @Override
    public boolean checkEmailRegistered(String email) {
        return userInfoService.checkEmailRegistered(email)!=null;
    }

    @Override
    public UserInfoDTO createUserInfo(String email, String password) {
        UserInfoPojo userInfo = userInfoService.createUserInfo(email, password);
        return new UserInfoDTO(userInfo);
    }

    @Override
    public UserInfoDTO getUserInfo(String id) {
        return new UserInfoDTO(userInfoService.getUserInfoById(id));
    }
}
