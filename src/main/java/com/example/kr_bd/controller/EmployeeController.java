package com.example.kr_bd.controller;

import lombok.RequiredArgsConstructor;
import com.example.kr_bd.model.AuthRequest;
import com.example.kr_bd.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/")
    public String user(Model model) {
        System.out.println("Employee auth");
        model.addAttribute("authRequest", new AuthRequest());

        return "login-employee";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("authRequest") AuthRequest user, Model model){
        log.info("Method: /employee/login, Model: {}", user);

        if(employeeService.authUser(user))
            return "redirect:/employee/menu_employee";
        else
            return "redirect:/employee/login-employee";
    }

    @GetMapping("/menu_employee")
    public String menuEmployee(){
        return "menu_employee";
    }


}
