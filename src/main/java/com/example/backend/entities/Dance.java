package com.example.backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "dance")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class Dance {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "name_translation_id", referencedColumnName = "id")
    private Translation name;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "description_translation_id", referencedColumnName = "id")
    private Translation description;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "description_extra_translation_id", referencedColumnName = "id")
    private Translation descriptionExtra;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date createdAt;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "DANCE_GENRE",
            joinColumns = {
                    @JoinColumn(name = "dance_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "genre_id", referencedColumnName = "id")
            })
    @JsonManagedReference("genreRef")
    private Set<Genre> genre_list;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "DANCE_STATE",
            joinColumns = {
                    @JoinColumn(name = "dance_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "state_id", referencedColumnName = "id")
            })
    @JsonManagedReference("stateRef")
    private Set<State> state_list;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "DANCE_MUSIC",
            joinColumns = {
                    @JoinColumn(name = "dance_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "music_id", referencedColumnName = "id")
            })
    @JsonManagedReference("musicRef")
    private Set<Music> music_list;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dance dance = (Dance) o;
        return Objects.equals(id, dance.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Dance{" +
                "id=" + id +
                ", name=" + (name != null ? name.getId() : null) +
                ", description=" + (description != null ? description.getId() : null) +
                ", descriptionExtra=" + (descriptionExtra != null ? descriptionExtra.getId() : null) +
                ", genre_list=" + (genre_list != null ? genre_list.stream().map(Genre::getId).collect(Collectors.toSet()) : null) +
                ", state_list=" + (state_list != null ? state_list.stream().map(State::getId).collect(Collectors.toSet()) : null) +
                ", music_list=" + (music_list != null ? music_list.stream().map(Music::getId).collect(Collectors.toSet()) : null) +
                '}';
    }

}