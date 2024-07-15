package com.example.backend.dtos.out;

import com.example.backend.entities.Translation;
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
public class OptionOutDto {
    @Id
    private UUID id;
    @NotNull
    private Translation name;

}