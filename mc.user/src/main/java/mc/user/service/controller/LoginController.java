package mc.user.service.controller;

import mc.cas.center.dto.LoginTokenDTO;
import mc.cas.center.dto.UserInfoDTO;
import mc.cas.center.rpc.LoginStatusProvider;
import mc.cas.center.rpc.UserInfoProvider;
import mc.cas.center.utils.Base64Util;
import mc.user.service.constant.ReturnCode;
import mc.user.service.exception.LoginCheckFailException;
import mc.user.service.exception.UnLoginException;
import mc.user.service.service.PreCheckService;
import mc.user.service.utils.CheckImage;
import mc.user.service.utils.StringUtils;
import mc.user.service.vo.LoginInfoVO;
import mc.user.service.vo.RegisterInfoVO;
import mc.user.service.vo.ReturnInfoVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

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
@RequestMapping("/identity")
public class LoginController {
    private final String TICKET_PREFIX = "?ticket=";
    @Value("${front.url}")
    private String FRONT_URL;
    @Autowired
    private PreCheckService preCheckService;
    @DubboReference
    private LoginStatusProvider loginStatusProvider;
    @DubboReference
    private UserInfoProvider userInfoProvider;

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
    public ResponseEntity<String> checkLoginStatus(@CookieValue(name = "token", required = false)Cookie token,
                                                   @RequestParam(name = "service")String originServiceUrl){
        System.out.println(token);
        if(token!=null && !StringUtils.isNullOrWhiteSpace(token.getValue())){
            if(loginStatusProvider.checkTokenValidation(token.getValue())){
                String ticket = loginStatusProvider.registerTicket(Base64Util.decode(token.getValue()));
                HttpHeaders responseHeaders = new HttpHeaders();
                UriComponents uriComponents = UriComponentsBuilder
                        .fromHttpUrl(originServiceUrl)
                        .queryParam("ticket", ticket)
                        .build();
                responseHeaders.setLocation(uriComponents.toUri());
                return new ResponseEntity<>(responseHeaders, HttpStatus.FOUND);
            }
        }
        throw new UnLoginException(originServiceUrl);
    }

    @PostMapping("/login")
    public ResponseEntity<ReturnInfoVO<String>> login(HttpSession session,
                                                      @RequestBody LoginInfoVO loginInfoVO,
                                                      @RequestParam(name = "service", required = false)String originUrl) throws Exception {
        System.out.println(originUrl);
        //TODO check loginInfoVO legal
        //校验登录次数和验证码
        preCheckService.checkLoginAttempt(session.getId());
        preCheckService.checkIdentifyImage(session.getId(),loginInfoVO.getUncheckedKey());
        //校验邮箱地址和密码
        UserInfoDTO userInfoDTO = userInfoProvider.checkEmailAndPassword(loginInfoVO.getEmail(), loginInfoVO.getPassword());
        if(userInfoDTO == null){
            throw new LoginCheckFailException(mc.cas.center.constant.ReturnCode.EMAIL_PASSWORD_ERROR.getCode(),"邮箱不存在或密码验证失败");
        }
        //缓存登录状态
        LoginTokenDTO loginTokenDTO = loginStatusProvider.registerLogin(userInfoDTO, loginInfoVO.isRemember());
        HttpHeaders responseHeaders = new HttpHeaders();
        ResponseCookie cookie = ResponseCookie
                .from("token", loginTokenDTO.getToken())
                .maxAge(Duration.of(24*(loginInfoVO.isRemember()?7:1), ChronoUnit.HOURS))
                .sameSite("Lax")
                .path("/user")
                .httpOnly(true)
                .build();
        responseHeaders.add(HttpHeaders.SET_COOKIE,cookie.toString());
        ReturnInfoVO<String> returnInfoVO = new ReturnInfoVO<>(ReturnCode.SUCCESS.getCode(), userInfoDTO.getUserName() + "，欢迎回来");
        String setLocation = (StringUtils.isNullOrWhiteSpace(originUrl)? FRONT_URL :originUrl) + TICKET_PREFIX + loginTokenDTO.getTicket();
        returnInfoVO.setData(setLocation);
        return new ResponseEntity<>(returnInfoVO,responseHeaders,HttpStatus.OK);
    }

    @PutMapping("/logout")
    public ResponseEntity<ReturnInfoVO<String>> logout(@CookieValue(name = "token", required = false) Cookie token) throws Exception{
        if(token!=null){
            loginStatusProvider.deleteLogin(token.getValue());
            ReturnInfoVO<String> returnInfoVO = new ReturnInfoVO<>(ReturnCode.SUCCESS.getCode(), "退出成功");
            return new ResponseEntity<>(returnInfoVO,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ReturnInfoVO<String>> register(HttpSession session,
                                                   @RequestBody RegisterInfoVO registerInfoVO){
        preCheckService.checkRegisterAttempt(session.getId());
        preCheckService.checkIdentifyImage(session.getId(),registerInfoVO.getUncheckedKey());
        //TODO check email and password legal
        //检查邮箱是否已注册
        if (userInfoProvider.checkEmailRegistered(registerInfoVO.getEmail())){
            throw new LoginCheckFailException(mc.cas.center.constant.ReturnCode.EMAIL_ALREADY_REGISTERED.getCode(),"该邮箱已注册");
        }
        //创建新的用户并缓存邮箱和ID
        UserInfoDTO userInfoDTO = userInfoProvider.createUserInfo(registerInfoVO.getEmail(), registerInfoVO.getPassword());
        //缓存登录状态
        LoginTokenDTO loginTokenDTO = loginStatusProvider.registerLogin(userInfoDTO, false);
        HttpHeaders responseHeaders = new HttpHeaders();
        ResponseCookie cookie = ResponseCookie
                .from("token", loginTokenDTO.getToken())
                .maxAge(Duration.of(24, ChronoUnit.HOURS))
                .sameSite("Lax")
                .build();
        ReturnInfoVO<String> returnInfoVO = new ReturnInfoVO<>(ReturnCode.SUCCESS.getCode(), userInfoDTO.getUserName() + "，欢迎成为本站的一员");
        String setLocation = FRONT_URL + TICKET_PREFIX + loginTokenDTO.getTicket();
        returnInfoVO.setData(setLocation);
        responseHeaders.add(HttpHeaders.SET_COOKIE,cookie.toString());
        return new ResponseEntity<>(returnInfoVO,responseHeaders,HttpStatus.OK);
    }
}
