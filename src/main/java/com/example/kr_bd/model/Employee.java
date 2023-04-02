package com.example.kr_bd.model;

import lombok.Data;

@Data
public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;
    private Long carstationId;
    private String post;
    private String password;

}
