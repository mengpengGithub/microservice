package com.captain.user.service;

import com.captain.thrift.user.UserInfo;
import com.captain.thrift.user.UserService;
import com.captain.user.mapper.UserMapper;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author mengpeng
 * @Date 2020/3/15 21:22
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService.Iface {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserInfo getUserById(int id) throws TException {
        return userMapper.getUserById(id);
    }

    @Override
    public UserInfo getUserByName(String username) throws TException {
        return userMapper.getUserByName(username);
    }

    @Override
    public void registerUser(UserInfo userInfo) throws TException {

        userMapper.registerUser(userInfo);
    }
}
