package com.example.backend.dtos.out;

import com.example.backend.entities.Dance;
import com.example.backend.entities.Genre;
import com.example.backend.entities.State;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class MusicOutDto {

    @Id
    private UUID id;
    private String name;
    private String path;
    private String rating;

//    private Set<Dance> dances;

}
