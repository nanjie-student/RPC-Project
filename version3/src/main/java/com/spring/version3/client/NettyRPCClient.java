package com.spring.version3.client;

import com.spring.version3.common.RPCRequest;
import com.spring.version3.common.RPCResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;


public class NettyRPCClient implements RPCClient {
    private static final Bootstrap bootstrap;
    private static final EventLoopGroup group;
    private String host;
    private int port;
    public NettyRPCClient(String host, int port) {
        this.host = host;
        this.port = port;
    }
    // netty客户端初始化，重复使用
    static {
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class)
                .handler(new NettyClientInitializer());
    }
    /**
     * 这里需要操作一下，因为netty的传输都是异步的，你发送request，会立刻返回一个值， 而不是想要的相应的response
     */
    @Override
    public RPCResponse sendRequest(RPCRequest request) {
        try {
            ChannelFuture future = bootstrap.connect(host, port).sync();
            Channel channel = future.channel();
            //System.out.println(request.toString());
            //发送数据
            channel.writeAndFlush(request);
            System.out.println("这里有问题吗");
            channel.closeFuture().sync();
            System.out.println("Client Channel closed");
            // 阻塞的获得结果，通过给channel设计别名，获取特定名字下的channel中的内容（这个在hanlder中设置）
            // AttributeKey是，线程隔离的，不会由线程安全问题。
            // 实际上不应通过阻塞，可通过回调函数
            AttributeKey<RPCResponse> key = AttributeKey.valueOf("RPCRESPONSE");
            RPCResponse response = channel.attr(key).get();
            //System.out.println(response);
            return response;
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        return null;
    }
}
