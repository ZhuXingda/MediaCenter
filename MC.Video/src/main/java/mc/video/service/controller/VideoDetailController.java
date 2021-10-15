package mc.video.service.controller;

import mc.video.service.dto.VideoSetDetailInfoDTO;
import mc.video.service.service.VideoSetInfoService;
import mc.video.service.service.impl.VideoSetDetailServiceImpl;
import mc.video.service.vo.VideoSetDetailInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: ZhuXingda
 * @Date: 2021/9/19 20:30
 */
@Controller
@RequestMapping("/detail")
public class VideoDetailController {
    @Autowired
    VideoSetDetailServiceImpl videoSetDetailService;

    @GetMapping("/getById")
    public VideoSetDetailInfoDTO getVideoSetDetailInfoById(@RequestParam("set_id")Long setId){
        return videoSetDetailService.getVideoSetDetailInfoById(setId);
    }
}
