package com.example.kr_bd.controller;

import com.example.kr_bd.exception.KrBdException;
import com.example.kr_bd.model.AuthRequest;
import com.example.kr_bd.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {
    private final UsersService usersService;

    @GetMapping("/")
    public String user(Model model) {
        System.out.println("User Page Requested");
        model.addAttribute("authRequest", new AuthRequest());

        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("authRequest") AuthRequest user, Model model){
        log.info("Method: /login, Model: {}", user);

        if(usersService.authUser(user))
            return "redirect:menu";
        else
            throw new KrBdException("Invalid login or password");
    }

    @GetMapping("/menu")
    public String menu(){
        return "menu";
    }
}
