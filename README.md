# RPC-Project

Following the design principles of mainstream RPC framework in the market implemented a high performance and 
highly available RPC framework manually using java.
Version0:
RPC的最大痛点：
只能调用服务端Service唯一确定的方法，如果有两个方法需要调用呢?（Reuest需要抽象）
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

