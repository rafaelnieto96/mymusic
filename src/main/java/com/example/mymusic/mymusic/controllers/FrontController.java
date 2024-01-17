package com.example.mymusic.mymusic.controllers;

import com.example.mymusic.mymusic.models.Audio;
import com.example.mymusic.mymusic.services.AudioServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class FrontController {

    private AudioServiceImpl audioService;

    public FrontController(AudioServiceImpl audioService) {
        this.audioService = audioService;
    }

    @GetMapping("/")
    public String myMusic(Model model) {
        List<Audio> audioList = audioService.getAllAudios();
        model.addAttribute("audioList", audioList);
        return "index";
    }
}
