package mc.video.service.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author: ZhuXingda
 * @Date: 2021/8/7 21:30
 */
@Mapper
public interface VideoTagRelationMapper {
    @Select("SELECT video_set_id FROM video_tag_relation WHERE tag_id = #{tagId} AND LIMIT #{start}, #{size}")
    @ResultType(Long.class)
    List<Long> getVideoSetIdListByTagId(@NotNull @Param("tagId") Long tagId,
                                        @Param("start") int start,
                                        @Param("size") int size);

    @Select("SELECT tag_id FROM video_tag_relation WHERE video_set_id = #{videoSetId}")
    @ResultType(Long.class)
    List<Long> getTagIdListByVideoSetId(@NotNull @Param("videoSetId") Long videoSetId);
}
