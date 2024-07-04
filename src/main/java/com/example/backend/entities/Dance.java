package com.example.backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;
import java.util.UUID;

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
    private Set<Genre> genre_list;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "DANCE_STATE",
            joinColumns = {
                    @JoinColumn(name = "dance_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "state_id", referencedColumnName = "id")
            })
    private Set<State> state_list;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "DANCE_MUSIC",
            joinColumns = {
                    @JoinColumn(name = "dance_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "music_id", referencedColumnName = "id")
            })
    private Set<Music> music_list;

}