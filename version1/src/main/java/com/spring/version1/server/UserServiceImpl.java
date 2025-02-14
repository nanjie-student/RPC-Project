package com.spring.version1.server;

import com.spring.version1.common.User;
import com.spring.version1.service.UserService;

import java.util.Random;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(int id) {
        System.out.println("getUserById"+ id);
        //模拟从数据库取到的id
        Random random = new Random();
        User user = User.builder().username(UUID.randomUUID().toString())
                .id(id)
                .sex(random.nextBoolean())
                .build();
        return user;
    }

    @Override
    public Integer insertUser(User user) {
        System.out.println("insertUser"+ user);
        return 1;
    }
}
