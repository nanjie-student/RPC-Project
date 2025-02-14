package com.spring.version0.server;

import com.spring.version0.common.User;
import com.spring.version0.service.UserService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RPCServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8899)) {
            System.out.println("🚀 服务器启动，监听端口 8899...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void handleClient(Socket clientSocket) {
        try (
                ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream())
        ) {
            // 读取客户端传来的 ID
            Object obj = ois.readObject();
            if (obj instanceof Integer) {
                int userId = (Integer) obj;
                System.out.println("📥 收到客户端请求: 用户ID = " + userId);

                // 查询用户信息
                UserService userService = new UserServiceImpl();
                User user = userService.getUserById(userId);

                // 发送给客户端
                System.out.println("📤 发送用户信息: " + user);
                oos.writeObject(user);
                oos.flush();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
