package com.cn.myRpc.version0.client;

import com.cn.myRpc.version0.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

/*
*
* Client建立socket连接，传输ID给Server，得到的返回user对象*/
public class RPCClient {
    public static void main(String[] args)  {
        //建立Socket连接
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1",8899);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            //传给Server端Id
            objectOutputStream.writeInt(new Random().nextInt());
            objectOutputStream.flush();
            //Server search data,return 对应的对象
            User user = (User)objectInputStream.readObject();
            System.out.println("服务器端返回的User object:" + user);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("客户端启动失败");
        }

    }
}
