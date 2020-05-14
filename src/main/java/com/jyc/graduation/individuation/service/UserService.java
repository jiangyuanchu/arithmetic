package com.jyc.graduation.individuation.service;

import com.jyc.graduation.individuation.domain.User;
import com.jyc.graduation.individuation.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired UserMapper userMapper;

    public User queryUserByAccountAndPassword(User user){
        return userMapper.queryUserByAccountAndPassword(user);
    }

    public User queryUserByAccount(String account){
        return userMapper.queryUserByAccount(account);
    }

    public void addUser(User user){
        userMapper.addUser(user);
    }

}
