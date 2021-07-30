package mh.cas.center.constant;

public enum ReturnCode {
    SUCCESS(0),
    EMAIL_PASSWORD_ERROR(10001),
    IDENTIFY_IMAGE_ERROR(10002),
    ATTEMPT_OUT_LIMIT(10003),
    EMAIL_ALREADY_REGISTERED(10004);

    private int code;

    ReturnCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
