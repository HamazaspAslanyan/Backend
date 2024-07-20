package com.example.backend.controllers;

import com.example.backend.dtos.OptionDto;
import com.example.backend.dtos.in.DanceInDto;
import com.example.backend.dtos.in.VideoInDto;
import com.example.backend.dtos.out.DanceOutDto;
import com.example.backend.dtos.out.VideoOutDto;
import com.example.backend.services.DanceService;
import com.example.backend.services.VideoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController("/video")
public class VideoController {
    private final VideoService videoService;

    @GetMapping("")
    public ResponseEntity<List<VideoOutDto>> allVideos(){
        return ResponseEntity.ok(videoService.allVideos());
    }

    @GetMapping("/name_list")
    public ResponseEntity<List<OptionDto>> getVideoNameList(){
        return ResponseEntity.ok(videoService.getVideoNameListAm());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoOutDto> getVideo(@PathVariable UUID id){
        return ResponseEntity.ok(videoService.getVideo(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoOutDto> updateVideo(@PathVariable UUID id, @Valid @RequestBody VideoInDto dto) {
        return ResponseEntity.ok(videoService.updateVideo(id, dto));
    }

    @PostMapping("")
    public ResponseEntity<VideoOutDto> createVideo(@Valid @RequestBody VideoInDto dto) {
        VideoOutDto createdVideo = videoService.createVideo(dto);
        return ResponseEntity.created(URI.create("/video/" + createdVideo.getId())).body(createdVideo);
    }

}
