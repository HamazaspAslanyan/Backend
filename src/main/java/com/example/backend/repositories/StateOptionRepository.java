package com.example.backend.repositories;

import com.example.backend.entities.Genre;
import com.example.backend.entities.State;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StateOptionRepository extends JpaRepository<State, UUID> {

    @EntityGraph(attributePaths = {"name"})
    Optional<State> findById(@Param("id") UUID id);


    @EntityGraph(attributePaths = {"name"})
    List<State> findAll();

}

