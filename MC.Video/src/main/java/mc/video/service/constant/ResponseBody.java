package mc.video.service.constant;

import lombok.Data;

/**
 * @author: ZhuXingda
 * @Date: 2021/8/8 14:39
 */
@Data
public class ResponseBody <T>{
    private int code;
    private String msg;
    private T data;
}
