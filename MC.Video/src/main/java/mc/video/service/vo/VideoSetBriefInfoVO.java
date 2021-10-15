package mc.video.service.vo;

import lombok.Data;
import mc.video.service.dao.VideoSetBriefInfoDao;
import mc.video.service.dto.VideoSetBriefInfoDTO;

import java.util.List;

/**
 * @author: ZhuXingda
 * @Date: 2021/8/20 21:36
 */
@Data
public class VideoSetBriefInfoVO {
    private int totalCount;
    private List<VideoSetBriefInfoDTO> list;

    public VideoSetBriefInfoVO(int totalCount, List<VideoSetBriefInfoDTO> list) {
        this.totalCount = totalCount;
        this.list = list;
    }
}
