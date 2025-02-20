package com.spring.version3.client;

import com.spring.version3.common.Blog;
import com.spring.version3.common.User;
import com.spring.version3.service.BlogService;
import com.spring.version3.service.UserService;

public class TestClient {
    public static void main(String[] args) {
        //构建一个使用java Socket/ netty/....传输的客户端
        RPCClient rpcClient = new NettyRPCClient("127.0.0.1", 8899);
        //把这个客户端传入代理客户端
        RPCClientProxy rpcClientProxy = new RPCClientProxy(rpcClient);
        //代理客户端根据不同的服务，获得一个代理类， 并且这个代理类的方法以或者增强（封装数据，发送请求）
        UserService userService = rpcClientProxy.getProxy(UserService.class);
        //调用方法
        User userByUserId = userService.getUserById(10);
        System.out.println("get the userId from service:"+userByUserId);

        User user = User.builder().username("Jayda").sex(true).id(1000).build();
        Integer integer = userService.insertUserId(user);
        System.out.println("insert data to service:"+ integer);

        BlogService blogService = rpcClientProxy.getProxy(BlogService.class);
        Blog blogById = blogService.getBlogById(10000);
        System.out.println("get blog by id:"+blogById);



    }
}
