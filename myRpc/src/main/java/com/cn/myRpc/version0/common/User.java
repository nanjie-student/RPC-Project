package com.cn.myRpc.version0.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable{
    //field: Client and Server
    private Integer id;
    private String username;
    private Boolean sex;


}