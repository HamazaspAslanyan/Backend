package com.example.backend.repositories;

import com.example.backend.entities.Dance;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DanceRepository extends JpaRepository<Dance, UUID> {


    @EntityGraph(attributePaths = {
            "name",
            "description",
            "descriptionExtra",
            "genre_list",
            "genre_list.name",
            "state_list",
            "state_list.name",
            "music_list"})
    Optional<Dance> findById(@Param("id") UUID id);

    @EntityGraph(attributePaths = {
            "name",
            "description",
            "descriptionExtra",
            "genre_list",
            "genre_list.name",
            "state_list",
            "state_list.name",
            "music_list"})
    List<Dance> findAll();

//    @Query(value = "SELECT * FROM dance_name WHERE similarity(dance_name.name, :name) > 0.3 ORDER BY similarity(dance_name.name, :name) DESC", nativeQuery = true)
//    List<Dance> searchByName(@Param("name") String name);

}

