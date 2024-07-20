package com.example.backend.mappers;

import com.example.backend.dtos.out.VideoOutDto;
import com.example.backend.entities.Video;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public class VideoMapper {

    /**
     * UUID id;
     * Translation name;
     * Translation description;
     * String url;
     * Set<Dance> danceList;
     * Set<Ensemble> ensemble_list;
     * Date createdAt;
     * Date modifiedAt;
     * Enum<VideoType> type;
     * */

    public VideoOutDto toOutDto(Video entity) {
        VideoOutDto outDto = VideoOutDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .url(entity.getUrl())
                .danceList(entity.getDanceList())
                .ensembleList(entity.getEnsembleList())
                .type(entity.getType())
                .createdAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
                .build();

        return outDto;
    }

    public List<VideoOutDto> toOutDtoList(List<Video> entityList) {
        List<VideoOutDto> outDtoList = new ArrayList<>();
        for(Video entity : entityList) {
            outDtoList.add(this.toOutDto(entity));
        }
        return outDtoList;
    }
}

