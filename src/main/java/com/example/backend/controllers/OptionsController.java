package com.example.backend.controllers;

import com.example.backend.dtos.DanceOptionDto;
import com.example.backend.dtos.GenreOptionDto;
import com.example.backend.dtos.StateOptionDto;
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

    @GetMapping("/dancesik")
    public ResponseEntity<List<DanceOptionDto>> allDanceOptions(){
        return ResponseEntity.ok(danceService.allOptions());
    }

    @GetMapping("/genres")
    public ResponseEntity<List<GenreOptionDto>> allGenreOptions(){
        return ResponseEntity.ok(genreOptionService.allOptions());
    }

    @GetMapping("/genre/{id}")
    public ResponseEntity<GenreOptionDto> getGenreOption(@PathVariable UUID id){
        return ResponseEntity.ok(genreOptionService.getOption(id));
    }

    @PostMapping("/genre")
    public ResponseEntity<GenreOptionDto> createGenreOption(@Valid @RequestBody GenreOptionDto optionDto) {
        GenreOptionDto createdOption = genreOptionService.createOption(optionDto);
        return ResponseEntity.created(URI.create("/genre/" + createdOption.getId())).body(createdOption);
    }

    @DeleteMapping("/genres/{id}")
    public ResponseEntity<GenreOptionDto> deleteGenreOption(@PathVariable UUID id) {
        return ResponseEntity.ok(genreOptionService.deleteOption(id));
    }

    @PutMapping("/genres/{id}")
    public ResponseEntity<GenreOptionDto> updateGenreOption(@PathVariable UUID id, @Valid @RequestBody GenreOptionDto optionDto) {
        return ResponseEntity.ok(genreOptionService.updateOption(id, optionDto));
    }

    @GetMapping("/states")
    public ResponseEntity<List<StateOptionDto>> allStateOptions(){
        return ResponseEntity.ok(stateOptionService.allOptions());
    }

    @GetMapping("/state/{id}")
    public ResponseEntity<StateOptionDto> getStateOption(@PathVariable UUID id){
        return ResponseEntity.ok(stateOptionService.getOption(id));
    }

    @PostMapping("/state")
    public ResponseEntity<StateOptionDto> createStateOption(@Valid @RequestBody StateOptionDto optionDto) {
        StateOptionDto createdOption = stateOptionService.createOption(optionDto);
        return ResponseEntity.created(URI.create("/state/" + createdOption.getId())).body(createdOption);
    }

    @DeleteMapping("/state/{id}")
    public ResponseEntity<StateOptionDto> deleteStateOption(@PathVariable UUID id) {
        return ResponseEntity.ok(stateOptionService.deleteOption(id));
    }

    @PutMapping("state/{id}")
    public ResponseEntity<StateOptionDto> updateStateOption(@PathVariable UUID id, @Valid @RequestBody StateOptionDto optionDto) {
        return ResponseEntity.ok(stateOptionService.updateOption(id, optionDto));
    }

}
