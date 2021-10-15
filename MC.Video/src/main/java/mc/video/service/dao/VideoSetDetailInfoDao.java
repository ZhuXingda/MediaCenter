package mc.video.service.dao;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author: ZhuXingda
 * @Date: 2021/8/6 10:58
 */
@Data
public class VideoSetDetailInfoDao {
    private Long id;
    private String title;
    private String describe;
    private int totalCount;
    private String coverImgSrc;
    private Date publishDate;
    private Timestamp uploadDate;
}
