package mh.cas.center.controller;

import mh.cas.center.constant.ReturnCode;
import mh.cas.center.dto.LoginTokenDTO;
import mh.cas.center.exception.UnLoginException;
import mh.cas.center.pojo.UserInfoPojo;
import mh.cas.center.service.PreCheckService;
import mh.cas.center.service.LoginStatusService;
import mh.cas.center.service.UserInfoService;
import mh.cas.center.utils.CheckImage;
import mh.cas.center.utils.StringUtils;
import mh.cas.center.vo.LoginInfoVO;
import mh.cas.center.vo.RegisterInfoVO;
import mh.cas.center.vo.ReturnInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.Duration;
import java.time.temporal.ChronoUnit;


@Controller
@ResponseBody
@RequestMapping("/identity")
public class LoginController {
    private final String TICKET_PREFIX = "?ticket=";
    @Value("${index.url}")
    private String INDEX_URL;
    @Autowired
    private PreCheckService preCheckService;
    @Autowired
    private LoginStatusService loginStatusService;
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/header")
    public String getComment(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return "{\"user_avatar\":\"static/img/toby2.jpeg\",\"header_link\":\"http://www.baidu.com\",\"header_describe\":\"个人中心\"}";
    }

    @GetMapping("/getCheckImg")
    public void getCheckImg(HttpSession session, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        try (OutputStream out = response.getOutputStream(); ByteArrayOutputStream bos = new ByteArrayOutputStream()){
            CheckImage checkImage = preCheckService.getIdentifyImage(session.getId());
            ImageIO.write(checkImage.getImage(),"jpeg",bos);
            out.write(bos.toByteArray());
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @GetMapping("/checkLoginStatus")
    public void checkLoginStatus(@CookieValue(name = "TGC", required = false)Cookie TGC,
                                 @RequestParam(name = "service", required = false)String originService ,HttpServletResponse response){
        try {
            if(TGC==null||StringUtils.isNullOrWhiteSpace(TGC.getValue())){
                throw new UnLoginException();
            }
            String ticket = "?ticket=dsfsdfsdfsd213141313";
            response.sendRedirect(originService+ticket);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<ReturnInfoVO<String>> login(HttpSession session,
                                                      @RequestBody LoginInfoVO loginInfoVO,
                                                      @RequestParam(name = "service", required = false)String originUrl) throws Exception {
        //TODO check loginInfoVO legal
        //校验登录次数和验证码
        preCheckService.checkLoginAttempt(session.getId());
        preCheckService.checkIdentifyImage(session.getId(),loginInfoVO.getUncheckedKey());
        //校验邮箱地址和密码
        UserInfoPojo userInfo = userInfoService.checkEmailAndPassword(loginInfoVO);
        //缓存登录状态
        LoginTokenDTO loginTokenDTO = loginStatusService.registerLogin(userInfo.getId(), loginInfoVO.isRemember());
        HttpHeaders responseHeaders = new HttpHeaders();
        ResponseCookie cookie = ResponseCookie
                .from("token", loginTokenDTO.getToken())
                .maxAge(Duration.of(24*(loginInfoVO.isRemember()?7:1), ChronoUnit.HOURS))
                .sameSite("Lax")
                .build();
        responseHeaders.add(HttpHeaders.SET_COOKIE,cookie.toString());
        ReturnInfoVO<String> returnInfoVO = new ReturnInfoVO<>(ReturnCode.SUCCESS.getCode(), userInfo.getUserName() + "，欢迎回来");
        String setLocation = (StringUtils.isNullOrWhiteSpace(originUrl)?INDEX_URL:originUrl) + TICKET_PREFIX + loginTokenDTO.getTicket();
        returnInfoVO.setData(setLocation);
        return new ResponseEntity<>(returnInfoVO,responseHeaders,HttpStatus.OK);
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<ReturnInfoVO<String>> register(HttpSession session,
                                                   @RequestBody RegisterInfoVO registerInfoVO){
        preCheckService.checkRegisterAttempt(session.getId());
        preCheckService.checkIdentifyImage(session.getId(),registerInfoVO.getUncheckedKey());
        //TODO check email and password legal
        //检查邮箱是否已注册
        userInfoService.checkEmailRegistered(registerInfoVO.getEmail());
        //创建新的用户并缓存邮箱和ID
        UserInfoPojo userInfo = userInfoService.createUserInfo(registerInfoVO.getEmail(), registerInfoVO.getPassword());
        //缓存登录状态
        LoginTokenDTO loginTokenDTO = loginStatusService.registerLogin(userInfo.getId(), false);
        HttpHeaders responseHeaders = new HttpHeaders();
        ResponseCookie cookie = ResponseCookie
                .from("token", loginTokenDTO.getToken())
                .maxAge(Duration.of(24, ChronoUnit.HOURS))
                .sameSite("Lax")
                .build();
        ReturnInfoVO<String> returnInfoVO = new ReturnInfoVO<>(ReturnCode.SUCCESS.getCode(), userInfo.getUserName() + "，欢迎成为本站的一员");
        String setLocation = INDEX_URL + TICKET_PREFIX + loginTokenDTO.getTicket();
        returnInfoVO.setData(setLocation);
        responseHeaders.add(HttpHeaders.SET_COOKIE,cookie.toString());
        return new ResponseEntity<>(returnInfoVO,responseHeaders,HttpStatus.OK);
    }
}
