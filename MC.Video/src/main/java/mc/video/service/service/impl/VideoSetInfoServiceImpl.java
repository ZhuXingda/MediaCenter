package mc.video.service.service.impl;

import mc.video.service.dao.VideoSetBriefInfoDao;
import mc.video.service.dto.VideoSetBriefInfoDTO;
import mc.video.service.dto.VideoSetDetailInfoDTO;
import mc.video.service.mapper.VideoSetInfoMapper;
import mc.video.service.mapper.VideoTagRelationMapper;
import mc.video.service.service.VideoSetInfoService;
import mc.video.service.vo.VideoSetBriefInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: ZhuXingda
 * @Date: 2021/8/6 01:19
 */
@Service
public class VideoSetInfoServiceImpl implements VideoSetInfoService {
    @Value("video.src.prefix")
    private String VIDEO_SRC_PREFIX;

    private static int TOTAL_VIDEO_SET_COUNT;

    @Autowired
    VideoTagRelationMapper videoTagRelationMapper;

    @Autowired
    VideoSetInfoMapper videoSetInfoMapper;

    @PostConstruct
    private void initTotalVideoSetCount(){
        TOTAL_VIDEO_SET_COUNT = videoSetInfoMapper.getVideoSetInfoCount();
    }

    @Override
    public VideoSetBriefInfoVO getRecommendVideoListForIndex(int pageIndex, int size) {
        pageIndex -= 1;
        size = Math.min(size, TOTAL_VIDEO_SET_COUNT - pageIndex*size);
        List<VideoSetBriefInfoDTO> list = videoSetInfoMapper.getVideoSetBriefInfoList(pageIndex, size)
                                                            .stream()
                                                            .map(this::convertVideoSetBriefInfoDAO2DTO)
                                                            .collect(Collectors.toList());
        return new VideoSetBriefInfoVO(TOTAL_VIDEO_SET_COUNT, list);
    }

    public VideoSetBriefInfoDTO convertVideoSetBriefInfoDAO2DTO(VideoSetBriefInfoDao dao){
        VideoSetBriefInfoDTO dto = new VideoSetBriefInfoDTO();
        dto.setId(dao.getId());
        dto.setCoverImgSrc(dao.getCoverImgSrc());
        dto.setTitle(dao.getTitle());
        dto.setPublishDate(dao.getPublishDate());
        dto.setUploadDate(dao.getUploadDate());
        dto.setTotalCount(dao.getTotalCount());
        return dto;
    }

    @Override
    public List<VideoSetBriefInfoDTO> getRecommendVideoListByTag(Long tagId, int start, int size) {
        return null;
    }

    @Override
    public VideoSetDetailInfoDTO getVideoDetailInfoById(Long tagId) {
        return null;
    }

    @Override
    public void notifyNewVideoSetUpload(List<VideoSetBriefInfoDTO> infoList) {

    }
}
