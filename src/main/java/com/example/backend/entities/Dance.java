package com.example.backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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

    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "DANCE_GENRE",
            joinColumns = {
                    @JoinColumn(name = "dance_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "genre_id", referencedColumnName = "id")
            })
    @JsonManagedReference
    private Set<Genre> genre_list;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "DANCE_STATE",
            joinColumns = {
                    @JoinColumn(name = "dance_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "state_id", referencedColumnName = "id")
            })
    @JsonManagedReference
    private Set<State> state_list;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "DANCE_MUSIC",
            joinColumns = {
                    @JoinColumn(name = "dance_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "music_id", referencedColumnName = "id")
            })
    @JsonManagedReference
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
                ", name='" + name + '\'' +
                // Avoid recursive call to Genre's toString
                ", genres=" + (genre_list != null ? genre_list.stream().map(genre -> genre.getId()).collect(Collectors.toSet()) : null) +
                '}';
    }

}