package com.example.backend.services;

import com.example.backend.dtos.OptionDto;
import com.example.backend.dtos.in.OptionInDto;
import com.example.backend.dtos.out.OptionOutDto;
import com.example.backend.entities.State;
import com.example.backend.exceptions.AppException;
import com.example.backend.mappers.StateOptionMapper;
import com.example.backend.repositories.StateOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StateOptionService {

    private final StateOptionRepository stateOptionRepository;
    private final StateOptionMapper stateOptionMapper;

    public List<OptionOutDto> allOptions(){
        List <State> all = stateOptionRepository.findAll();
        return stateOptionMapper.toDtoList(all);
    }

    public OptionOutDto getOption(UUID id) {
        State entity = stateOptionRepository.findById(id)
                .orElseThrow(() -> new AppException("State not found", HttpStatus.NOT_FOUND));
        return stateOptionMapper.toDto(entity);
    }

    public OptionOutDto createOption(OptionInDto optionDto) {
        return stateOptionMapper.toDto(stateOptionRepository.save(stateOptionMapper.toEntity(optionDto)));
    }

    public OptionOutDto updateOption(UUID id, OptionInDto optionDto) {
        return stateOptionMapper.toDto(stateOptionRepository.save(stateOptionMapper.toEntity(optionDto)));
    }

    public OptionOutDto deleteOption(UUID id) {
        State entity = stateOptionRepository.findById(id)
                .orElseThrow(() -> new AppException("State not found", HttpStatus.NOT_FOUND));

        stateOptionRepository.deleteById(id);
        return  stateOptionMapper.toDto(entity);
    }
}

