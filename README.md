# RPC-Project

Following the design principles of mainstream RPC framework in the market implemented a high performance and 
highly available RPC framework manually using java.

Version0:
RPC的最大痛点：
只能调用服务端Service唯一确定的方法，如果有两个方法需要调用呢?（需要抽象）
返回值只支持User对象，如果需要传一个字符串或者一个Dog，String对象呢（Response需要抽象）
客户端不够通用，host，port， 与调用的方法都特定（需要抽象）

Version1:
总结
定义更加通用的消息格式：Request 与Response格式， 从此可能调用不同的方法，与返回各种类型的数据。
使用了动态代理进行不同服务方法的Request的封装，
客户端更加松耦合，不再与特定的Service，host，port绑定

最大痛点：
服务端我们直接绑定的是UserService服务，如果还有其它服务接口暴露呢?（多个服务的注册）
服务端以BIO的方式性能是否太低，
服务端功能太复杂：监听，处理。需要松耦合

Version2:
总结：
在一版本中，我们重构了服务端的代码，代码更加简洁，
添加线程池版的服务端的实现，性能应该会有所提升（未测）
并且服务端终于能够提供不同服务了， 功能更加完善，不再鸡肋

此RPC最大的痛点
传统的BIO与线程池网络传输性能低

Version3:
背景知识
netty高性能网络框架的使用
本节问题
如何提升这个rpc的性能? 可以从两个方面入手，网络传输从BIO到NIO，序列化要减少字节流长度，提高序列化反序列化效率
知名的rpc框架：dubbo， grpc都是使用netty底层进行通信的

升级过程：
前提： maven pom.xml文件引入netty
<dependency>
<groupId>io.netty</groupId>
<artifactId>netty-all</artifactId>
<version>4.1.100.Final</version>
</dependency>
升级1： 重构客户端代码

客户端的代码太乱了， 我们先进行代码重构，才有利于后面使用netty的方式实现客户端，使之不同方式网络连接的客户端有
着同样的结构，同样的api
假如我们现在已经有了两个客户端：SimpleRPCClient(使用java BIO的方式)， NettyRPCClient（使用netty进行网络
传输），那么它们两者的共性是啥?发送请求与得到response是共性， 而建立连接与发送请求的方式是不同点。

总结
此版本我们完成了客户端的重构，使之能够支持多种版本客户端的扩展（实现RPCClient接口）

并且使用netty实现了客户端与服务端的通信

此RPC最大痛点
java自带序列化方式（Java序列化写入不仅是完整的类名，也包含整个类的定义，包含所有被引用的类），不够通用，不够高效