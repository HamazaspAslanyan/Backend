package com.example.backend.mappers;

import com.example.backend.dtos.OptionDto;
import com.example.backend.entities.Dance;
import com.example.backend.entities.Option;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public class OptionMapper {

    public OptionDto toOutDto(Option entity) {
        OptionDto outDto = OptionDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();

        return outDto;
    }

    public List<OptionDto> toOutDtoList(List<Option> entityList) {
        List<OptionDto> outDtoList = new ArrayList<>();
        for(Option entity : entityList) {
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

