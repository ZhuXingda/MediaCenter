package mc.video.service.dto;

import lombok.Data;

/**
 * @author: ZhuXingda
 * @Date: 2021/8/6 10:12
 */
@Data
public class VideoTagDTO {
    private Long tagId;
    private String tagName;
    private int tagTotal;
}
