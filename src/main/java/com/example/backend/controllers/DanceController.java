package com.example.backend.controllers;

import com.example.backend.dtos.in.DanceInDto;
import com.example.backend.dtos.out.DanceNameOutDto;
import com.example.backend.dtos.out.DanceOutDto;
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
    public ResponseEntity<List<DanceOutDto>> allDances(){
        return ResponseEntity.ok(danceService.allDances());
    }
    @GetMapping("/dances/name_list")
    public ResponseEntity<List<DanceNameOutDto>> getDanceNameList(){
        return ResponseEntity.ok(danceService.getDanceNameList());
    }

    @GetMapping("/dances/{id}")
    public ResponseEntity<DanceOutDto> getDance(@PathVariable UUID id){
        return ResponseEntity.ok(danceService.getDance(id));
    }

    @PutMapping("/dance/{id}")
    public ResponseEntity<DanceOutDto> updateDance(@PathVariable UUID id, @Valid @RequestBody DanceInDto dto) {
        return ResponseEntity.ok(danceService.updateDance(id, dto));
    }

    @PostMapping("/dance")
    public ResponseEntity<DanceOutDto> createDance(@Valid @RequestBody DanceInDto dto) {
        DanceOutDto createdDance = danceService.createDance(dto);
        return ResponseEntity.created(URI.create("/dance/" + createdDance.getId())).body(createdDance);
    }

}
