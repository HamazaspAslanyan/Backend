package com.example.backend.entities;

import com.example.backend.constant.EventType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "event")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class Event {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    private String photoUrl;
    private String url;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "header_translation_id", referencedColumnName = "id")
    private Translation header;

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
    @JoinTable(name = "EVENT_ENSEMBLE",
            joinColumns = {
                    @JoinColumn(name = "event_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ensemble_id", referencedColumnName = "id")
            })
    @JsonManagedReference("ensembleRef")
    private Set<Ensemble> ensemble_list;

    private Enum<EventType> eventType;


    private Date startDate;
    private Date endDate;

    private String price;


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                '}';
    }

}
