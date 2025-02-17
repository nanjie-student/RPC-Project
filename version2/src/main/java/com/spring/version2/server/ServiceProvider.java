package com.spring.version2.server;

import java.util.HashMap;
import java.util.Map;

public class ServiceProvider {

    /**
     * 一个实现类可能实现多个接口
     */
    private Map<String,Object> interfaceProvider;

    public ServiceProvider(){
        this.interfaceProvider = new HashMap<>();
    }
    public void provideServiceInterface( Object service){
        String serviceName = service.getClass().getName();
        Class<?>[] interfaces = service.getClass().getInterfaces();
        for(Class clazz: interfaces){
            interfaceProvider.put(clazz.getName(),service);
        }
    }
    public Object getServiceInterface(String interfaceName){
        return interfaceProvider.get(interfaceName);
    }

}
