package com.example.backend.services;

import com.example.backend.dtos.GenreOptionDto;
import com.example.backend.entities.Genre;
import com.example.backend.exceptions.AppException;
import com.example.backend.mappers.GenreOptionMapper;
import com.example.backend.mappers.TelegramMapper;
import com.example.backend.repositories.GenreOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GenreOptionService {

    private final GenreOptionRepository genreOptionRepository;
    private final GenreOptionMapper genreOptionMapper;

    public List<GenreOptionDto> allOptions(){
        List <Genre> all = genreOptionRepository.getGenres();
        return genreOptionMapper.toGenreDtoList(all);
    }

    public GenreOptionDto getOption(UUID id) {
        Genre genre = genreOptionRepository.findById(id)
                .orElseThrow(() -> new AppException("Genre not found", HttpStatus.NOT_FOUND));

        return genreOptionMapper.toGenreDto(genre);
    }

    public GenreOptionDto createOption(GenreOptionDto optionDto) {
        Genre genre = genreOptionMapper.toGenre(optionDto);
        Genre createdGenre = genreOptionRepository.save(genre);
        return genreOptionMapper.toGenreDto(createdGenre);
    }

    public GenreOptionDto updateOption(UUID id, GenreOptionDto optionDto) {
        Genre genre = genreOptionRepository.findById(id)
                .orElseThrow(() -> new AppException("Genre not found", HttpStatus.NOT_FOUND));

        Genre updatedGenre = genreOptionRepository.save(genre);

        return genreOptionMapper.toGenreDto(updatedGenre);
    }

    public GenreOptionDto deleteOption(UUID id) {
        Genre genre = genreOptionRepository.findById(id)
                .orElseThrow(() -> new AppException("Genre not found", HttpStatus.NOT_FOUND));

        genreOptionRepository.deleteById(id);

        return  genreOptionMapper.toGenreDto(genre);

    }
}

