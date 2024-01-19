package com.example.mymusic.mymusic.services;

import com.example.mymusic.mymusic.models.Audio;
import com.example.mymusic.mymusic.repositories.AudioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

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
    public String saveFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String uniqueFilename = generateUniqueFilename(originalFilename);
        String fileUrl = "/path/to/your/audio/files/" + uniqueFilename;

        try {
            file.transferTo(new java.io.File(fileUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileUrl;
    }

    private String generateUniqueFilename(String originalFilename) {
        return UUID.randomUUID().toString() + "_" + originalFilename;
    }


}
