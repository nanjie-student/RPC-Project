package com.spring.version3.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * 实现RPCServer接口，负责监听与发送数据
 */
@AllArgsConstructor
@Slf4j
public class NettyRPCServer implements RPCServer {

    private ServiceProvider serviceProvider;
    @Override
    public void start(int port) {
        //1. 创建对应的EventLoop线程池备用, 分bossGroup和workerGroup
        NioEventLoopGroup bossgroup = new NioEventLoopGroup();
        NioEventLoopGroup workgroup = new NioEventLoopGroup();
        System.out.println("Netty RPC Server started");
        try {
            //2. 创建netty对应的入口核心类 ServerBootstrap
            ServerBootstrap bootstrap = new ServerBootstrap();
            //1.初始化
            bootstrap.group(bossgroup, workgroup)
                        .channel(NioServerSocketChannel.class)
                        .childHandler(new NettyServerInitializer(serviceProvider));
            //2.同步阻塞
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            //3.死循环监听
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            bossgroup.shutdownGracefully();
            workgroup.shutdownGracefully();
        }
    }

    @Override
    public void stop() {

    }
}
