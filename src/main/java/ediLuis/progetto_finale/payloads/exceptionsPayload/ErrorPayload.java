package ediLuis.progetto_finale.payloads.exceptionsPayload;

import java.time.LocalDateTime;

public record ErrorPayload(
        String message,
        LocalDateTime timestamp
) {
}
