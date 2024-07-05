package com.example.backend.services;

import com.example.backend.dtos.options.StateOptionDto;
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


    public List<StateOptionDto> allOptions(){
        List <State> all = stateOptionRepository.getStates();
        return stateOptionMapper.toStateDtoList(all);
    }

    public StateOptionDto getOption(UUID id) {
        State state = stateOptionRepository.findById(id)
                .orElseThrow(() -> new AppException("State not found", HttpStatus.NOT_FOUND));

        return stateOptionMapper.toStateDto(state);
    }

    public StateOptionDto createOption(StateOptionDto optionDto) {
        State state = stateOptionMapper.toState(optionDto);
        State createdState = stateOptionRepository.save(state);
        return stateOptionMapper.toStateDto(createdState);
    }

    public StateOptionDto updateOption(UUID id, StateOptionDto optionDto) {
        State state = stateOptionRepository.findById(id)
                .orElseThrow(() -> new AppException("State not found", HttpStatus.NOT_FOUND));
        State updatedState = stateOptionRepository.save(stateOptionMapper.toState(optionDto));

        return stateOptionMapper.toStateDto(updatedState);
    }

    public StateOptionDto deleteOption(UUID id) {
        State state = stateOptionRepository.findById(id)
                .orElseThrow(() -> new AppException("State not found", HttpStatus.NOT_FOUND));
        stateOptionRepository.deleteById(id);

        return  stateOptionMapper.toStateDto(state);
    }
}

