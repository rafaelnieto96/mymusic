package com.example.mymusic.mymusic.repositories;

import com.example.mymusic.mymusic.models.Audio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AudioRepository extends JpaRepository<Audio, Long> {
    Optional<Audio> findByName(String name);
}
