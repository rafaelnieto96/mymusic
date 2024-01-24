package com.example.mymusic.mymusic.services;

import com.example.mymusic.mymusic.models.Audio;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AudioService {

    public void saveFile(MultipartFile file) throws IOException;
    public Audio updateAudio(Long id, Audio audio);
    public Audio getAudioById(Long id);
    public List<Audio> getAllAudios();
    public void deleteAudio(Long id);
}
