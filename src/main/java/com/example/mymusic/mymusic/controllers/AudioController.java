package com.example.mymusic.mymusic.controllers;

import com.example.mymusic.mymusic.exceptions.ErrorResponse;
import com.example.mymusic.mymusic.models.Audio;
import com.example.mymusic.mymusic.services.AudioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/audio")
public class AudioController {

    private final AudioService audioService;

    public AudioController(AudioService audioService) {
        this.audioService = audioService;
    }


//    @PostMapping("/create")
//    public ResponseEntity<?> saveAudio(
//            @RequestPart("file") MultipartFile file,
//            @RequestPart("audio") Audio audio
//    ) {
//        // Save the file and get the file URL
//        String fileUrl = audioService.saveFile(file);
//
//        // Set the file URL in the audio object
//        audio.setFileUrl(fileUrl);
//
//        // Save the audio details
//        Audio savedAudio = audioService.saveAudio(audio);
//
//        return new ResponseEntity<>(savedAudio, HttpStatus.CREATED);
//    }

    @PostMapping("/create")
    public ResponseEntity<?> saveAudio(@RequestBody Audio audio) {
        return new ResponseEntity<>(audioService.saveAudio(audio), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAudio(@PathVariable long id, @RequestBody Audio updatedAudio) {
        Audio updated = audioService.updateAudio(id, updatedAudio);
        if (updated != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updated);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("Audio not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getAudioById(@PathVariable long id) {
        return new ResponseEntity<>(audioService.getAudioById(id), HttpStatus.OK);
    }

    @GetMapping("/get/{name}")
    public ResponseEntity<?> getAudioByName(@PathVariable String name) {
        return new ResponseEntity<>(audioService.getAudioByName(name), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAudio(@PathVariable Long id) {
        audioService.deleteAudio(id);
        return new ResponseEntity<>("Audio deleted succesfully", HttpStatus.OK);
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            System.out.println("File uploaded: " + file.getOriginalFilename());
        } else {
            System.out.println("No file uploaded");
        }

        return "redirect:/";
    }

}
