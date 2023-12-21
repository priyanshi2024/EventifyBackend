package com.eventify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eventify.entity.Booking;
import com.eventify.entity.Event;
import com.eventify.entity.User;
import com.eventify.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/all")
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Booking>> getByUser(@RequestParam User userId) {
        List<Booking> bookings = bookingService.getByUser(userId);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerEvent(@RequestBody Booking booking ,@RequestParam Event event , @RequestParam User user ) {
        String result = bookingService.registerEvent(booking,event,user);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
