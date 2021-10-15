package mc.user.service.exception;

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
