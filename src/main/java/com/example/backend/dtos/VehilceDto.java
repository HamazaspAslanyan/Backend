package com.example.backend.dtos;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class VehilceDto {
    private Long id;
    @NotNull
    private String brand;
    @NotNull
    private String model;
    @NotNull
    private String color;
    @NotNull
    private int year;
}
