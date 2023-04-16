package com.example.kr_bd.controller;

import com.example.kr_bd.model.Car;
import com.example.kr_bd.model.DeleteModifRequest;
import com.example.kr_bd.model.Sorting;
import com.example.kr_bd.service.CarService;
import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
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
    public String createCar(HttpSession httpSession, @ModelAttribute Car car){
        Long userId = (Long) httpSession.getAttribute("userId");
        carService.create(car, userId);

        return "redirect:/user/menu";
    }

    @GetMapping("/list")
    public String carList(
            @ModelAttribute("sorting") Sorting sorting,
            Model model,
            HttpSession httpSession
    ){
        log.info("Sorting: acs = {}", sorting.getAsc());
        Boolean asc = sorting.getAsc() == null || sorting.getAsc();

        Long userId = (Long) httpSession.getAttribute("userId");
        List<Car> carList = carService.getAllCar(userId, asc);

        model.addAttribute("carList", carList);
        model.addAttribute("deleteRequest", new DeleteModifRequest());

        return "car-list";
    }
}
