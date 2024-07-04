package com.example.backend.mappers;

import com.example.backend.dtos.DanceOptionDto;
import com.example.backend.dtos.in.DanceInDto;
import com.example.backend.dtos.out.DanceOutDto;
import com.example.backend.entities.Dance;
import com.example.backend.dtos.telegram.TelegramFile;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public class DanceMapper {

    public Dance toDanceEntity(DanceOptionDto danceOptionDto){
        return Dance.builder()
                .name(danceOptionDto.getName())
                .build();
    }


    public Dance toDanceEntity(DanceInDto danceInDto){
        return Dance.builder()
                .name(danceInDto.getName())
//                (danceInDto.getDescription()))
                .build();
    }


    public DanceOutDto toDanceOutDto(Dance dance) {
        return DanceOutDto.builder()
                .id(dance.getId())
                .name(dance.getName())
                .genres(dance.getGenre_list())
                .states(dance.getState_list())
                .build();
    }

    public List<DanceOutDto> toDanceOutDtoList(List<Dance> all) {
        List<DanceOutDto> danceOutDtoList = new ArrayList<>();
        for(Dance danceEntity : all) {
            danceOutDtoList.add(DanceOutDto.builder()
                    .id(danceEntity.getId())
                    .name(danceEntity.getName())
                    .genres(danceEntity.getGenre_list())
                    .states(danceEntity.getState_list())
                    .build());
        }
        return danceOutDtoList;
    }

    /** OPTION */
    public DanceOptionDto toDanceOptionDto(Dance dance, List<TelegramFile> musics){
        return DanceOptionDto.builder()
                .id(dance.getId())
                .name(dance.getName())
                .build();
    }

    public List<DanceOptionDto> toDanceOptionDtoList(List<Dance> all) {
        List<DanceOptionDto> danceOptionDtoList = new ArrayList<>();
        for(Dance danceEntity : all) {
            danceOptionDtoList.add(DanceOptionDto.builder()
                    .id(danceEntity.getId())
                    .name(danceEntity.getName())
                    .build());
        }
        return danceOptionDtoList;
    }
}

