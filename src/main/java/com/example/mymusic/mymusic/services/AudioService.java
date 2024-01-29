package com.example.mymusic.mymusic.services;

import com.example.mymusic.mymusic.models.Audio;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AudioService {

    void saveFile(MultipartFile file) throws IOException;

    List<Audio> getAllAudios();

    void deleteAudio(Long id);
}
