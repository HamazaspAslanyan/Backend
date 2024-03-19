package com.example.backend.mappers;

import com.example.backend.dtos.DanceDto;
import com.example.backend.entities.Dance;
import com.example.backend.entities.TelegramFile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public class DanceMapper {

    Dance toDance(DanceDto danceDto){
        return Dance.builder()
                .name(danceDto.getName())
                .build();
    }

    DanceDto toDanceDto(Dance dance, List<TelegramFile> musics){
        return DanceDto.builder()
                .description("mama")
                .musicList(musics)
                .build();
    }

    @Mapping(target = "id", ignore = true)
    List<DanceDto> toDanceDtos(List<Dance> dances);

    void updateDance(@MappingTarget Dance target, Dance source);
}

