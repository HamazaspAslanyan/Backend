package com.example.backend.controllers;

import com.example.backend.dtos.in.MusicInDto;
import com.example.backend.dtos.out.MusicOutDto;
import com.example.backend.services.MusicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class MusicController {
    private final MusicService service;

    @GetMapping("/musics")
    public ResponseEntity<List<MusicOutDto>> allMusics(){
        return ResponseEntity.ok(service.all());
    }

    @GetMapping("/musics/{id}")
    public ResponseEntity<MusicOutDto> getMusic(@PathVariable UUID id){
        return ResponseEntity.ok(service.get(id));
    }

    @PutMapping("/music/{id}")
    public ResponseEntity<MusicOutDto> updateMusic(@PathVariable UUID id, @Valid @RequestBody MusicInDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @PostMapping("/music")
    public ResponseEntity<MusicOutDto> createMusic(@Valid @RequestBody MusicInDto dto) {
        MusicOutDto createdMusic = service.create(dto);
        return ResponseEntity.created(URI.create("/music/" + createdMusic.getId())).body(createdMusic);
    }
}
