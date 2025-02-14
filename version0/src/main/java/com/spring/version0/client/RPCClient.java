package com.spring.version0.client;

import com.spring.version0.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class RPCClient {
    public static void main(String[] args) {
        //create socket connect
        try (Socket socket = new Socket("127.0.0.1", 8899);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

            // 生成随机 ID 并发送
            int userId = new Random().nextInt(100);
            System.out.println(" 发送用户ID: " + userId);
            oos.writeObject(userId);
            oos.flush();

            // 读取服务器返回的 User 对象
            User user = (User) ois.readObject();
            System.out.println("从服务器收到: " + user);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("客户端启动失败");
        }

    }
}
