package com.example.kr_bd.service;

import lombok.RequiredArgsConstructor;
import com.example.kr_bd.model.Car;
import com.example.kr_bd.respository.CarRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public void create(Car car){
        carRepository.createCar(car);
    }

    public List<Car> getAllCar(Long userId){ return carRepository.getAllCar(userId); }
}
