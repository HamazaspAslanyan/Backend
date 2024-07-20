package com.example.backend.dtos.in;

import com.example.backend.constant.VideoType;
import com.example.backend.entities.Translation;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class VideoInDto {
    @Id
    private UUID id;
    private String url;
    private Translation name;
    private Translation description;
    private Enum<VideoType> type;
    private List<UUID> danceList;
    private List<UUID> ensembleList;

}
