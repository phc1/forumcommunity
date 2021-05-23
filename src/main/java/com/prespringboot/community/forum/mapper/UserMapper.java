package com.prespringboot.community.forum.mapper;

import com.prespringboot.community.forum.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Value;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO USER(NAME,ACCOUNT_ID,TOKEN,GMT_CREATE,GMT_MODIFIED,avatar_url) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User foundByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User foundById(@Param("id") Integer id);

    @Select("select * from user where account_id = #{accountId}")
    User selectUser(@Param(value = "accountId") String accountId);

    @Update("update user set name = #{name},token = #{token},gmt_modified = #{gmtModified},avatar_url = #{avatarUrl} where id = #{id}")
    void update(User user);
}
