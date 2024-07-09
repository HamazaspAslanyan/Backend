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
public class DanceInDto {
    @Id
    private UUID id;
    private Translation name;
    private Translation description;
    private Translation descriptionExtra;
    private List<UUID> genreList;
    private List<UUID> stateList;

}
