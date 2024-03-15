package com.example.backend.mappers;

import com.example.backend.dtos.DanceDto;
import com.example.backend.entities.Dance;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DanceMapper {

    Dance toDance(DanceDto danceDto);

    DanceDto toDanceDto(Dance dance);

    List<DanceDto> toDanceDtos(List<Dance> dances);

    void updateDance(@MappingTarget Dance target, Dance source);
}

