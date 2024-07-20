package com.example.backend.services;

import com.example.backend.dtos.OptionDto;
import com.example.backend.dtos.in.VideoInDto;
import com.example.backend.dtos.out.VideoOutDto;
import com.example.backend.entities.*;
import com.example.backend.exceptions.AppException;
import com.example.backend.mappers.VideoMapper;
import com.example.backend.mappers.OptionMapper;
import com.example.backend.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class VideoService {

    @Autowired
    private final VideoRepository videoRepository;
    @Autowired
    private final DanceRepository danceRepository;
    @Autowired
    private final OptionRepository optionRepository;
    @Autowired
    private final EnsembleRepository ensembleRepository;
    @Autowired
    private final OptionMapper optionMapper;
    @Autowired
    private final VideoMapper videoMapper;

    public List<VideoOutDto> allVideos(){
        List <Video> all = videoRepository.findAll();
        return videoMapper.toOutDtoList(all);
    }



    public VideoOutDto getVideo(UUID id) {

        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new AppException("Video not found", HttpStatus.NOT_FOUND));
        return videoMapper.toOutDto(video);
    }

    public List<OptionDto> getVideoNameListAm() {
        return optionMapper.toOutDtoList(optionRepository.getVideoNameListAm());
    }

    public VideoOutDto updateVideo(UUID id, VideoInDto dto) {

        videoRepository.findById(id).orElseThrow(() -> new AppException("Video not found", HttpStatus.NOT_FOUND));

        Video entity  = initVideo(dto);
        entity.setId(id);

        return videoMapper.toOutDto(videoRepository.save(entity));
    }

    public VideoOutDto createVideo(VideoInDto dto) {

        Video entity  = initVideo(dto);

        return videoMapper.toOutDto(videoRepository.save(entity));
    }

    private Video initVideo(VideoInDto dto){
        Video entity  = new Video();

        Set<Dance> danceSet = new HashSet<>();
        if (dto.getDanceList() != null && !dto.getDanceList().isEmpty()) {
            for (UUID genreId : dto.getDanceList()) {
                danceRepository.findById(genreId).ifPresent(danceSet::add);
            }
        }

        Set<Ensemble> ensembleSet = new HashSet<>();
        if (dto.getEnsembleList() != null && !dto.getEnsembleList().isEmpty()) {
            for (UUID stateId : dto.getEnsembleList()) {
                ensembleRepository.findById(stateId).ifPresent(ensembleSet::add);
            }
        }

        entity.setDanceList(danceSet);
        entity.setEnsembleList(ensembleSet);
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setUrl(dto.getUrl());
        entity.setType(dto.getType());
        entity.setModifiedAt(new Date());

        return entity;
    }
}

