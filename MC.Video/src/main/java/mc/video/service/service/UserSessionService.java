package mc.video.service.service;

import javax.servlet.http.Cookie;

/**
 * @author: ZhuXingda
 * @Date: 2021/9/20 10:55
 */
public interface UserSessionService {
    public boolean checkToken(Cookie token);
    public void registerToken(String cookieValue, int expireTime);
}
