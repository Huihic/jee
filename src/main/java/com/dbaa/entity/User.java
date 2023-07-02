package com.dbaa.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class User implements Serializable {
    private int u_id;
    private String u_name;
    private String u_password;
    private String u_email;
}
