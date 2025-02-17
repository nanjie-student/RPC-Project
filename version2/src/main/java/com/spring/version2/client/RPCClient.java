package com.spring.version2.client;

import com.spring.version2.common.Blog;
import com.spring.version2.common.User;
import com.spring.version2.service.BlogService;
import com.spring.version2.service.UserService;

public class RPCClient {
    public static void main(String[] args) {
        RPCClientProxy rpcClientProxy = new RPCClientProxy("127.0.0.1", 8899);
        UserService userService = rpcClientProxy.getProxy(UserService.class);
        User userById = userService.getUserById(10);
        System.out.println("The user received from the server is：" + userById);
        User user = User.builder().userName("张三").id(100).sex(true).build();
        Integer integer = userService.insertUserId(user);
        System.out.println("Insert data into the server：" + integer);

        BlogService blogService = rpcClientProxy.getProxy(BlogService.class);
        Blog blogById = blogService.getBlogById(10000);
        System.out.println("The blog received from the server is " + blogById);

    }
}
