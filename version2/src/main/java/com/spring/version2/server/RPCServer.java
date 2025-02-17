package com.spring.version2.server;


import com.spring.version2.common.RPCRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端接受解析reuqest与封装发送response对象
 * */
public interface RPCServer {
    void start(int port);
    void stop();
}