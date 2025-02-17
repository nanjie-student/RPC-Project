package com.spring.version2.server;

import com.spring.version2.common.User;
import com.spring.version2.service.UserService;

import java.util.Random;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    @Override
    public User getUserById(Integer id) {
        System.out.println("getUserById"+ id);
        Random random = new Random();
        User user = User.builder().userName(UUID.randomUUID().toString())
                .id(id)
                .sex(random.nextBoolean()).build();
        return user;
    }

    @Override
    public Integer insertUserId(User user) {
        System.out.println("insertUserId"+ user);
        return 1;
    }
}
