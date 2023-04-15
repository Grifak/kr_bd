package com.example.kr_bd.controller;

import com.example.kr_bd.model.AuthRequest;
import com.example.kr_bd.model.RegistrationRequest;
import com.example.kr_bd.model.Sorting;
import com.example.kr_bd.service.UsersService;
import lombok.RequiredArgsConstructor;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/login")
    public String user(Model model) {
        model.addAttribute("authRequest", new AuthRequest());

        return "login";
    }


    @PostMapping("/login")
    public String login(
            @ModelAttribute("authRequest") AuthRequest user,
            HttpSession httpSession
    ){
        log.info("Method: /login, Model: {}", user);

        Long userId = usersService.authUser(user);
        httpSession.setAttribute("userId", userId);
        log.info("User userId = {}", userId);

        return "redirect:menu";
    }

    @GetMapping("/menu")
    public String menu(
            Model model
    ){
        model.addAttribute("sorting", new Sorting());

        return "menu";
    }

    @GetMapping("/register")
    public String registrationForm(
            Model model
    ){
        model.addAttribute("regRequest", new RegistrationRequest());

        return "registration";
    }

    @PostMapping("register")
    public String registration(
            @ModelAttribute("regUser") RegistrationRequest registrationRequest
    ){
        usersService.registration(registrationRequest);

        return "redirect:/user/";
    }

    @GetMapping("/")
    public String unAuthMenu(){
        return "un-auth-menu";
    }
}
