package com.example.backend.dtos.out;

import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DanceNameOutDto {

    private UUID danceId;
    private String name;
}
