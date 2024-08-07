package com.example.backend.dtos.in;

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
public class MusicInDto {
    @Id
    private UUID id;

    private Translation name;
    private Integer rating;
    private String path;
    private List<UUID> danceList;
    private List<UUID> ensambleList;
}
