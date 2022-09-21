package com.example.VanRentalApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateController {

    @GetMapping("login")
    public String getLoginView(){
        return "login"; //loads the file login.html within the template's folder. the folder name must match exactly to returned string
    }

    @GetMapping("van")
    public String getVanView(){
        return "van"; //loads the file login.html within the template's folder. the folder name must match exactly to returned string
    }
}
