package ediLuis.progetto_finale.repositories;

import ediLuis.progetto_finale.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

    boolean existsByTitle(String title);
    boolean existsByDate(LocalDate date);
    boolean existsByPlace(String place);
}
