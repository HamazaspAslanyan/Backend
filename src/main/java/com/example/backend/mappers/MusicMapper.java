package com.example.backend.mappers;

import com.example.backend.dtos.out.MusicOutDto;
import com.example.backend.entities.Music;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public class MusicMapper {




    public MusicOutDto toOutDto(Music entity) {
        return MusicOutDto.builder()
                .id(entity.getId())
                .name(entity.getName().getAm())
                .rating(entity.getRating())
                .path(entity.getPath())
                .build();
    }

    public List<MusicOutDto> toOutDtoList(List<Music> all) {
        List<MusicOutDto> outDtoList = new ArrayList<>();
        for(Music entity : all) {
            outDtoList.add(this.toOutDto(entity));
        }
        return outDtoList;
    }
}

