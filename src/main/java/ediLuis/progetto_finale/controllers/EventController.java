package ediLuis.progetto_finale.controllers;


import ediLuis.progetto_finale.entities.Event;
import ediLuis.progetto_finale.payloads.eventPayloads.EventDTO;
import ediLuis.progetto_finale.payloads.eventPayloads.EventRespDTO;
import ediLuis.progetto_finale.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    @PreAuthorize("hasAuthority('ORGANIZER')")
    public Page<Event> findAll(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5") int size){
        return this.eventService.findAll(page, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ORGANIZER')")
    public EventRespDTO saveNewEvent(@RequestBody EventDTO body){
        return this.eventService.saveNewEvent(body);
    }

    @PutMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ORGANIZER')")
    public Event findAndUpdate(@PathVariable UUID eventId, @RequestBody EventDTO body){
        return this.eventService.findAndUpdate(eventId, body);
    }

    @DeleteMapping("/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ORGANIZER')")
    public void findAndDelete(@PathVariable UUID eventId){
        this.eventService.findAndDelete(eventId);
    }


}
