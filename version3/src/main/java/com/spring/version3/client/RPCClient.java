package com.spring.version3.client;


import com.spring.version3.common.RPCRequest;
import com.spring.version3.common.RPCResponse;

public interface RPCClient {
    RPCResponse sendRequest(RPCRequest request);
}
