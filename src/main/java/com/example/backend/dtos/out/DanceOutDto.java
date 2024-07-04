package com.example.backend.dtos.out;

import com.example.backend.entities.Genre;
import com.example.backend.entities.State;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class DanceOutDto {

    @Id
    private UUID id;
    private String name;

    private Set<State> states;
    private Set<Genre> genres;

}
