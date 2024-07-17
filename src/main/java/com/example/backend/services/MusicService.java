package com.example.backend.services;

import com.example.backend.dtos.in.MusicInDto;
import com.example.backend.dtos.out.MusicOutDto;
import com.example.backend.entities.Dance;
import com.example.backend.entities.Music;
import com.example.backend.exceptions.AppException;
import com.example.backend.mappers.MusicMapper;
import com.example.backend.repositories.DanceRepository;
import com.example.backend.repositories.MusicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MusicService {

    @Autowired
    private final MusicRepository repository;
    @Autowired
    private final DanceRepository danceRepository;


    @Autowired
    private final MusicMapper mapper;

    public List<MusicOutDto> all(){
        List <Music> all = repository.findAll();
        return mapper.toOutDtoList(all);
        
    }

    public MusicOutDto get(UUID id) {

        Music entity = repository.findById(id)
                .orElseThrow(() -> new AppException("Music not found", HttpStatus.NOT_FOUND));

            return mapper.toOutDto(entity);
    }

    public MusicOutDto update(UUID id, MusicInDto dto) {

        Music entity = repository.findById(id)
                .orElseThrow(() -> new AppException("Music not found", HttpStatus.NOT_FOUND));

        entity.setName(dto.getName());
        entity.setPath(dto.getPath());
        entity.setRating(dto.getRating());

        if (dto.getDanceList() != null) {
            Set<Dance> currentDances = entity.getDanceList();
            Set<Dance> newDances = new HashSet<>();

            for (UUID danceId : dto.getDanceList()) {
                Dance dance = danceRepository.findById(danceId)
                        .orElseThrow(() -> new AppException("Dance not found", HttpStatus.NOT_FOUND));
                newDances.add(dance);
            }

            // Remove old dances
            if(currentDances != null && !currentDances.isEmpty()) {
                for (Dance dance : currentDances) {
                    if (!newDances.contains(dance)) {
                        dance.getMusic_list().remove(entity);
                    }
                }
            }

            // Add new dances
            if(!newDances.isEmpty()) {
                for (Dance dance : newDances) {
                    dance.getMusic_list().add(entity);
                }
            }

            entity.setDanceList(newDances);
        } else {
            // Clear all dances if dto.getDanceList() is null
            for (Dance dance : entity.getDanceList()) {
                dance.getMusic_list().remove(entity);
            }
            entity.setDanceList(Collections.emptySet());
        }

        return mapper.toOutDto(repository.save(entity));
    }


    public MusicOutDto create(MusicInDto dto) {

        Music entity = Music.builder()
                .name(dto.getName())
                .rating(dto.getRating())
                .path(dto.getPath())
                .build();


        if (dto.getDanceList() != null && !dto.getDanceList().isEmpty()){

            Set<Dance> dances = new HashSet<>();

            for (UUID danceId : dto.getDanceList()) {
                Dance dance = danceRepository.findById(danceId)
                        .orElseThrow(() -> new AppException("Dance not found", HttpStatus.NOT_FOUND));
                dances.add(dance);
                dance.getMusic_list().add(entity); // Add the music entity to the dance
            }
            entity.setDanceList(dances);
        }

        return mapper.toOutDto(repository.save(entity));
    }
}

