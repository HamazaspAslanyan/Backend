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

//    @Query(value = """
//    SELECT d.id as id, d.name as name,
//           genre.id , genre.name,
//           state.id , state.name,
//           d.description as description
//    FROM public.dance d
//    LEFT JOIN public.dance_genre dg ON d.id = dg.dance_id
//    LEFT JOIN public.genre genre ON dg.genre_id = genre.id
//    LEFT JOIN public.dance_state ds ON d.id = ds.dance_id
//    LEFT JOIN public.state state ON ds.state_id = state.id
//    WHERE d.id = :id
//    """, nativeQuery = true)
////    List<Object[]> findByIdWithGenresAndStates(@Param("id") UUID id);
//    Optional<Dance> findByIdWithGenresAndStates(@Param("id") UUID id);

    @EntityGraph(attributePaths = {"genre_list", "state_list"})
    Optional<Dance> findById(@Param("id") UUID id);

}

