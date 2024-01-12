package com.example.mymusic.mymusic.services;

import com.example.mymusic.mymusic.models.Audio;

public interface AudioService {
    public Audio getAudiobyId(Long id);
    public Audio getAudiobyName(Long id);
    public Audio saveAudio(Long id);
    public void deleteAudio(Long id);
    public Audio updateAudio(Long id, Audio audio);
}
