package mc.video.service.controller;

import mc.video.service.constant.ResponseBody;
import mc.video.service.constant.ReturnCode;
import mc.video.service.dto.VideoSetBriefInfoDTO;
import mc.video.service.service.VideoSetInfoService;
import mc.video.service.vo.VideoSetBriefInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: ZhuXingda
 * @Date: 2021/8/6 00:51
 */
@Controller
@RequestMapping("/recommend")
public class VideoRecommendController {
    @Autowired
    VideoSetInfoService videoSetInfoService;

    @GetMapping("/index")
    public ResponseEntity<ResponseBody<VideoSetBriefInfoVO>> recommendVideoForIndex(@RequestParam("pageIndex") int pageIndex,
                                                   @RequestParam("pageSize") int pageSize){
        VideoSetBriefInfoVO vo = videoSetInfoService.getRecommendVideoListForIndex(pageIndex, pageSize);
        ResponseBody<VideoSetBriefInfoVO> responseBody = new ResponseBody<>();
        responseBody.setCode(ReturnCode.SUCCESS.getCode());
        responseBody.setMsg("获取成功");
        responseBody.setData(vo);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping("/personal")
    public ResponseEntity<ResponseBody<VideoSetBriefInfoVO>> recommendVideoForPersonal(@RequestParam("pageIndex") int pageIndex,
                                                                                    @RequestParam("pageSize") int pageSize){
        VideoSetBriefInfoVO vo = videoSetInfoService.getRecommendVideoListForIndex(pageIndex, pageSize);
        ResponseBody<VideoSetBriefInfoVO> responseBody = new ResponseBody<>();
        responseBody.setCode(ReturnCode.SUCCESS.getCode());
        responseBody.setMsg("获取成功");
        responseBody.setData(vo);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
