package com.example.kr_bd.model;

import lombok.Data;

@Data
public class AuthRequest {
    private String login;
    private String password;
}
