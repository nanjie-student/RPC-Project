package com.spring.version2.server;

import com.spring.version2.service.BlogService;
import com.spring.version2.service.UserService;

import java.util.HashMap;
import java.util.Map;

public class TestServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        BlogService blogService = new BlogServiceImpl();
        //Map<String,Object> serviceProvider = new HashMap<>();
        //暴露两个服务接口，在RPCServer中加一个hashmap
        ServiceProvider serviceProvider = new ServiceProvider();
        //serviceProvider.put("userService",userService);
        //serviceProvider.put("blogService",blogService);
        serviceProvider.provideServiceInterface(userService);
        serviceProvider.provideServiceInterface(blogService);
        RPCServer rpcServer = new SimpleRPCRPCServer(serviceProvider);
        rpcServer.start(8899);


    }
}
