package com.example.backend.services;

import com.example.backend.db.DB;
import com.example.backend.dtos.DanceDto;
import com.example.backend.entities.Dance;
import com.example.backend.dtos.telegram.TelegramButton;
import com.example.backend.exceptions.AppException;
import com.example.backend.mappers.DanceMapper;
import com.example.backend.mappers.TelegramMapper;
import com.example.backend.repositories.DanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DanceService {

    private final DanceRepository danceRepository;
    private final DanceMapper danceMapper;

    @Autowired
    private final TelegramMapper telegramMapper;

    private final DB db;

    public List<DanceDto> allDances(){
        List <Dance> all = danceRepository.getDances();
        return danceMapper.toDanceDtos(all);
    }

    public List<TelegramButton> getDanceList() {
        List<DanceDto> danceDtos = db.getDanceList();
        return telegramMapper.toDanceButtons(danceDtos);
    }


    public DanceDto getDance(UUID id) {
        Dance dance = danceRepository.findById(id)
                .orElseThrow(() -> new AppException("Dance not found", HttpStatus.NOT_FOUND));

        return danceMapper.toDanceDto(dance, new ArrayList<>());
    }

    public DanceDto createDance(DanceDto danceDto) {
        Dance dance = danceMapper.toDance(danceDto);
        Dance createdDance = danceRepository.save(dance);
        return danceMapper.toDanceDto(createdDance, new ArrayList<>());
    }

    public DanceDto updateDance(UUID id, DanceDto danceDto) {
        Dance dance = danceRepository.findById(id)
                .orElseThrow(() -> new AppException("Dance not found", HttpStatus.NOT_FOUND));

//        danceMapper.updateDance(dance, danceMapper.toDance(danceDto));

        Dance updatedDance = danceRepository.save(dance);

        return danceMapper.toDanceDto(updatedDance, new ArrayList<>());
    }

    public DanceDto deleteDance(UUID id) {
        Dance dance = danceRepository.findById(id)
                .orElseThrow(() -> new AppException("Dance not found", HttpStatus.NOT_FOUND));

        danceRepository.deleteById(id);

        return  danceMapper.toDanceDto(dance, new ArrayList<>());

    }
}

