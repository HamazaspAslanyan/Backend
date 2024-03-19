package com.example.backend.controllers;

import com.example.backend.dtos.DanceDto;
import com.example.backend.services.DanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class DanceController {

    private final DanceService danceService;
    @GetMapping("/dances")
    public ResponseEntity<List<DanceDto>> allDances(){
        return ResponseEntity.ok(danceService.allDances());
    }

    @GetMapping("/dance/{id}")
    public ResponseEntity<DanceDto> getDance(@PathVariable UUID id){
        return ResponseEntity.ok(danceService.getDance(id));
    }

    @PostMapping("/dance")
    public ResponseEntity<DanceDto> createDance(@Valid @RequestBody DanceDto danceDto) {
        DanceDto createdDance = danceService.createDance(danceDto);
        return ResponseEntity.created(URI.create("/dance/" + createdDance.getId())).body(createdDance);
    }

    @DeleteMapping("/dances/{id}")
    public ResponseEntity<DanceDto> deleteDance(@PathVariable UUID id) {
        return ResponseEntity.ok(danceService.deleteDance(id));
    }

    @PutMapping("/dances/{id}")
    public ResponseEntity<DanceDto> updateDance(@PathVariable UUID id, @Valid @RequestBody DanceDto danceDto) {
        return ResponseEntity.ok(danceService.updateDance(id, danceDto));
    }

}
