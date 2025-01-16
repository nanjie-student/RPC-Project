package com.cn.myRpc.version0.server;

import com.cn.myRpc.version0.common.User;
import com.cn.myRpc.version0.service.UserService;

import java.util.Random;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    @Override
    public User getUserByUserId(Integer id){
        System.out.println("客户端查询了"+id+"的用户");
        //模拟从数据库取用户的行为
        Random random = new Random();
        User user = User.builder().username(UUID.randomUUID().toString()).id(id).sex(random.nextBoolean()).build();
        return user;
    }
}
