package mc.video.service.mapper;

import mc.video.service.dao.VideoDetailInfoDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VideoDetailInfoMapper {

    @Select("SELECT `id`, `set_id`, `title`, `video_src`, `upload_date`" +
            "FROM video_detail_info WHERE set_id = #{setId};")
    @ResultMap(value = {"videoDetailInfoDao"})
    List<VideoDetailInfoDao> getVideoDetailInfoBatch (@Param("setId") Long setId);
}
