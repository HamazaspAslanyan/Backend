package com.example.backend.services;

import com.example.backend.dtos.DanceOptionDto;
import com.example.backend.dtos.in.DanceInDto;
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
        return danceMapper.toDanceOutDtoList(all);
    }

    public DanceOutDto getDance(UUID id) {
//
//    Optional<Dance> listik = danceRepository.findById(id);
        try {

//            List<Object[]> result = danceRepository.findByIdWithGenresAndStates(id);
//                    .orElseThrow(() -> new AppException("Dance not found", HttpStatus.NOT_FOUND));
//            Dance dance = danceRepository.findByIdWithGenresAndStates(id)
//                    .orElseThrow(() -> new AppException("Dance not found", HttpStatus.NOT_FOUND));

            Dance dance = danceRepository.findById(id)
                    .orElseThrow(() -> new AppException("Dance not found", HttpStatus.NOT_FOUND));

//            Dance dance = null;
                    System.out.println("Fetched Dance: " + dance);
            System.out.println("Genres: " + dance.getGenre_list());
            System.out.println("States: " + dance.getState_list());

            return danceMapper.toDanceOutDto(dance);
        } catch (Exception e){
            System.out.println(e.getMessage() + "");
            e.printStackTrace();
            System.out.println(e + "");

        }


        return null;
    }


    public DanceOutDto updateDance(UUID id, DanceInDto dto) {

        Dance optionalDance  = danceRepository.findById(id)
                .orElseThrow(() -> new AppException("Dance not found", HttpStatus.NOT_FOUND));

        // Fetch genres
        Set<Genre> genres = new HashSet<>();
        for (UUID genreId : dto.getGenreList()) {
            genreOptionRepository.findById(genreId).ifPresent(genres::add);
        }

        // Fetch states
        Set<State> states = new HashSet<>();
        for (UUID stateId : dto.getStateList()) {
            stateOptionRepository.findById(stateId).ifPresent(states::add);
        }

        optionalDance.setName(dto.getName());
        optionalDance.setGenre_list(genres);
        optionalDance.setState_list(states);

        return danceMapper.toDanceOutDto(danceRepository.save(optionalDance));
    }

    public DanceOutDto createDance(DanceInDto dto) {

        // Fetch genres
        Set<Genre> genres = new HashSet<>();
        for (UUID genreId : dto.getGenreList()) {
            genreOptionRepository.findById(genreId).ifPresent(genres::add);
        }

        // Fetch states
        Set<State> states = new HashSet<>();
        for (UUID stateId : dto.getStateList()) {
            stateOptionRepository.findById(stateId).ifPresent(states::add);
        }

        Dance dance = Dance.builder()
                        .name(dto.getName())
                        .genre_list(genres)
                        .state_list(states)
                        .build();

        return danceMapper.toDanceOutDto(danceRepository.save(dance));
    }

    /** OPTIONS */
    public List<DanceOptionDto> allOptions(){
        List <Dance> all = danceRepository.findAll();
        return danceMapper.toDanceOptionDtoList(all);
    }

    public DanceOptionDto getOption(UUID id) {
        Dance dance = danceRepository.findById(id)
                .orElseThrow(() -> new AppException("Dance not found", HttpStatus.NOT_FOUND));

        return danceMapper.toDanceOptionDto(dance, new ArrayList<>());
    }

    public DanceOptionDto createOption(DanceOptionDto danceOptionDto) {
        Dance dance = danceMapper.toDanceEntity(danceOptionDto);
        Dance createdDance = danceRepository.save(dance);
        return danceMapper.toDanceOptionDto(createdDance, new ArrayList<>());
    }

    public DanceOptionDto updateOption(UUID id, DanceOptionDto danceOptionDto) {
        Dance dance = danceRepository.findById(id)
                .orElseThrow(() -> new AppException("Dance not found", HttpStatus.NOT_FOUND));

//        danceMapper.updateDance(dance, danceMapper.toDance(danceDto));

        Dance updatedDance = danceRepository.save(dance);

        return danceMapper.toDanceOptionDto(updatedDance, new ArrayList<>());
    }

    public DanceOptionDto deleteOption(UUID id) {
        Dance dance = danceRepository.findById(id)
                .orElseThrow(() -> new AppException("Dance not found", HttpStatus.NOT_FOUND));

        danceRepository.deleteById(id);

        return  danceMapper.toDanceOptionDto(dance, new ArrayList<>());

    }

    /** TELEGRAM*/
    public List<TelegramButton> getTelegramDanceList() {

        List <Dance> all = danceRepository.findAll();
        List<DanceOutDto> danceOutDtos =  danceMapper.toDanceOutDtoList(all);
        return telegramMapper.toDanceButtons(danceOutDtos);
    }
}

