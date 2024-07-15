package com.example.backend.repositories;

import com.example.backend.entities.Dance;
import com.example.backend.entities.Genre;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GenreOptionRepository extends JpaRepository<Genre, UUID> {

    @EntityGraph(attributePaths = {"name"})
    Optional<Genre> findById(@Param("id") UUID id);


    @EntityGraph(attributePaths = {"name"})
    List<Genre> findAll();

}

