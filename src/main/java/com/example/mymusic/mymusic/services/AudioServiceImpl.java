package com.example.mymusic.mymusic.services;

import com.example.mymusic.mymusic.models.Audio;
import com.example.mymusic.mymusic.repositories.AudioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AudioServiceImpl implements AudioService {

    private final AudioRepository audioRepository;

    public AudioServiceImpl(AudioRepository audioRepository) {
        this.audioRepository = audioRepository;
    }

    @Override
    public Audio updateAudio(Long id, Audio updatedAudio) {
        Audio existingAudio = audioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Audio not found"));

        existingAudio.setName(updatedAudio.getName());
        existingAudio.setDuration(updatedAudio.getDuration());

        return audioRepository.save(existingAudio);
    }

    @Override
    public Audio getAudioById(Long id) {
        return audioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Audio not found"));
    }

    @Override
    public Audio getAudioByName(String name) {
        return audioRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Audio not found"));
    }

    @Override
    public List<Audio> getAllAudios() {
        return audioRepository.findAll();
    }

    @Override
    public void deleteAudio(Long id) {
        Audio existingAudio = audioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Audio not found"));
        audioRepository.delete(existingAudio);
    }

    @Override
    public void saveFile(MultipartFile file) {
        Audio audio = new Audio();
        audio.setName(file.getOriginalFilename());
        audio.setDuration(0.0);
        audio.setFileUrl("test");

        audioRepository.save(audio);

        System.out.println("File and audio information saved successfully");
    }

}
