package com.cn.myRpc.version0.service;

import com.cn.myRpc.version0.common.User;

//定义接口
public interface UserService {
    // 客户端通过这个接口调用服务端的实现类
    User getUserByUserId(Integer id);
}
