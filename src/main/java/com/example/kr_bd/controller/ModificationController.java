package com.example.kr_bd.controller;

import lombok.RequiredArgsConstructor;
import com.example.kr_bd.model.Car;
import com.example.kr_bd.model.CarModification;
import com.example.kr_bd.model.DeleteModifRequest;
import com.example.kr_bd.model.Modification;
import com.example.kr_bd.model.Sorting;
import com.example.kr_bd.service.CarService;
import com.example.kr_bd.service.ModifService;
import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/modif")
public class ModificationController {
    private final ModifService modifService;
    private final CarService carService;

    @GetMapping("/list")
    public String carList(
            @ModelAttribute("sorting") Sorting sorting,
            Model model
    ){
        log.info("Sorting: acs = {}", sorting.getAsc());
        Boolean asc = sorting.getAsc() == null || sorting.getAsc();

        List<Modification> modifList = modifService.getAllModif(asc);
        model.addAttribute("modifList", modifList);

        return "modif-list";
    }

    @GetMapping("/add")
    public String addModifForm(
            Model model,
            HttpSession httpSession
    ){
        Long userId = (Long) httpSession.getAttribute("userId");
        List<Car> carList = carService.getAllCar(userId, true);
        log.info("Car list = {}", carList);
        List<Modification> modifList = modifService.getAllModif(true);
        log.info("Modif List = {}", modifList);

        model.addAttribute("carList", carList);
        model.addAttribute("modifList", modifList);
        model.addAttribute("carModif", new CarModification());

        return "add_modif";
    }

    @PostMapping("/add")
    public String addModif(
            @ModelAttribute("carModif")CarModification carModification
    ){
        log.info("CarModification = {}", carModification);
        carService.addModif(carModification);

        return "redirect:/user/menu";
    }

    @PostMapping("/delete")
    public String deleteModif(
            @ModelAttribute("deleteRequest") DeleteModifRequest deleteModifRequest,
            Model model
    ){
        log.info("Delete request = {}", deleteModifRequest);
        model.addAttribute("sorting", new Sorting());
        carService.deleteModif(deleteModifRequest);

        return "redirect:/car/list";
    }

    @GetMapping("/photo")
    public String photo(){
        return "modif-photo";
    }
}
