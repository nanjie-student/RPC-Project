package com.spring.version2.service;

import com.spring.version2.common.User;

public interface UserService {
    User getUserById(Integer id);

    Integer insertUserId(User user);
}
