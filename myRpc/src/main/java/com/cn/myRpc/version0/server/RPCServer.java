package com.cn.myRpc.version0.server;

import com.cn.myRpc.version0.common.User;
import com.cn.myRpc.version0.service.UserService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RPCServer {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        try {
            ServerSocket serverSocket = new ServerSocket(8899);
            System.out.println("服务器启动了");
            //BIO方式监听Socket
            while(true){
                Socket socket = serverSocket.accept();
                //开启一个线程去处理
                new Thread(() ->{
                    ObjectOutputStream oos = null;
                    try {
                        oos = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                        //接受Client端传的ID
                        Integer id = ois.readInt();
                        User userByUserId = userService.getUserByUserId(id);
                        //写入User对象给客户端
                        oos.writeObject(userByUserId);
                        oos.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("从I0中读取数据错误");
                    }


                }).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }
}

