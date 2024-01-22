package com.example.mymusic.mymusic.config;

import com.example.mymusic.mymusic.models.Audio;
import com.example.mymusic.mymusic.repositories.AudioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AudioRepository audioRepository;

    public DataInitializer(AudioRepository audioRepository) {
        this.audioRepository = audioRepository;
    }

    @Override
    public void run(String... args) {
        initializeData();
    }

    private void initializeData() {
        Audio audio1 = new Audio(1L, "Breaking benjamin - Breath", 3.45, "https://example.com/audio1");
        Audio audio2 = new Audio(2L, "Linkin Park - In the End", 4.45, "https://example.com/audio2");
        Audio audio3 = new Audio(3L, "Linkin Park - Numb", 5.45, "https://example.com/audio3");

        audioRepository.save(audio1);
        audioRepository.save(audio2);
        audioRepository.save(audio3);
    }
}
