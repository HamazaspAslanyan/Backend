package com.example.backend.repositories;

import com.example.backend.entities.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MusicRepository extends JpaRepository<Music, UUID> {

    @Query(value = "SELECT * FROM music", nativeQuery = true)
    List<Music> getMusics();

}

