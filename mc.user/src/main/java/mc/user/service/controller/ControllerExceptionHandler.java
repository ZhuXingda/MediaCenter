package mc.user.service.controller;

import mc.user.service.constant.ReturnCode;
import mc.user.service.exception.LoginCheckFailException;
import mc.user.service.exception.UnLoginException;
import mc.user.service.vo.ReturnInfoVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@ControllerAdvice
public class ControllerExceptionHandler {
    @Value("${front.login.uri}")
    private String FRONT_LOGIN_URI;

    @ExceptionHandler(UnLoginException.class)
    public ResponseEntity<ReturnInfoVO<String>> unLoginExceptionHandler(UnLoginException exception){
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(FRONT_LOGIN_URI)
                .queryParam("service", exception.getOriginServiceUrl())
                .build();
        ReturnInfoVO<String> returnInfoVO = new ReturnInfoVO<>(ReturnCode.NO_LOGIN_RECORD.getCode(), "请先登录您的账户");
        returnInfoVO.setData(uriComponents.toUriString());
        return new ResponseEntity<>(returnInfoVO, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(LoginCheckFailException.class)
    public ResponseEntity<ReturnInfoVO> checkCodeErrorExceptionHandler(LoginCheckFailException exception){
        ReturnInfoVO returnInfoVO = new ReturnInfoVO<>(exception.getErrorCode(), exception.getMsg());
        return ResponseEntity.badRequest().body(returnInfoVO);
    }
}
