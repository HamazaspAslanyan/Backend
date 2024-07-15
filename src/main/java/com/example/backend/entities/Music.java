package com.example.backend.entities;

import com.example.backend.constant.MusicType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "music")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "name_translation_id", referencedColumnName = "id")
    private Translation name;

    private String path;
    private String rating;

    @ManyToMany(mappedBy = "music_list", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Dance> dances;

    @ManyToMany(mappedBy = "music_list", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Ensemble> ensembles;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date createdAt;

    private Enum<MusicType> type;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Music obj = (Music) o;
        return Objects.equals(id, obj.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", name='" + name + '\'' +
                // Avoid recursive call to Dance's toString
                ", dances=" + (dances != null ? dances.stream().map(dance -> dance.getId()).collect(Collectors.toSet()) : null) +
                '}';
    }
}
