package com.eventify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eventify.entity.Event;
import com.eventify.entity.User;
import com.eventify.service.EventService;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/all")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAll();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Event>> getByUser(@RequestParam User userId) {
        List<Event> events = eventService.getByUser(userId);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> addEvent(@RequestBody Event event, @RequestParam Long userId) {
        String result = eventService.addEvent(event, userId);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    public ResponseEntity<String> editEvent(@RequestBody Event event, @RequestParam Long eventId) {
        String result = eventService.editEvent(event, eventId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteEvent(@RequestParam Long eventId) {
        String result = eventService.deleteEvent(eventId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
