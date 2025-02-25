package com.spring.version3.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
public class RPCResponse implements Serializable {
    private int code;
    private String message;
    private Object data;
    // 更新,这里我们需要加入这个，不然用其它序列化方式（除了java Serialize）得不到data的type
    private Class<?> dataType;
    public static RPCResponse success(Object data) {
        return RPCResponse.builder().code(200).data(data).build();
    }
    public static RPCResponse fail(){
        return RPCResponse.builder().code(500).message("service wrong").build();
    }
}
