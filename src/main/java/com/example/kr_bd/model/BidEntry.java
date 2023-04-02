package com.example.kr_bd.model;

import lombok.Data;
import java.util.List;

@Data
public class BidEntry {
    private Long id;
    private String name;
    private String status;
    private Long bidAmount;
    private List<String> registrationNumberList;
}
