package ediLuis.progetto_finale.repositories;

import ediLuis.progetto_finale.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {

    // boolean existsByEventDateAndUserId(String userId, LocalDate date);

}
