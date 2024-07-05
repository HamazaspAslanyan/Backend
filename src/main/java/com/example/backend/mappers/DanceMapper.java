package com.example.backend.mappers;

import com.example.backend.dtos.options.DanceOptionDto;
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
                .genreList(dance.getGenre_list())
                .stateList(dance.getState_list())
                .musicList(dance.getMusic_list())
                .build();
    }

    public List<DanceOutDto> toDanceOutDtoList(List<Dance> all) {
        List<DanceOutDto> danceOutDtoList = new ArrayList<>();
        for(Dance danceEntity : all) {
            danceOutDtoList.add(DanceOutDto.builder()
                    .id(danceEntity.getId())
                    .name(danceEntity.getName())
                    .genreList(danceEntity.getGenre_list())
                    .stateList(danceEntity.getState_list())
                    .musicList(danceEntity.getMusic_list())
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

