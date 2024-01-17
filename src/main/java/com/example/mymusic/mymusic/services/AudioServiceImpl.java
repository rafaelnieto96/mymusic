package com.example.mymusic.mymusic.services;

import com.example.mymusic.mymusic.models.Audio;
import com.example.mymusic.mymusic.repositories.AudioRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AudioServiceImpl implements AudioService {

    private final AudioRepository audioRepository;

    public AudioServiceImpl(AudioRepository audioRepository) {
        this.audioRepository = audioRepository;
    }

    @Override
    public Audio saveAudio(Audio audio) {
        return audioRepository.save(audio);
    }

    @Override
    public Audio updateAudio(Long id, Audio updatedAudio) {
        Audio existingAudio = audioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Audio not found"));

        existingAudio.setName(updatedAudio.getName());
        existingAudio.setSize(updatedAudio.getSize());

        return audioRepository.save(existingAudio);
    }

    @Override
    public Audio getAudiobyId(Long id) {
        return audioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Audio not found"));
    }

    @Override
    public Audio getAudiobyName(String name) {
        return audioRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Audio not found"));
    }

    @Override
    public void deleteAudio(Long id) {
        Audio existingAudio = audioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Audio not found"));
        audioRepository.delete(existingAudio);
    }


}
