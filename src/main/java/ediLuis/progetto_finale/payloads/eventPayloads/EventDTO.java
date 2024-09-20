package ediLuis.progetto_finale.payloads.eventPayloads;

import java.time.LocalDate;

public record EventDTO(
        LocalDate date,
        String title,
        String description,
        String place,
        int num_sits,
        String creator_id
) {
}
