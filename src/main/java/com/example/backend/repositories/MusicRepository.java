package com.example.backend.repositories;

import com.example.backend.entities.Music;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MusicRepository extends JpaRepository<Music, UUID> {

    @EntityGraph(attributePaths = {"name"})
    Optional<Music> findById(@Param("id") UUID id);


    @EntityGraph(attributePaths = {"name"})
    List<Music> findAll();

}

