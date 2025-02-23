package com.spring.version3.server;

import com.spring.version3.codec.JsonSerializer;
import com.spring.version3.codec.MyDecode;
import com.spring.version3.codec.MyEncode;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import lombok.AllArgsConstructor;


/**
 * 初始化，主要负责序列化的编码解码， 需要解决netty的粘包问题packet sticking
 */
@AllArgsConstructor
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    private ServiceProvider serviceProvider;

//    @Override
//    protected void initChannel(SocketChannel socketChannel) throws Exception {
//        ChannelPipeline pipeline = socketChannel.pipeline();
//        //1.FixedLengthFrameDecoder
//        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0,4,0,4));
//        pipeline.addLast(new LengthFieldPrepender(4));
//        // 这里使用的还是java 序列化方式， netty的自带的解码编码支持传输这种结构
//        pipeline.addLast(new ObjectEncoder());
//        pipeline.addLast(new ObjectDecoder(new ClassResolver() {
//            @Override
//            public Class<?> resolve(String className) throws ClassNotFoundException {
//                return Class.forName(className);
//            }
//        }));
//        pipeline.addLast(new NettyRPCServerHandler(serviceProvider));
//    }
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 使用自定义的编解码器
        pipeline.addLast(new MyDecode());
        // 编码需要传入序列化器，这里是json，还支持ObjectSerializer，也可以自己实现其他的
        pipeline.addLast(new MyEncode(new JsonSerializer()));
        pipeline.addLast(new NettyRPCServerHandler(serviceProvider));
    }

}
