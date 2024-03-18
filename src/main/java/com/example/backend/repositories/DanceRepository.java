package com.example.backend.repositories;

import com.example.backend.dtos.DanceDto;
import com.example.backend.entities.Dance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DanceRepository extends JpaRepository<Dance, Long> {

    @Query(value = "SELECT * FROM dance", nativeQuery = true)
    List<Dance> getDances();
}

