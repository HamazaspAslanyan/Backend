package com.example.backend.repositories;

import com.example.backend.entities.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OptionRepository extends JpaRepository<Option, UUID> {

    /***
    SELECT d.id AS id,
    t.am AS name
    FROM dance d
    JOIN translation t ON d.name_translation_id = t.id */
    @Query(value = "SELECT d.id, d.name FROM dance_name_am d", nativeQuery = true)
    List<Option> getDanceNameListAm();

//TODO view for video obj(id, name)
    @Query(value = "SELECT d.id, d.name FROM dance_name_am d", nativeQuery = true)
    List<Option> getVideoNameListAm();

}

