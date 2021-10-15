package mc.user.service.controller;

import mc.cas.center.dto.UserInfoDTO;
import mc.cas.center.rpc.LoginStatusProvider;
import mc.cas.center.rpc.UserInfoProvider;
import mc.cas.center.utils.Base64Util;
import mc.user.service.utils.StringUtils;
import mc.user.service.vo.HeaderInfoVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: ZhuXingda
 * @Date: 2021/8/8 23:02
 */
@Controller
public class HeaderController {
    @Value("front.url")
    private String FRONT_URL;

    private HeaderInfoVO unLoginInfo;
    @DubboReference
    private UserInfoProvider userInfoProvider;
    @DubboReference
    private LoginStatusProvider loginStatusProvider;

    @PostConstruct
    private void init(){
        unLoginInfo = new HeaderInfoVO();
        unLoginInfo.setHasLogin(false);
    }

    @GetMapping("/getHeaderInfo")
    public ResponseEntity<HeaderInfoVO> getHeaderInfo(@CookieValue(name = "token", required = false) Cookie token){
        if(token!=null && !StringUtils.isNullOrWhiteSpace(token.getValue())
                && loginStatusProvider.checkTokenValidation(token.getValue())){
            String id = Base64Util.decode(token.getValue());
            UserInfoDTO userInfoDTO = userInfoProvider.getUserInfo(id);
            return new ResponseEntity<>(new HeaderInfoVO(userInfoDTO), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(unLoginInfo, HttpStatus.OK);
        }
    }
}
