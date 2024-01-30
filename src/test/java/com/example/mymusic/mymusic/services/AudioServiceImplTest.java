package com.example.mymusic.mymusic.services;

import com.example.mymusic.mymusic.models.Audio;
import com.example.mymusic.mymusic.repositories.AudioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AudioServiceImplTest {

    @Mock
    private AudioRepository audioRepository;

    private AudioServiceImpl audioService;

    private List<Audio> audioList;
    private Audio audio1;
    private Audio audio2;
    private Audio audio3;

    @BeforeEach
    void setUp() {
        audioService = new AudioServiceImpl(audioRepository);

        audio1 = new Audio(1L, "Dj Quads - Soul", "/uploads/1.mp3", Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        audio2 = new Audio(2L, "JPB - High", "/uploads/2.mp3", Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        audio3 = new Audio(3L, "MGR 7TH - Cloud Nining", "/uploads/3.mp3", Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

        audioList = new ArrayList<>();
        audioList.add(audio1);
        audioList.add(audio2);
        audioList.add(audio3);
    }

    @Test
    public void testGetAllAudios() {
        Mockito.when(audioRepository.findAll()).thenReturn(audioList);

        List<Audio> actualAudios = audioService.getAllAudios();

        assertEquals(audioList, actualAudios);
        Mockito.verify(audioRepository, Mockito.times(1)).findAll();
    }

    @Test
    @Disabled
    public void testDeleteAudio() {
        Long audioId = 1L;
        Mockito.when(audioRepository.findById(audioId)).thenReturn(Optional.of(audio1));
        Mockito.doNothing().when(audioRepository).delete(audio1);

        audioService.deleteAudio(audioId);

        Mockito.verify(audioRepository, Mockito.times(1)).delete(audio1);
    }

    @Test
    public void testDeleteAudioNotFound() {
        Long audioId = 1L;
        Mockito.when(audioRepository.findById(audioId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> audioService.deleteAudio(audioId));
    }

    @Test
    @Disabled
    public void testSaveFile() throws IOException {
        Mockito.when(audioRepository.save(Mockito.any(Audio.class))).thenReturn(audio1);

        byte[] fileBytes = "Test content".getBytes();
        MultipartFile mockFile = new MockMultipartFile("test.mp3", "test.mp3", "audio/mp3", fileBytes);

        audioService.saveFile(mockFile);

        Mockito.verify(audioRepository, Mockito.times(2)).save(Mockito.any(Audio.class));
    }
}
