package mc.video.service.dao;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author: ZhuXingda
 * @Date: 2021/9/20 14:55
 */
@Data
public class VideoDetailInfoDao {
    private Long id;
    private Long setId;
    private String title;
    private String videoSrc;
    private Timestamp uploadDate;
}
