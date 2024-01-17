package com.example.mymusic.mymusic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {
    @GetMapping("/")
    public String examplePage() {
        return "index";
    }
}
