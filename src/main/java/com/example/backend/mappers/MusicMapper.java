package com.example.backend.mappers;

import com.example.backend.dtos.in.DanceInDto;
import com.example.backend.dtos.in.MusicInDto;
import com.example.backend.dtos.options.DanceOptionDto;
import com.example.backend.dtos.out.DanceOutDto;
import com.example.backend.dtos.out.MusicOutDto;
import com.example.backend.dtos.telegram.TelegramFile;
import com.example.backend.entities.Dance;
import com.example.backend.entities.Music;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public class MusicMapper {

    public Music toEntity(MusicInDto dto){
        return Music.builder()
                .name(dto.getName())
                .path(dto.getPath())
                .rating(dto.getRating())
                .build();
    }


    public MusicOutDto toOutDto(Music entity) {
        return MusicOutDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .rating(entity.getRating())
                .path(entity.getPath())
                .build();
    }

    public List<MusicOutDto> toOutDtoList(List<Music> all) {
        List<MusicOutDto> outDtoList = new ArrayList<>();
        for(Music entity : all) {
            outDtoList.add(MusicOutDto.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .rating(entity.getRating())
                    .path(entity.getPath())
                    .build());
        }
        return outDtoList;
    }
}

