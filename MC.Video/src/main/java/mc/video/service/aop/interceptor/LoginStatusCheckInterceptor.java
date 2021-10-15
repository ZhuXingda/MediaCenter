package mc.video.service.aop.interceptor;

import com.google.common.base.Strings;
import mc.cas.center.dto.TicketValidationDTO;
import mc.cas.center.rpc.LoginStatusProvider;
import mc.video.service.service.impl.UserSessionServiceImpl;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * @author: ZhuXingda
 * @Date: 2021/8/3 15:25
 */
@Component
public class LoginStatusCheckInterceptor implements HandlerInterceptor {
    private static final String SESSION_KEY = "token";
    private static final String TICKET_KEY = "ticket";
    @Value("${cas.check.path}")
    private String CAS_CHECK_PATH;
    @Value("${back.service.host}")
    private String BACK_SERVICE_HOST;
    @Value("${back.service.port}")
    private String BACK_SERVICE_PORT;
    @Value("${front.url}")
    private String FRONT_SERVICE_URL;

    @Autowired
    UserSessionServiceImpl userSessionServiceImpl;
    @DubboReference
    LoginStatusProvider loginStatusProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if(request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpStatus.OK.value());
            return false;
        }
        System.out.println("check user login status...");
        MultiValueMap<String, String> queryMap = new LinkedMultiValueMap<>();
        try {
            //check token if present
            if(request.getCookies()!=null){
                Optional<Cookie> tokenOptional = Arrays.stream(request.getCookies())
                        .filter(cookie -> cookie.getName().equals(SESSION_KEY))
                        .findAny();
                if(tokenOptional.isPresent()&& userSessionServiceImpl.checkToken(tokenOptional.get())) {
                    return true;
                }
            }
            //check ticket if present
            String queryString = request.getQueryString();
            if(!Strings.isNullOrEmpty(queryString)){
                Arrays.stream(queryString.split("&"))
                        .map(p->p.split("="))
                        .forEach(pair -> queryMap.put(pair[0], Collections.singletonList(pair[1])));
            }
            if(queryMap.containsKey(TICKET_KEY)){
                String ticket = queryMap.get(TICKET_KEY).get(0);
                System.out.println(ticket);
                TicketValidationDTO ticketValidationDTO = loginStatusProvider.checkTicketValidation(ticket);
                if(ticketValidationDTO.getValidation()){
                    int expireTime = 24*60*60;
                    userSessionServiceImpl.registerToken(ticket,expireTime);
                    ResponseCookie cookie = ResponseCookie
                            .from(SESSION_KEY, ticket)
                            .maxAge(Duration.of(expireTime, ChronoUnit.SECONDS))
                            .sameSite("Lax")
                            .path("/video")
                            .build();
                    response.addHeader(HttpHeaders.SET_COOKIE,cookie.toString());
                    response.sendRedirect(FRONT_SERVICE_URL+"/index.html");
                    return true;
                }else {
                    //TODO 如果这里ticket校验失败不做拦截，后面就会跳转获取ticket的接口，获取成功了又回来，然后又跳转，陷入死循环
                    response.sendRedirect(FRONT_SERVICE_URL+"/login.html");
                    return true;
                }
                /*queryMap.remove(TICKET_KEY);*/
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        String originUri = UriComponentsBuilder
                .fromHttpUrl(request.getRequestURL().toString())
                .build().toUriString();
        System.out.println(originUri);
        UriComponents uri = UriComponentsBuilder
                .newInstance().scheme("http")
                .host(BACK_SERVICE_HOST)
                .port(BACK_SERVICE_PORT)
                .path(CAS_CHECK_PATH)
                .queryParam("service", originUri)
                .build();
        response.setHeader(HttpHeaders.LOCATION,uri.toUriString());
        response.setStatus(HttpStatus.FOUND.value());
        return false;
    }
}
