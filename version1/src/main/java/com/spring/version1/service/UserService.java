package com.spring.version1.service;

import com.spring.version1.common.User;

import java.io.Serializable;

public interface UserService {
    User getUserById(int id);
    //给这个服务增加一个功能
    Integer insertUser(User user);

}
