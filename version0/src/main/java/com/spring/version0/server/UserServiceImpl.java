package com.spring.version0.server;

import com.spring.version0.common.User;
import com.spring.version0.service.UserService;

import java.util.Random;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    @Override
    public User getUserById(int id) {
        System.out.println("client query user by id:"+id+" 's customer");
        Random random = new Random();
        User user = User.builder().userName(UUID.randomUUID().toString())
                .Id(id)
                .sex(random.nextBoolean()).build();
        return user;
    }
}
