package com.example.backend.services;

import com.example.backend.dtos.in.DanceInDto;
import com.example.backend.dtos.out.DanceNameOutDto;
import com.example.backend.dtos.out.DanceOutDto;
import com.example.backend.entities.Dance;
import com.example.backend.dtos.telegram.TelegramButton;
import com.example.backend.entities.Genre;
import com.example.backend.entities.State;
import com.example.backend.exceptions.AppException;
import com.example.backend.mappers.DanceMapper;
import com.example.backend.mappers.TelegramMapper;
import com.example.backend.repositories.DanceRepository;
import com.example.backend.repositories.GenreOptionRepository;
import com.example.backend.repositories.StateOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DanceService {

    @Autowired
    private final DanceRepository danceRepository;
    @Autowired
    private final StateOptionRepository stateOptionRepository;
    @Autowired
    private final GenreOptionRepository genreOptionRepository;
    @Autowired
    private final DanceMapper danceMapper;
    @Autowired
    private final TelegramMapper telegramMapper;

    public List<DanceOutDto> allDances(){
        List <Dance> all = danceRepository.findAll();
        return danceMapper.toOutDtoList(all);
    }



    public DanceOutDto getDance(UUID id) {

        Dance dance = danceRepository.findById(id)
                .orElseThrow(() -> new AppException("Dance not found", HttpStatus.NOT_FOUND));

        return danceMapper.toOutDto(dance);
    }
    public List<DanceNameOutDto> getDanceNameList() {

        return danceRepository.getDanceNameList();
    }




//    public List<DanceOutDto> searchDance(String danceName) {
//        try {
//            List <Dance> all = danceRepository.searchByName(danceName);
//
//            return danceMapper.toDanceOutDtoList(all);
//        }catch (Exception e){
//             return null;
//        }
//
//    }


    public DanceOutDto updateDance(UUID id, DanceInDto dto) {

        return null;

//        Dance optionalDance  = danceRepository.findById(id)
//                .orElseThrow(() -> new AppException("Dance not found", HttpStatus.NOT_FOUND));
//
//
//        Set<DanceTranslation> translations = new HashSet<>();
//        for (DanceTranslation translation : dto.getTranslations()) {
//
//            danceTranslationRepository.findById(translation.getLanguageCode()).ifPresent(translations::add);
//        }
//
//        // Fetch genres
//        Set<Genre> genres = new HashSet<>();
//        for (UUID genreId : dto.getGenreList()) {
//            genreOptionRepository.findById(genreId).ifPresent(genres::add);
//        }
//
//        // Fetch states
//        Set<State> states = new HashSet<>();
//        for (UUID stateId : dto.getStateList()) {
//            stateOptionRepository.findById(stateId).ifPresent(states::add);
//        }
//
//        optionalDance.setGenre_list(genres);
//        optionalDance.setState_list(states);
//
//        return danceMapper.toOutDto(danceRepository.save(optionalDance));
    }

    public DanceOutDto createDance(DanceInDto dto) {

        Dance entity = Dance.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .descriptionExtra(dto.getDescriptionExtra())
                .build();


        if (dto.getGenreList() != null && !dto.getGenreList().isEmpty()){
            Set<Genre> genres = new HashSet<>();
            for (UUID genreId : dto.getGenreList()) {
                genreOptionRepository.findById(genreId).ifPresent(genres::add);
            }
            entity.setGenre_list(genres);
        }

        if (dto.getStateList() != null && !dto.getStateList().isEmpty()){

            Set<State> states = new HashSet<>();
            for (UUID stateId : dto.getStateList()) {
                stateOptionRepository.findById(stateId).ifPresent(states::add);
            }
            entity.setState_list(states);
        }
        return danceMapper.toOutDto(danceRepository.save(entity));
    }

//    /** OPTIONS */
//    public List<DanceOptionDto> allOptions(){
//        List <Dance> all = danceRepository.findAll();
//        return danceMapper.toDanceOptionDtoList(all);
//    }
//
//    public DanceOptionDto getOption(UUID id) {
//        Dance dance = danceRepository.findById(id)
//                .orElseThrow(() -> new AppException("Dance not found", HttpStatus.NOT_FOUND));
//
//        return danceMapper.toDanceOptionDto(dance, new ArrayList<>());
//    }



    /** TELEGRAM */
    public List<TelegramButton> getTelegramDanceList() {

        List <Dance> all = danceRepository.findAll();
        List<DanceOutDto> outDtoList =  danceMapper.toOutDtoList(all);
        return telegramMapper.toDanceButtons(outDtoList);
    }
}

