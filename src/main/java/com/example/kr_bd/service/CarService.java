package com.example.kr_bd.service;

import lombok.RequiredArgsConstructor;
import com.example.kr_bd.model.Car;
import com.example.kr_bd.model.CarModification;
import com.example.kr_bd.respository.CarModificationRepository;
import com.example.kr_bd.respository.CarRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final CarModificationRepository carModificationRepository;

    public void create(Car car, Long userId){
        carRepository.createCar(car, userId);
    }

    public List<Car> getAllCar(Long userId, Boolean asc){
        return carRepository.getAllCar(userId, asc);
    }

    public void addModif(CarModification carModification){
        List<Long> modifIdList = carModification.getModifIdList();
        modifIdList.stream().forEach( mod ->
                carModificationRepository.addModif(carModification.getCarId(), mod)
        );

    }
}
