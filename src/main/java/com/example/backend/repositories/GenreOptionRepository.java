package com.example.backend.repositories;

import com.example.backend.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface GenreOptionRepository extends JpaRepository<Genre, UUID> {

    @Query(value = "SELECT * FROM genre", nativeQuery = true)
    List<Genre> getGenres();

}

