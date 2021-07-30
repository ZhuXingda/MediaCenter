package mh.cas.center.mapper;

import mh.cas.center.pojo.UserInfoPojo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    @Select("SELECT id, email, user_name, user_pwd, avatar_src FROM user_info WHERE id = #{id}")
    @Results({
            @Result(property = "id",column = "id",id = true),
            @Result(property = "email",column = "email"),
            @Result(property = "userName",column = "user_name"),
            @Result(property = "password",column = "user_pwd"),
            @Result(property = "avatarSrc",column = "avatar_src"),
    })
    UserInfoPojo getUserInfoById(@Param("id") long id);

    @Select("SELECT id, email FROM user_info limit #{start}, #{size}")
    @Results({
            @Result(property = "id",column = "id",id = true),
            @Result(property = "email",column = "email")
    })
    List<UserInfoPojo> getUserInfoList(@Param("start") int start, @Param("size") int size);

    @Select("SELECT COUNT(id) FROM user_info")
    int getUserCount();

    @Insert("INSERT INTO user_info" +
            " (email, user_name, user_pwd, avatar_src)" +
            " VALUES (#{user.email}, #{user.userName}, #{user.password}, #{user.avatarSrc})")
    //将自增长的key赋值给user对象的id属性
    @Options(useGeneratedKeys = true, keyProperty = "id")
    long createUserInfo(@Param("user") UserInfoPojo user);
}
