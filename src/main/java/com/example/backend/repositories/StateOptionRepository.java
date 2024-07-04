package com.example.backend.repositories;

import com.example.backend.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface StateOptionRepository extends JpaRepository<State, UUID> {

    @Query(value = "SELECT * FROM state", nativeQuery = true)
    List<State> getStates();

}

