package mc.video.service.mapper;

import mc.video.service.dao.VideoSetBriefInfoDao;
import mc.video.service.dao.VideoSetDetailInfoDao;
import org.apache.ibatis.annotations.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Mapper
public interface VideoSetInfoMapper {

    @Select("SELECT `id`, `title`, `video_describe`, `total_count`, `cover_img_src` ,`publish_date`, `upload_date` " +
            "FROM video_set_info WHERE id = #{id};")
    @ResultMap(value = {"videoSetDetailInfoDao"})
    VideoSetDetailInfoDao getVideoSetInfoById(@NotNull @Param("id") Long id);

    @Select("SELECT `id`, `title`, `cover_img_src` ,`publish_date`, `upload_date`, `total_count` " +
            "FROM video_set_info " +
            "ORDER BY upload_date DESC LIMIT #{start}, #{size};")
    @ResultMap(value = {"videoSetBriefInfoDao"})
    List<VideoSetBriefInfoDao> getVideoSetBriefInfoList(@Param("start") int start, @Param("size") int size);

    List<VideoSetBriefInfoDao> getVideoSetBriefInfoListByIdSet(@NotEmpty @NotNull @Param("idSet") Set<Long> idSet);

    @Select("SELECT COUNT(id) FROM video_set_info;")
    int getVideoSetInfoCount();
}
