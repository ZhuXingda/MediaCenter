package mc.cas.center.rpc;

import mc.cas.center.dto.UserInfoDTO;

public interface UserInfoProvider {
    public UserInfoDTO checkEmailAndPassword(String email, String passWord);

    /**
     * @return true-已注册    false-未注册
     */
    public boolean checkEmailRegistered(String email);

    public UserInfoDTO createUserInfo(String email, String password);

    public UserInfoDTO getUserInfo(String id);
}
