package mh.cas.center.controller;

import mh.cas.center.exception.LoginCheckFailException;
import mh.cas.center.exception.UnLoginException;
import mh.cas.center.vo.ReturnInfoVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UnLoginException.class)
    public void unLoginExceptionHandler(HttpServletResponse response){
        try {
            //TODO 重定向到登录页面
            response.sendRedirect("http://localhost:8080/index.html");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @ExceptionHandler(LoginCheckFailException.class)
    public ResponseEntity<ReturnInfoVO> checkCodeErrorExceptionHandler(LoginCheckFailException exception){
        ReturnInfoVO returnInfoVO = new ReturnInfoVO<>(exception.getErrorCode(), exception.getMsg());
        return ResponseEntity.badRequest().body(returnInfoVO);
    }
}
