package com.example.mymusic.mymusic.repositories;

import com.example.mymusic.mymusic.models.Audio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AudioRepository extends JpaRepository<Audio, Long> {
    Optional<Audio> findByName(String name);
}
