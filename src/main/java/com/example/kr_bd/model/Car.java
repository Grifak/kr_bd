package com.example.kr_bd.model;

import lombok.Data;

@Data
public class  Car {
    Long id;
    String vin;
    String registrationNumber;
    String model;
    String brand;
    String horsePower;
    String modifPower;
}
