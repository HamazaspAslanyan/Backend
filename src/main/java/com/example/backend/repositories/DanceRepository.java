package com.example.backend.repositories;

import com.example.backend.dtos.in.DanceInDto;
import com.example.backend.dtos.out.DanceNameOutDto;
import com.example.backend.entities.Dance;
import com.example.backend.entities.Music;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DanceRepository extends JpaRepository<Dance, UUID> {

    @Query(value = "SELECT d.danceId, d.name FROM dance_name d", nativeQuery = true)
    List<DanceNameOutDto> getDanceNameList();

    @EntityGraph(attributePaths = {"name", "description", "descriptionExtra", "genre_list", "state_list", "music_list"})
    Optional<Dance> findById(@Param("id") UUID id);

//    @Query(value = "SELECT * FROM Dance WHERE similarity(Dance.name, :name) > 0.3 ORDER BY similarity(Dance.name, :name) DESC", nativeQuery = true)
//    List<Dance> searchByName(@Param("name") String name);

//    @Query(value = """
//        SELECT new com.example.backend.dtos.out.DanceNameOutDto(d.id, dt.name)
//        FROM Dance d
//        JOIN d.translations dt
//        WHERE dt.languageCode = :languageCode
//    """, nativeQuery = true)
//    List<DanceNameOutDto> getNameList(@Param("name") String languageCode);

}

