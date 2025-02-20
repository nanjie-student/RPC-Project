package com.spring.version3.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 这个实现类代表着java原始的BIO监听模式，来一个任务，就new一个线程去处理
 * 处理任务的工作见WorkThread中
 */
public class SimpleRPCServerImpl implements RPCServer {

    private ServiceProvider serviceProvider;

    public SimpleRPCServerImpl(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    @Override
    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started");
            //BIO方式监听线程
            while (true) {
                Socket socket = serverSocket.accept();
                //开启一个新的线程
                new Thread(new WorkThread(socket,serviceProvider)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void stop() {

    }
}
