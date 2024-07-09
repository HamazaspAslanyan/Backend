package com.example.backend.dtos.out;

import com.example.backend.entities.*;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Objects;
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

    private Translation name;
    private Translation description;
    private Translation descriptionExtra;
    private Set<State> stateList;
    private Set<Genre> genreList;
    private Set<Music> musicList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DanceOutDto that = (DanceOutDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DanceOutDto{" +
                "id=" + id +
                ", name=" + (name != null ? name.getId() : null) +
                ", description=" + (description != null ? description.getId() : null) +
                ", descriptionExtra=" + (descriptionExtra != null ? descriptionExtra.getId() : null) +
                ", stateList=" + (stateList != null ? stateList.stream().map(State::getId).collect(Collectors.toSet()) : null) +
                ", genreList=" + (genreList != null ? genreList.stream().map(Genre::getId).collect(Collectors.toSet()) : null) +
                ", musicList=" + (musicList != null ? musicList.stream().map(Music::getId).collect(Collectors.toSet()) : null) +
                '}';
    }

}
