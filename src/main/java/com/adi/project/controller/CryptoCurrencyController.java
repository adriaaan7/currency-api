package com.adi.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CryptoCurrencyController {

    @GetMapping("/")
    public String getMainPage(){
        return "index";
    }
}
