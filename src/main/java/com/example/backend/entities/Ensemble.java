package com.example.backend.entities;

import com.example.backend.constant.MusicType;
import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @ManyToMany(mappedBy = "ensemble_list", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Music> musicList;

    @ManyToMany(mappedBy = "ensemble_list", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Video> videoList;

    @ManyToMany(mappedBy = "ensemble_list", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Event> eventList;

    private Enum<MusicType> musicType;

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
