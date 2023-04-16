package com.example.kr_bd.model;

import lombok.Data;
import java.util.List;

@Data
public class  Car {
    Long id;
    String vin;
    String registrationNumber;
    String model;
    String brand;
    String horsePower;
    String modifPower;
    List<Modification> modifList;
}
