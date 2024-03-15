package com.example.backend.services;

import com.example.backend.dtos.DanceDto;
import com.example.backend.entities.Dance;
import com.example.backend.exceptions.AppException;
import com.example.backend.mappers.DanceMapper;
import com.example.backend.repositories.DanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DanceService {

    private final DanceRepository danceRepository;
    private final DanceMapper danceMapper;

    public List<DanceDto> allDances(){
        List <Dance> all = danceRepository.findAll();
        return danceMapper.toDanceDtos(all);
    }

    public DanceDto getDance(Long id) {
        Dance dance = danceRepository.findById(id)
                .orElseThrow(() -> new AppException("Dance not found", HttpStatus.NOT_FOUND));
        return danceMapper.toDanceDto(dance);
    }

    public DanceDto createDance(DanceDto danceDto) {
        Dance dance = danceMapper.toDance(danceDto);
        Dance createdDance = danceRepository.save(dance);
        return danceMapper.toDanceDto(createdDance);
    }

    public DanceDto updateDance(Long id, DanceDto danceDto) {
        Dance dance = danceRepository.findById(id)
                .orElseThrow(() -> new AppException("Dance not found", HttpStatus.NOT_FOUND));

        danceMapper.updateDance(dance, danceMapper.toDance(danceDto));

        Dance updatedDance = danceRepository.save(dance);

        return danceMapper.toDanceDto(updatedDance);
    }

    public DanceDto deleteDance(Long id) {
        Dance dance = danceRepository.findById(id)
                .orElseThrow(() -> new AppException("Dance not found", HttpStatus.NOT_FOUND));

        danceRepository.deleteById(id);

        return  danceMapper.toDanceDto(dance);

    }
}

