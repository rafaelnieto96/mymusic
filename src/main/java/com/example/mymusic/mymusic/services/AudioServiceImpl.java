package com.example.mymusic.mymusic.services;

import com.example.mymusic.mymusic.models.Audio;
import com.example.mymusic.mymusic.repositories.AudioRepository;

import java.util.NoSuchElementException;

public class AudioServiceImpl implements AudioService{

    private final AudioRepository audioRepository;

    public AudioServiceImpl(AudioRepository audioRepository) {
        this.audioRepository = audioRepository;
    }

    public Audio getAudiobyId(Long id){

    };
    public Audio getAudiobyName(Long id){};
    public Audio saveAudio(Long id){};
    public void deleteAudio(Long id){};

    @Override
    public Audio updateAudio(Long id, Audio updatedAudio){
        Audio existingAudio = audioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Audio not found"));

        existingAudio.setName(updatedAudio.getName());
        existingAudio.setSize(updatedAudio.getSize());

        return audioRepository.save(existingAudio);
    };
}
