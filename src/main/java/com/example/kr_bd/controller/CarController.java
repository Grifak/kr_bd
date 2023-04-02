package com.example.kr_bd.controller;

import lombok.RequiredArgsConstructor;
import com.example.kr_bd.model.Car;
import com.example.kr_bd.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/car")
public class CarController {
    private final CarService carService;

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("car", new Car());

        return "create-car";
    }

    @PostMapping("/create")
    public String createCar(Model model, @ModelAttribute Car car){
        carService.create(car);

        return "redirect:/user/menu";
    }

    @GetMapping("/list")
    public String carList(Model model){
        model.addAttribute("carList", carService.getAllCar(1L));

        return "car-list";
    }
}
