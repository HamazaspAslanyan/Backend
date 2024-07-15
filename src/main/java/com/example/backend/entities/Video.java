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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "name_translation_id", referencedColumnName = "id")
    private Translation name;

//    @ManyToMany(mappedBy = "videos")
//    private Set<Dance> dances;
//
//    @ManyToMany(mappedBy = "videos")
//    private Set<Ensemble> ensembles;
}