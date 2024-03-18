package com.example.backend.dtos;

import com.example.backend.entities.TelegramFile;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.net.URL;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class DanceDto {
    @Id
    private Long id;
    @NotNull
    private String name;
    private String description;
    private String type;
    private String district;
    private List<TelegramFile> musicList;
    private List<URL> videoList;
    private List<URL> lessonList;

}
