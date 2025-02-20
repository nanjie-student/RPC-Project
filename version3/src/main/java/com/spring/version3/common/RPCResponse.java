package com.spring.version3.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class RPCResponse implements Serializable {
    private int code;
    private String message;
    private Object data;
    public static RPCResponse success(Object data) {
        return RPCResponse.builder().code(200).data(data).build();
    }
    public static RPCResponse fail(){
        return RPCResponse.builder().code(500).message("service wrong").build();
    }
}
