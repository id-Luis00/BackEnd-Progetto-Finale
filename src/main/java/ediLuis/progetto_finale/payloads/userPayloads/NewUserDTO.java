package ediLuis.progetto_finale.payloads.userPayloads;

public record NewUserDTO(
        String username,
        String email,
        String password,
        String name,
        String surname
) {
}
