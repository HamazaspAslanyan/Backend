package com.example.backend.services;

import com.example.backend.dtos.OptionDto;
import com.example.backend.dtos.in.OptionInDto;
import com.example.backend.dtos.out.OptionOutDto;
import com.example.backend.entities.Genre;
import com.example.backend.exceptions.AppException;
import com.example.backend.mappers.GenreOptionMapper;
import com.example.backend.repositories.GenreOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GenreOptionService {

    private final GenreOptionRepository genreOptionRepository;
    private final GenreOptionMapper genreOptionMapper;

    public List<OptionOutDto> allOptions(){
        List <Genre> all = genreOptionRepository.findAll();
        return genreOptionMapper.toDtoList(all);
    }

    public OptionOutDto getOption(UUID id) {
        Genre entity = genreOptionRepository.findById(id)
                .orElseThrow(() -> new AppException("Genre not found", HttpStatus.NOT_FOUND));

        return genreOptionMapper.toDto(entity);
    }

    public OptionOutDto createOption(OptionInDto optionDto) {
        return genreOptionMapper.toDto(genreOptionRepository.save(genreOptionMapper.toEntity(optionDto)));
    }

    public OptionOutDto updateOption(UUID id, OptionInDto optionDto) {
//        Genre entity = genreOptionRepository.findById(id)
//                .orElseThrow(() -> new AppException("Genre not found", HttpStatus.NOT_FOUND));
//        entity.getName().setAm("aaaaaaa");
        return genreOptionMapper.toDto(genreOptionRepository.save(genreOptionMapper.toEntity(optionDto)));
    }

    public OptionOutDto deleteOption(UUID id) {
        Genre entity = genreOptionRepository.findById(id)
                .orElseThrow(() -> new AppException("Genre not found", HttpStatus.NOT_FOUND));

        genreOptionRepository.deleteById(id);
        return  genreOptionMapper.toDto(entity);
    }
}

