package com.example.mymusic.mymusic.controllers;

import com.example.mymusic.mymusic.services.AudioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/audio")
public class AudioController {

    private final AudioService audioService;

    public AudioController(AudioService audioService) {
        this.audioService = audioService;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAudio(@PathVariable Long id) {
        audioService.deleteAudio(id);
        return new ResponseEntity<>("Audio deleted succesfully", HttpStatus.OK);
    }

    @PostMapping("/upload")
    public String saveFile(@RequestParam("file") MultipartFile file) {
        try {
            audioService.saveFile(file);
            return "redirect:/";
        } catch (IOException e) {
            return "Error uploading the file: " + e.getMessage();
        }
    }

}
