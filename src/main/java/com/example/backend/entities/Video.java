package com.example.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "video")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    private String name;

//    @ManyToMany(mappedBy = "videos")
//    private Set<Dance> dances;
//
//    @ManyToMany(mappedBy = "videos")
//    private Set<Ensemble> ensembles;
}