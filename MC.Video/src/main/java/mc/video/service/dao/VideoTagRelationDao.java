package mc.video.service.dao;

import lombok.Data;


/**
 * @author: ZhuXingda
 * @Date: 2021/8/6 11:14
 */
@Data
public class VideoTagRelationDao {
    private Long id;
    private String tagId;
    private String videoSetId;
}
