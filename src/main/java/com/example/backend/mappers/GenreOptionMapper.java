package com.example.backend.mappers;

import com.example.backend.dtos.OptionDto;
import com.example.backend.dtos.in.OptionInDto;
import com.example.backend.dtos.out.OptionOutDto;
import com.example.backend.entities.Genre;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public class GenreOptionMapper {

    public Genre toEntity(OptionInDto optionDto){
        return Genre.builder()
                .id(optionDto.getId())
                .name(optionDto.getName())
                .build();
    }

    public OptionOutDto toDto(Genre genre){
        return OptionOutDto.builder()
                .name(genre.getName())
                .id(genre.getId())
                .build();
    }

    public List<OptionOutDto> toDtoList(List<Genre> all) {
        List<OptionOutDto> genreOptionDtoList = new ArrayList<>();
        for(Genre entity : all) {
            genreOptionDtoList.add(this.toDto(entity));
        }
        return genreOptionDtoList;
    }
}

