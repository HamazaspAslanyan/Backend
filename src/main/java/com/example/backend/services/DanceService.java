package com.example.backend.services;

import com.example.backend.dtos.DanceOptionDto;
import com.example.backend.dtos.in.DanceInDto;
import com.example.backend.dtos.out.DanceOutDto;
import com.example.backend.entities.Dance;
import com.example.backend.dtos.telegram.TelegramButton;
import com.example.backend.entities.Genre;
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

    public List<DanceOutDto> allDances(){
        List <Dance> all = danceRepository.findAll();
        return danceMapper.toDanceOutDtoList(all);
    }

    public DanceOutDto getDance(UUID id) {
        Dance dance = danceRepository.findById(id)
                .orElseThrow(() -> new AppException("Dance not found", HttpStatus.NOT_FOUND));

        return danceMapper.toDanceOutDto(dance);
    }

    public DanceOutDto updateDance(UUID id, DanceInDto dto) {

        return null;
    }

    public DanceOutDto createDance(DanceInDto dto) {

        Dance createdDance = danceRepository.save(danceMapper.toDanceEntity(dto));
        return danceMapper.toDanceOutDto(createdDance);
    }

    /** OPTIONS */
    public List<DanceOptionDto> allOptions(){
        List <Dance> all = danceRepository.findAll();
        return danceMapper.toDanceOptionDtoList(all);
    }

    public DanceOptionDto getOption(UUID id) {
        Dance dance = danceRepository.findById(id)
                .orElseThrow(() -> new AppException("Dance not found", HttpStatus.NOT_FOUND));

        return danceMapper.toDanceOptionDto(dance, new ArrayList<>());
    }

    public DanceOptionDto createOption(DanceOptionDto danceOptionDto) {
        Dance dance = danceMapper.toDanceEntity(danceOptionDto);
        Dance createdDance = danceRepository.save(dance);
        return danceMapper.toDanceOptionDto(createdDance, new ArrayList<>());
    }

    public DanceOptionDto updateOption(UUID id, DanceOptionDto danceOptionDto) {
        Dance dance = danceRepository.findById(id)
                .orElseThrow(() -> new AppException("Dance not found", HttpStatus.NOT_FOUND));

//        danceMapper.updateDance(dance, danceMapper.toDance(danceDto));

        Dance updatedDance = danceRepository.save(dance);

        return danceMapper.toDanceOptionDto(updatedDance, new ArrayList<>());
    }

    public DanceOptionDto deleteOption(UUID id) {
        Dance dance = danceRepository.findById(id)
                .orElseThrow(() -> new AppException("Dance not found", HttpStatus.NOT_FOUND));

        danceRepository.deleteById(id);

        return  danceMapper.toDanceOptionDto(dance, new ArrayList<>());

    }

    /** TELEGRAM*/
    public List<TelegramButton> getTelegramDanceList() {

        List <Dance> all = danceRepository.findAll();
        List<DanceOutDto> danceOutDtos =  danceMapper.toDanceOutDtoList(all);
        return telegramMapper.toDanceButtons(danceOutDtos);
    }
}

