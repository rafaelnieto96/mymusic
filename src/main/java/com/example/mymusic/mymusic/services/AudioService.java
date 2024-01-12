package com.example.mymusic.mymusic.services;

public interface AudioService {
    public Audio getAudiobyId(Long id);
    public Audio getAudiobyName(Long id);
    public void saveAudio(Long id);
    public void deleteAudio(Long id);
    public void updateAudio(Long id, Audio audio);
}
