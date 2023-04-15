package com.example.kr_bd.model;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String login;
    private String password;
    private String email;
}
