package mc.video.service.mapper;

import mc.video.service.dao.VideoTagDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author: ZhuXingda
 * @Date: 2021/8/7 21:18
 */
@Mapper
public interface VideoTagMapper {

    List<VideoTagDao> getVideoTagList(@NotNull @Param("idList") List<Long> idList);
}
