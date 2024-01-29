package com.example.mymusic.mymusic.services;

import com.example.mymusic.mymusic.models.Audio;
import com.example.mymusic.mymusic.repositories.AudioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AudioServiceImpl implements AudioService {

    private static final String UPLOAD_FOLDER = "uploads/";

    private final AudioRepository audioRepository;

    public AudioServiceImpl(AudioRepository audioRepository) {
        this.audioRepository = audioRepository;
    }

    @Override
    public List<Audio> getAllAudios() {
        return audioRepository.findAll();
    }

    @Override
    public void deleteAudio(Long id) {
        Audio existingAudio = audioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Audio not found"));

        deleteFromFolder(existingAudio);

        audioRepository.delete(existingAudio);
    }

    private void deleteFromFolder(Audio audio) {
        String fileUrl = audio.getFileUrl();
        Long id = audio.getId();

        if (fileUrl != null && !fileUrl.isEmpty()) {
            Path filePath = Paths.get(UPLOAD_FOLDER + id + "." + "mp3");
            try {
                Files.deleteIfExists(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveFile(MultipartFile file) throws IOException {
        Audio audio = new Audio();
        audio.setName("");
        audio.setDuration(0.0);
        audioRepository.save(audio);

        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        Long id = audio.getId();

        String path = UPLOAD_FOLDER + id + "." + extension;
        audio.setName(file.getOriginalFilename());
        audio.setFileUrl(path);

        audio.setUploadDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

        audioRepository.save(audio);
        byte[] bytes = file.getBytes();

        Path location = Paths.get(UPLOAD_FOLDER + id + "." + extension);
        Files.write(location, bytes);
    }

}
