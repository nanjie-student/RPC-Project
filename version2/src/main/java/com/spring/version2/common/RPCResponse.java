package com.spring.version2.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class RPCResponse implements Serializable {
    private int code;
    private String msg;
    private Object data;

    public static RPCResponse success(Object data) {
        return RPCResponse.builder().code(200).msg("success").data(data).build();
    }
    public static RPCResponse fail() {
        return RPCResponse.builder().code(500).msg("Service request failed").build();
    }

}
