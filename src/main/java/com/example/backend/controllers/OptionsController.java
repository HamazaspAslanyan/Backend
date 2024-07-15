package com.example.backend.controllers;

import com.example.backend.dtos.OptionDto;
import com.example.backend.dtos.in.OptionInDto;
import com.example.backend.dtos.out.OptionOutDto;
import com.example.backend.services.DanceService;
import com.example.backend.services.GenreOptionService;
import com.example.backend.services.StateOptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController("/options")
public class OptionsController {

    private final DanceService danceService;
    private final GenreOptionService genreOptionService;
    private final StateOptionService stateOptionService;

//    @GetMapping("/dances_options")
//    public ResponseEntity<List<DanceOptionDto>> allDanceOptions(){
//        return ResponseEntity.ok(danceService.allOptions());
//    }

    @GetMapping("/genres")
    public ResponseEntity<List<OptionOutDto>> allGenreOptions(){
        return ResponseEntity.ok(genreOptionService.allOptions());
    }

    @GetMapping("/genre/{id}")
    public ResponseEntity<OptionOutDto> getGenreOption(@PathVariable UUID id){
        return ResponseEntity.ok(genreOptionService.getOption(id));
    }

    @PostMapping("/genre")
    public ResponseEntity<OptionOutDto> createGenreOption(@Valid @RequestBody OptionInDto optionDto) {
        OptionOutDto createdOption = genreOptionService.createOption(optionDto);
        return ResponseEntity.created(URI.create("/genre/" + createdOption.getId())).body(createdOption);
    }

    @DeleteMapping("/genres/{id}")
    public ResponseEntity<OptionOutDto> deleteGenreOption(@PathVariable UUID id) {
        return ResponseEntity.ok(genreOptionService.deleteOption(id));
    }

    @PutMapping("/genres/{id}")
    public ResponseEntity<OptionOutDto> updateGenreOption(@PathVariable UUID id, @Valid @RequestBody OptionInDto optionDto) {
        return ResponseEntity.ok(genreOptionService.updateOption(id, optionDto));
    }

    @GetMapping("/states")
    public ResponseEntity<List<OptionOutDto>> allStateOptions(){
        return ResponseEntity.ok(stateOptionService.allOptions());
    }

    @GetMapping("/state/{id}")
    public ResponseEntity<OptionOutDto> getStateOption(@PathVariable UUID id){
        return ResponseEntity.ok(stateOptionService.getOption(id));
    }

    @PostMapping("/state")
    public ResponseEntity<OptionOutDto> createStateOption(@Valid @RequestBody OptionInDto optionDto) {
        OptionOutDto createdOption = stateOptionService.createOption(optionDto);
        return ResponseEntity.created(URI.create("/state/" + createdOption.getId())).body(createdOption);
    }

    @DeleteMapping("/state/{id}")
    public ResponseEntity<OptionOutDto> deleteStateOption(@PathVariable UUID id) {
        return ResponseEntity.ok(stateOptionService.deleteOption(id));
    }

    @PutMapping("state/{id}")
    public ResponseEntity<OptionOutDto> updateStateOption(@PathVariable UUID id, @Valid @RequestBody OptionInDto optionDto) {
        return ResponseEntity.ok(stateOptionService.updateOption(id, optionDto));
    }

}
