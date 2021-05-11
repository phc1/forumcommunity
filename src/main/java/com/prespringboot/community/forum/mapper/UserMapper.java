package com.prespringboot.community.forum.mapper;

import com.prespringboot.community.forum.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO USER(NAME,ACCOUNT_ID,TOKEN,GMT_CREATE,GMT_MODIFIED,avatar_url) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User foundByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User foundById(@Param("id") Integer id);
}
