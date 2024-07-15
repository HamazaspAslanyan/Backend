package com.example.backend.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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

    private String photoUrl;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "name_translation_id", referencedColumnName = "id")
    private Translation name;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "name_long_translation_id", referencedColumnName = "id")
    private Translation nameLong;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "description_translation_id", referencedColumnName = "id")
    private Translation description;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_translation_id", referencedColumnName = "id")
    private Address address;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_translation_id", referencedColumnName = "id")
    private Contact contact;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "ENSEMBLE_MUSIC",
            joinColumns = {
                    @JoinColumn(name = "ensemble_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "music_id", referencedColumnName = "id")
            })
    @JsonManagedReference
    private Set<Music> music_list;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ensemble ensemble = (Ensemble) o;
        return Objects.equals(id, ensemble.id);
    }

    @Override
    public String toString() {
        return "Ensemble{" +
                "id=" + id +
                '}';
    }


}
