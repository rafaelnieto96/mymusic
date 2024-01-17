package com.example.mymusic.mymusic.services;

import com.example.mymusic.mymusic.models.Audio;

import java.util.List;

public interface AudioService {

    public Audio saveAudio(Audio audio);
    public Audio updateAudio(Long id, Audio audio);
    public Audio getAudiobyId(Long id);
    public Audio getAudiobyName(String name);
    public List<Audio> getAllAudios();
    public void deleteAudio(Long id);
}
