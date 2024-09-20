package ediLuis.progetto_finale.services;


import ediLuis.progetto_finale.entities.Event;
import ediLuis.progetto_finale.entities.User;
import ediLuis.progetto_finale.enums.Role;
import ediLuis.progetto_finale.exceptions.BadRequestException;
import ediLuis.progetto_finale.exceptions.NotFoundException;
import ediLuis.progetto_finale.payloads.eventPayloads.EventDTO;
import ediLuis.progetto_finale.payloads.eventPayloads.EventRespDTO;
import ediLuis.progetto_finale.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserService userService;


    public Page<Event> findAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return this.eventRepository.findAll(pageable);
    }

    public Event findById(UUID id){
        return this.eventRepository.findById(id).orElseThrow(() -> new NotFoundException("L'evento non è stato trovato!. ID: " + id));
    }

    public EventRespDTO saveNewEvent(EventDTO body) {
        // cotrolliamo se l'evento è già presente nel db
        if (this.eventRepository.existsByTitle(body.title()) && this.eventRepository.existsByDate(body.date())) {
            if (this.eventRepository.existsByPlace(body.place())) throw new BadRequestException("Evento già presente: " + body.title() + " - " + body.place() + " - " + body.date());
        }

        // reperisco l'utente che ha creato l'evento e lo salvo come User dentro all'event
        User userFound = this.userService.findById(UUID.fromString(body.creator_id()));

        Event event = new Event(body.date(), body.title(), body.description(), body.place(), body.num_sits(), userFound);

        this.eventRepository.save(event);

        return new EventRespDTO(event.getId());
    }

    public Event findAndUpdate(UUID eventId, EventDTO body){
        // cerco l'evento da modificare
        Event foundEvent = this.findById(eventId);

        // verifico che l'utente che modifica sia un organizer
        User userFound = this.userService.findById(UUID.fromString(body.creator_id()));
        if (userFound.getRole().equals(Role.USER)) throw new BadRequestException("Non sei abilitato per la modifica degli eventi");

        foundEvent.setDate(body.date());
        foundEvent.setTitle(body.title());
        foundEvent.setDescription(body.description());
        foundEvent.setPlace(body.place());
        foundEvent.setNum_sits(body.num_sits());
        foundEvent.setUser(userFound);

        return foundEvent;

    }

    public void findAndDelete(UUID eventId){
        this.eventRepository.deleteById(eventId);
    }



}
