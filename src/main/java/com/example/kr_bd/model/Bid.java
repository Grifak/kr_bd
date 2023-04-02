package com.example.kr_bd.model;

import lombok.Data;

@Data
public class Bid {
    private Long id;
    private String status;
    private String name;
    private String registrationNumber;
    private Long bidAmount;
    private Long authorId;
    private Long employeeId;
}
