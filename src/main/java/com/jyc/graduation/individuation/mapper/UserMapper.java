package com.jyc.graduation.individuation.mapper;

import com.jyc.graduation.individuation.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

    @Select("select * from user where account = #{account} and password = #{password}")
    User queryUserByAccountAndPassword(User user);

    @Select("select * from user where account = #{account}")
    User queryUserByAccount(String account);

    @Insert("INSERT INTO user (account,password,name) VALUES (#{account},#{password},#{name})")
    void addUser(User user);
}
