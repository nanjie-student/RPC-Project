package com.spring.version3.client;

import com.spring.version3.common.RPCRequest;
import com.spring.version3.common.RPCResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SimpleRPCClient implements RPCClient {
    private int port;
    private String host;

    /**
     * 客户端发起一次请求调用，Socket建立连接，发起请求Request，得到响应Response
     * 这里的request是封装好的，不同的service需要进行不同的封装， 客户端只知道Service接口，
     * 需要一层动态代理根据反射封装不同的Service
     * */
    @Override
    public RPCResponse sendRequest(RPCRequest request) {
        try {
            Socket socket = new Socket(host,port);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            System.out.println(request);
            oos.writeObject(request);
            oos.flush();
            RPCResponse rpcResponse = (RPCResponse) ois.readObject();
            return rpcResponse;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("wrong");
            return null;

        }
    }
}
