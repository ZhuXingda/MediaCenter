package mc.video.service.dto;

import lombok.Data;
import mc.video.service.dao.VideoSetBriefInfoDao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author: ZhuXingda
 * @Date: 2021/8/6 01:20
 */
@Data
public class VideoSetBriefInfoDTO {
    private Long id;
    private String title;
    private String coverImgSrc;
    private Date publishDate;
    private Timestamp uploadDate;
    private Integer totalCount;
}
