package mc.video.service.aop;

import mc.video.service.service.impl.UserSessionServiceImpl;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoginStatusCheckAspect {

    @Autowired
    UserSessionServiceImpl userSessionServiceImpl;

    /*@Pointcut(value = "execution(public * mc.video.service.controller.*Controller.*(..))")
    public void controllerPointCut() {
    }

    @Before(value = "controllerPointCut()")
    @ResponseBody
    public void LoginStatusCheck() throws Throwable {
        HttpServletRequest httpServletRequest = (HttpServletRequest) RequestContextHolder
                .currentRequestAttributes()
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);
        if (httpServletRequest != null) {
            userInfoService.checkUserLoginStatus(httpServletRequest);
        }
    }*/
}
