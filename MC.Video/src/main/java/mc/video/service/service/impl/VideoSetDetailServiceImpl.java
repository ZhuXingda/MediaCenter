package mc.video.service.service.impl;

import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
import mc.video.service.dao.VideoDetailInfoDao;
import mc.video.service.dao.VideoSetDetailInfoDao;
import mc.video.service.dto.VideoSetDetailInfoDTO;
import mc.video.service.exception.TargetNotFoundException;
import mc.video.service.mapper.VideoDetailInfoMapper;
import mc.video.service.mapper.VideoSetInfoMapper;
import mc.video.service.service.VideoSetDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author: ZhuXingda
 * @Date: 2021/9/20 14:17
 */
@Service
public class VideoSetDetailServiceImpl implements VideoSetDetailService {
    @Autowired
    VideoSetInfoMapper videoSetInfoMapper;
    @Autowired
    VideoDetailInfoMapper videoDetailInfoMapper;

    @Override
    public VideoSetDetailInfoDTO  getVideoSetDetailInfoById(Long videoSetId) {
        VideoSetDetailInfoDao videoSetInfo = Optional.of(videoSetInfoMapper.getVideoSetInfoById(videoSetId)).orElseThrow(TargetNotFoundException::new);
        List<VideoDetailInfoDao> videoDetailInfo = Optional.of(videoDetailInfoMapper.getVideoDetailInfoBatch(videoSetId)).orElseThrow(TargetNotFoundException::new);
        return buildVideoSetDetailInfoDTO(videoSetInfo,videoDetailInfo);
    }

    private VideoSetDetailInfoDTO buildVideoSetDetailInfoDTO(VideoSetDetailInfoDao videoSetInfo, List<VideoDetailInfoDao> videoDetailInfo){
        VideoSetDetailInfoDTO videoSetDetailInfoDTO = new VideoSetDetailInfoDTO();
        videoSetDetailInfoDTO.setId(videoSetInfo.getId());
        videoSetDetailInfoDTO.setTitle(videoSetInfo.getTitle());
        videoSetDetailInfoDTO.setDescribe(videoSetInfo.getDescribe());
        videoSetDetailInfoDTO.setCoverImgSrc(videoSetInfo.getCoverImgSrc());
        videoSetDetailInfoDTO.setPublishDate(videoSetInfo.getPublishDate());
        videoSetDetailInfoDTO.setUploadDate(videoSetInfo.getUploadDate());
        videoSetDetailInfoDTO.setVideoDetailInfo(videoDetailInfo);
        return videoSetDetailInfoDTO;
    }
}
