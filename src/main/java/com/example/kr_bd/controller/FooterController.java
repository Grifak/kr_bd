package com.example.kr_bd.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class FooterController {

    @GetMapping("/info")
    public String getInfo(){
        return "info";
    }

    @GetMapping("/contact")
    public String getContract(){
        return "contact";
    }
}
