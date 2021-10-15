package mc.video.service.service;

import mc.video.service.dto.VideoSetBriefInfoDTO;
import mc.video.service.dto.VideoSetDetailInfoDTO;
import mc.video.service.vo.VideoSetBriefInfoVO;

import java.util.List;
import java.util.Map;

public interface VideoSetInfoService {

    public VideoSetBriefInfoVO getRecommendVideoListForIndex(int pageIndex, int size);

    public List<VideoSetBriefInfoDTO> getRecommendVideoListByTag(Long tagId, int start, int size);

    public VideoSetDetailInfoDTO getVideoDetailInfoById(Long tagId);

    public void notifyNewVideoSetUpload(List<VideoSetBriefInfoDTO> infoList);
}
