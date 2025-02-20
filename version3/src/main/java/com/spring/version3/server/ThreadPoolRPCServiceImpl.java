package com.spring.version3.server;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolRPCServiceImpl implements RPCServer{

    private final ThreadPoolExecutor threadPool;
    private ServiceProvider serviceProvider;
    // 默认构造函数
    public ThreadPoolRPCServiceImpl(ServiceProvider serviceProvider){
        threadPool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                1000, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
        this.serviceProvider = serviceProvider;
    }
    // 自定义构造函数
    public ThreadPoolRPCServiceImpl(ServiceProvider serviceProvider, int corePoolSize,
                                  int maximumPoolSize,
                                  long keepAliveTime,
                                  TimeUnit unit,
                                  BlockingQueue<Runnable> workQueue){

        threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        this.serviceProvider = serviceProvider;
    }



    @Override
    public void start(int port) {

    }

    @Override
    public void stop() {

    }
}
