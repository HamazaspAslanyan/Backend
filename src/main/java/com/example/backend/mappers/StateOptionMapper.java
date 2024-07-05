package com.example.backend.mappers;

import com.example.backend.dtos.options.StateOptionDto;
import com.example.backend.entities.State;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public class StateOptionMapper {

    public State toState(StateOptionDto optionDto){
        return State.builder()
                .name(optionDto.getName())
                .build();
    }

    public StateOptionDto toStateDto(State genre){
        return StateOptionDto.builder()
                .name(genre.getName())
                .id(genre.getId())
                .build();
    }

    public List<StateOptionDto> toStateDtoList(List<State> all) {
        List<StateOptionDto> genreOptionDtoList = new ArrayList<>();
        for(State genreEntity : all) {
            genreOptionDtoList.add(StateOptionDto.builder()
                    .id(genreEntity.getId())
                    .name(genreEntity.getName())
                    .build());
        }
        return genreOptionDtoList;
    }

}

