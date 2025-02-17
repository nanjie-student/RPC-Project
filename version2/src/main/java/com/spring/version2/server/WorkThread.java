package com.spring.version2.server;

import com.spring.version2.common.RPCRequest;
import com.spring.version2.common.RPCResponse;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

@AllArgsConstructor
public class WorkThread implements Runnable {
    private Socket socket;

    private ServiceProvider serviceProvider;

    @Override
    public void run() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            // 读取客户端传过来的request
            RPCRequest rpcRequest = (RPCRequest) ois.readObject();
            // 反射调用服务方法获得返回值
            // 反射调用服务方法获得返回值
            RPCResponse response = getResponse(rpcRequest);
            oos.writeObject(response);
            oos.flush();

        } catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("can't get the source from the server");

        }
    }

    private RPCResponse getResponse(RPCRequest rpcRequest) {
        //得到服务名
        String interfaceName = rpcRequest.getInterfaceName();
        //得到服务端相应服务实现
        Object service = serviceProvider.getServiceInterface(interfaceName);
        //反射调用方法
        Method method = null;
        try {
            method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamsTypes());
            Object invoke = method.invoke(service, rpcRequest.getParams());
            return RPCResponse.success(invoke);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e){
            e.printStackTrace();
            System.out.println("can't get the method from the server");
            return RPCResponse.fail();

        }
    }
}
