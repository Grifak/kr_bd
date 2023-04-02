package com.example.kr_bd.model;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String cardNumber;
    private String phoneNumber;
    private String email;
    private String password;
}
