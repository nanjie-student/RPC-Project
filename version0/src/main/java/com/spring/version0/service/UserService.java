package com.spring.version0.service;

import com.spring.version0.common.User;

import java.io.Serializable;

public interface UserService extends Serializable {
    User getUserById(int id);

}
