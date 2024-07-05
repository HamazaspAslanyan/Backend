package com.example.backend.repositories;

import com.example.backend.dtos.in.DanceInDto;
import com.example.backend.entities.Dance;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DanceRepository extends JpaRepository<Dance, UUID> {

    @Query(value = "SELECT * FROM dance", nativeQuery = true)
    List<Dance> getDances();

    @EntityGraph(attributePaths = {"genre_list", "state_list"})
    Optional<Dance> findById(@Param("id") UUID id);

}

