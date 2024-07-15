package com.example.backend.dtos.in;

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
public class OptionInDto {
    @Id
    private UUID id;
    @NotNull
    private Translation name;

}