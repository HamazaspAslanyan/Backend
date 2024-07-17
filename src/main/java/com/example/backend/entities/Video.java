package com.example.backend.entities;

import com.example.backend.constant.MusicType;
import com.example.backend.constant.VideoType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "description_translation_id", referencedColumnName = "id")
    private Translation description;

    private String url;

    @ManyToMany(mappedBy = "video_list", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Dance> danceList;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "VIDEO_ENSEMBLE",
            joinColumns = {
                    @JoinColumn(name = "video_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ensemble_id", referencedColumnName = "id")
            })
    @JsonManagedReference("ensembleRef")
    private Set<Ensemble> ensemble_list;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date createdAt;
    private Date modifiedAt;

    private Enum<VideoType> type;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video obj = (Video) o;
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
                '}';
    }
}