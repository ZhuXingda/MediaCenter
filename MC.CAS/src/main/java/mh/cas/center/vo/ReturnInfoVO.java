package mh.cas.center.vo;

import lombok.Data;

@Data
public class ReturnInfoVO<T> {
    private int code;
    private String msg;
    private T data;

    public ReturnInfoVO(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ReturnInfoVO(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
