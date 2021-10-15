package mc.video.service.dto;

import lombok.Data;
import mc.video.service.dao.VideoDetailInfoDao;
import mc.video.service.dao.VideoSetDetailInfoDao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author: ZhuXingda
 * @Date: 2021/8/7 23:28
 */
@Data
public class VideoSetDetailInfoDTO {
    private Long id;
    private String title;
    private String describe;
    private String coverImgSrc;
    private Date publishDate;
    private Timestamp uploadDate;
    private List<VideoDetailInfoDao> videoDetailInfo;
}
