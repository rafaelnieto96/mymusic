package com.example.mymusic.mymusic.repositories;

import com.example.mymusic.mymusic.models.Audio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudioRepository extends JpaRepository<Audio, Long> {
}
