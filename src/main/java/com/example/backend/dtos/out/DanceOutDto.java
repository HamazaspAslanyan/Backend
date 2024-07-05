package com.example.backend.dtos.out;

import com.example.backend.entities.Genre;
import com.example.backend.entities.Music;
import com.example.backend.entities.State;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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

    private Set<State> stateList;
    private Set<Genre> genreList;
    private Set<Music> musicList;

    @Override
    public String toString() {
        return "DanceOutDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                // Avoid recursive call to Genre's toString
                ", genres=" + (genreList != null ? genreList.stream().map(genre -> genre.getId()).collect(Collectors.toSet()) : null) +
                // Avoid recursive call to Genre's toString
                ", music_list=" + (musicList != null ? musicList.stream().map(music -> music.getId()).collect(Collectors.toSet()) : null) +
                '}';
    }

}
