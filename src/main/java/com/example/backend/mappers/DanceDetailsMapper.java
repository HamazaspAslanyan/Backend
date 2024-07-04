package com.example.backend.mappers;

import com.example.backend.dtos.GenreOptionDto;
import com.example.backend.entities.Genre;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public class DanceDetailsMapper {

    public Genre toGenre(GenreOptionDto optionDto){
        return Genre.builder()
                .name(optionDto.getName())
                .build();
    }

    public GenreOptionDto toGenreDto(Genre genre){
        return GenreOptionDto.builder()
                .name(genre.getName())
                .id(genre.getId())
                .build();
    }

    public List<GenreOptionDto> toGenreDtoList(List<Genre> all) {
        List<GenreOptionDto> genreOptionDtoList = new ArrayList<>();
        for(Genre genreEntity : all) {
            genreOptionDtoList.add(GenreOptionDto.builder()
                    .id(genreEntity.getId())
                    .name(genreEntity.getName())
                    .build());
        }
        return genreOptionDtoList;
    }

}

