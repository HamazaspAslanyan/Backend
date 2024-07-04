package com.example.backend.dtos;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class DanceOptionDto {
    @Id
    private UUID id;
    @NotNull
    private String name;

}