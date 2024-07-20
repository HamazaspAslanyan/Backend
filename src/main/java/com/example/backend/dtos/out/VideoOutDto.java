package com.example.backend.dtos.out;

import com.example.backend.constant.VideoType;
import com.example.backend.entities.*;
import jakarta.persistence.Id;
import lombok.*;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class VideoOutDto {

    @Id
    private UUID id;

    private String url;
    private Translation name;
    private Translation description;
    private Enum<VideoType> type;
    private Set<Dance> danceList;
    private Set<Ensemble> ensembleList;
    private Date createdAt;
    private Date modifiedAt;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VideoOutDto that = (VideoOutDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DanceOutDto{" +
                "id=" + id +
                '}';
    }

}
