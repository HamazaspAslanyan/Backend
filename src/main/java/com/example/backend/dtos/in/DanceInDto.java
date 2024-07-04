package com.example.backend.dtos.in;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class DanceInDto {
    @Id
    private UUID id;
    private String name;
    private String description_extra;
    private String description;
    private List<UUID> genreList;
    private List<UUID> stateList;
    private List<UUID> musicList;
    private List<UUID> videoList;
    private List<UUID> lessonList;

}
