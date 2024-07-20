package com.example.backend.repositories;

import com.example.backend.entities.Video;
import com.example.backend.entities.Video;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VideoRepository extends JpaRepository<Video, UUID> {


    //TODO write
    @EntityGraph(attributePaths = {
            "name",
            "description",
            "descriptionExtra",
            "genre_list",
            "genre_list.name",
            "state_list",
            "state_list.name",
            "music_list"})
    Optional<Video> findById(@Param("id") UUID id);


    //TODO write
    @EntityGraph(attributePaths = {
            "name",
            "description",
            "descriptionExtra",
            "genre_list",
            "genre_list.name",
            "state_list",
            "state_list.name",
            "music_list"})
    List<Video> findAll();

}

