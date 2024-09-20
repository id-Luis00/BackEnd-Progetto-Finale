package ediLuis.progetto_finale.services;


import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import ediLuis.progetto_finale.entities.Booking;
import ediLuis.progetto_finale.entities.Event;
import ediLuis.progetto_finale.entities.User;
import ediLuis.progetto_finale.exceptions.BadRequestException;
import ediLuis.progetto_finale.payloads.bookingPayloads.BookingDTO;
import ediLuis.progetto_finale.payloads.bookingPayloads.BookingRespDTO;
import ediLuis.progetto_finale.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private EventService eventService;

    // serve la classe per creare una nuova prenotazione
    public BookingRespDTO saveNewBooking(BookingDTO body){

        // controllo l'utente
        User user = this.userService.findById(UUID.fromString(body.userId()));

        // controllo l'evento
        Event event = this.eventService.findById(UUID.fromString(body.eventId()));

        // controllo se ha già una prenotazione
      //  if (this.bookingRepository.existsByEventDateAndUserId(String.valueOf(user.getId()), event.getDate())) throw new BadRequestException("La prenotazione è già presente ");

        Booking newBooking = new Booking(user, event);
        this.bookingRepository.save(newBooking);

        return new BookingRespDTO(newBooking.getId());
    }

    // modificare una prenotazione

    // eliminare una prenotazione

    // visualizzare tutte le prenotazioni

    // visualizzare una sola prenotazione
}
