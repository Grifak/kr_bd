package com.example.kr_bd.model;

import lombok.Data;
import java.util.List;

@Data
public class CarModification {
    private Long carId;
    private List<Long> modifIdList;
}
