package com.example.backend.repositories;

import com.example.backend.entities.Dance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DanceRepository extends JpaRepository<Dance, Long> {
}

