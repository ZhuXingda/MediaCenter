package mc.video.service.service;

import mc.video.service.dto.VideoSetDetailInfoDTO;

public interface VideoSetDetailService {
    VideoSetDetailInfoDTO getVideoSetDetailInfoById(Long videoId);
}
