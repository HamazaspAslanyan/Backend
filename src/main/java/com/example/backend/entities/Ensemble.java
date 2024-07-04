package com.example.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "ensemble")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class Ensemble {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    private String name;

    private String description;

//    @ManyToMany
//    @JoinTable(name = "ensemble_music",
//            joinColumns = @JoinColumn(name = "ensemble_id"),
//            inverseJoinColumns = @JoinColumn(name = "music_id"))
//    private Set<Music> musics;
//
//
//    @ManyToMany
//    @JoinTable(name = "ensemble_video",
//            joinColumns = @JoinColumn(name = "ensemble_id"),
//            inverseJoinColumns = @JoinColumn(name = "video_id"))
//    private Set<Video> videos;

//
//    @ManyToMany(mappedBy = "groups")
//    private Set<Event> events;

}
