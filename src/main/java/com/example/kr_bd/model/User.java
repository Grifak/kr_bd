package com.example.kr_bd.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class User {
    private Long id;
    private String login;
    private String email;
    private String password;
    private LocalDateTime created;
}
