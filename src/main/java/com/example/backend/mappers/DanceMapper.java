package com.example.backend.mappers;

import com.example.backend.dtos.out.DanceOutDto;
import com.example.backend.entities.Dance;
import com.example.backend.entities.Translation;
import org.hibernate.Hibernate;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public class DanceMapper {

    public DanceOutDto toOutDto(Dance entity) {
        DanceOutDto outDto = DanceOutDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .descriptionExtra(entity.getDescriptionExtra())
                .genreList(entity.getGenre_list())
                .stateList(entity.getState_list())
                .musicList(entity.getMusic_list())
                .build();

        return outDto;
    }

    public List<DanceOutDto> toOutDtoList(List<Dance> entityList) {
        List<DanceOutDto> outDtoList = new ArrayList<>();
        for(Dance entity : entityList) {
            outDtoList.add(this.toOutDto(entity));
        }
        return outDtoList;
    }




//    /** OPTION */
//    public DanceOptionDto toOptionDto(Dance entity, List<TelegramFile> musics){
//        return DanceOptionDto.builder()
//                .id(dance.getId())
//                .name(dance.getName())
//                .build();
//    }
//
//    public List<DanceOptionDto> toOptionDtoList(List<Dance> all) {
//        List<DanceOptionDto> danceOptionDtoList = new ArrayList<>();
//        for(Dance danceEntity : all) {
//        }
//        return danceOptionDtoList;
//    }
}

