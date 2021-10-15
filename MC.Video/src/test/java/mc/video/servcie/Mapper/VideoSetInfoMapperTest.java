package mc.video.servcie.Mapper;

import mc.video.servcie.TestConfig;
import mc.video.service.dao.VideoSetBriefInfoDao;
import mc.video.service.dao.VideoSetDetailInfoDao;
import mc.video.service.mapper.VideoSetInfoMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: ZhuXingda
 * @Date: 2021/8/7 16:37
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
public class VideoSetInfoMapperTest {
    @Autowired
    VideoSetInfoMapper videoSetInfoMapper;

    @Test
    public void getVideoSetInfoListByIdSetTest(){
        Set<Long> idSet = new HashSet<>();
        idSet.add(1L);
        idSet.add(2L);
        List<VideoSetBriefInfoDao> videoSetInfoListByIdSet = videoSetInfoMapper.getVideoSetBriefInfoListByIdSet(idSet);
        for (VideoSetBriefInfoDao videoSetDetailInfoDao : videoSetInfoListByIdSet) {
            System.out.println(videoSetDetailInfoDao.toString());
        }
    }

    @Test
    public void getVideoSetInfoByIdTest(){
        VideoSetDetailInfoDao videoSetInfoById = videoSetInfoMapper.getVideoSetInfoById(1L);
        System.out.println(videoSetInfoById.toString());
    }
}
