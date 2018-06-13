package com.springreactive.demo.controllers;

import com.springreactive.demo.model.Event;
import com.springreactive.demo.model.reactiverepository.EventReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class DemoController {
    @Autowired
    EventReactiveRepository eventReactiveRepository;

    //EVENTS
    @GetMapping("/events")
    public Flux<Event> getAllEvents() {
        return eventReactiveRepository.findAll();
    }
    // Events are Sent to the client as Server Sent Events.
    @GetMapping(value = "/stream/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Event> streamAllEvents() {
        return eventReactiveRepository.findAll();
    }

    @GetMapping("/event/{id}")
    public Mono<ResponseEntity<Event>> getVenueByID(@PathVariable(value = "id") String id) {
        return eventReactiveRepository.findById(id)
                .map(venue -> ResponseEntity.ok(venue))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


}
