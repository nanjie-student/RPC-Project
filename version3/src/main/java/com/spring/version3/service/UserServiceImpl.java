package com.spring.version3.service;

import com.spring.version3.common.User;

import java.util.UUID;

public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(Integer id) {
        User user = User.builder().id(id).username(UUID.randomUUID().toString()).sex(true).build();
        System.out.println("Client queries for user id"+ id);
        return user;
    }

    @Override
    public Integer insertUserId(User user) {
        System.out.println("Client queries for insert user id"+ user);
        return 1;
    }
}
