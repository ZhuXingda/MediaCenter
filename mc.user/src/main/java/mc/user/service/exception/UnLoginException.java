package mc.user.service.exception;

import lombok.Data;

@Data
public class UnLoginException extends RuntimeException {
    private String originServiceUrl;

    public UnLoginException(String originServiceUrl) {
        this.originServiceUrl = originServiceUrl;
    }
}
