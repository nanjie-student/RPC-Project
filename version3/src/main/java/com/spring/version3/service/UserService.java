package com.spring.version3.service;

import com.spring.version3.common.User;

public interface UserService {
    User getUserById(Integer id);

    Integer insertUserId(User user);
}
