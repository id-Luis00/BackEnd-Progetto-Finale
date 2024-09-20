package ediLuis.progetto_finale.exceptions;

import java.time.LocalDateTime;

public record ErrorPayload(
        String message,
        LocalDateTime timestamp
) {
}
