package com.spring.version3.server;

import com.spring.version3.service.BlogService;
import com.spring.version3.service.BlogServiceImpl;
import com.spring.version3.service.UserService;
import com.spring.version3.service.UserServiceImpl;

public class TestServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        BlogService blogService = new BlogServiceImpl();
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.provideServiceInterface(userService);
        serviceProvider.provideServiceInterface(blogService);
        RPCServer rpcServer = new NettyRPCServer(serviceProvider);
        rpcServer.start(8899);
    }


}
