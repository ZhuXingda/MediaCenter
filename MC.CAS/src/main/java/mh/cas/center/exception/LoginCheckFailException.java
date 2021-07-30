package mh.cas.center.exception;

import lombok.Data;

@Data
public class LoginCheckFailException extends RuntimeException {
    int errorCode;
    String msg;
    public LoginCheckFailException(int errorCode, String msg) {
        super();
        this.msg = msg;
        this.errorCode = errorCode;
    }
}
