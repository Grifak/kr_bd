package com.example.kr_bd.service;

import com.example.kr_bd.model.Car;
import com.example.kr_bd.model.CarModification;
import com.example.kr_bd.model.DeleteModifRequest;
import com.example.kr_bd.model.Modification;
import com.example.kr_bd.respository.CarModificationRepository;
import com.example.kr_bd.respository.CarRepository;
import com.example.kr_bd.respository.ModifRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final CarModificationRepository carModificationRepository;
    private final ModifRepository modifRepository;

    public void create(Car car, Long userId){
        carRepository.createCar(car, userId);
    }

    public List<Car> getAllCar(Long userId, Boolean asc){
        List<Car> carList = carRepository.getAllCar(userId, asc);
        carList.forEach(
                car -> {
                    List<Modification> modifList = modifRepository.getAllModifByCarId(car.getId());
                    car.setModifList(modifList);
                }
        );

        return carList;
    }

    public void addModif(CarModification carModification){
        List<Long> modifIdList = carModification.getModifIdList();
        modifIdList.stream().forEach( mod ->
                carModificationRepository.addModif(carModification.getCarId(), mod)
        );
    }

    public void deleteModif(DeleteModifRequest deleteModifRequest){
        carModificationRepository.deleteModif(deleteModifRequest.getCarId(), deleteModifRequest.getModifId());
    }
}
