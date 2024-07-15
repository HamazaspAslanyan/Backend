package com.example.backend.mappers;

import com.example.backend.dtos.OptionDto;
import com.example.backend.dtos.in.OptionInDto;
import com.example.backend.dtos.out.OptionOutDto;
import com.example.backend.entities.State;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public class StateOptionMapper {


    public State toEntity(OptionInDto optionDto){
        return State.builder()
                .id(optionDto.getId())
                .name(optionDto.getName())
                .build();
    }

    public OptionOutDto toDto(State entity){
        return OptionOutDto.builder()
                .name(entity.getName())
                .id(entity.getId())
                .build();
    }

    public List<OptionOutDto> toDtoList(List<State> all) {
        List<OptionOutDto> optionDtoList = new ArrayList<>();
        for(State entity : all) {
            optionDtoList.add(this.toDto(entity));
        }
        return optionDtoList;
    }

}

