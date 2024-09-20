package ediLuis.progetto_finale.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    private LocalDate date;
    private String title;
    private String description;
    private String place;
    private int num_sits;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User user;

    public Event(LocalDate date, String title, String description, String place, int num_sits, User user) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.place = place;
        this.num_sits = num_sits;
        this.user=user;
    }
}
