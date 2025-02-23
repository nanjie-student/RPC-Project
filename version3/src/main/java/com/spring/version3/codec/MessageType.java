package com.spring.version3.codec;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MessageType {
    REQUEST(0),RESPONSE(1);
    private int code;
    protected int getCode(){
        return code;
    }

}
