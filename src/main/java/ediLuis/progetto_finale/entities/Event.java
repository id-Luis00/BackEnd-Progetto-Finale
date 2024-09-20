package ediLuis.progetto_finale.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "evento")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private UUID id;

    private LocalDate date_event;
    private String title;
    private String description;
    private String place;
    private int num_sits;

    public Event(LocalDate date_event, String title, String description, String place, int num_sits) {
        this.date_event = date_event;
        this.title = title;
        this.description = description;
        this.place = place;
        this.num_sits = num_sits;
    }
}
