package com.captain.user.mapper;

import com.captain.thrift.user.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author mengpeng
 * @Date 2020/3/15 21:16
 * @Version 1.0
 */
@Mapper
public interface UserMapper {

    @Select("select id,username,password, real_name as realName" +
            ", mobile,email from pe_user where id=#{id}")
    UserInfo getUserById(@Param("id") int id);

    @Select("select id,username,password, real_name as realName" +
            ", mobile,email from pe_user where username=#{username}")
    UserInfo getUserByName(@Param("username") String username);


    @Insert("insert into pe_user (username,password,real_name,mobile,email)" +
            " values(#{u.username},#{u.password}, #{u.realName}, #{u.mobile},#{u.email})")
    void registerUser(@Param("u") UserInfo userInfo);
}
