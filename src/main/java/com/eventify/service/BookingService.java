package com.eventify.service;

import java.util.List;

import com.eventify.entity.Booking;
import com.eventify.entity.Event;
import com.eventify.entity.User;

public interface BookingService {

	public List<Booking> getAllBookings();
	public List<Booking> getByUser(User userId);
	public String registerEvent(Booking booking,Event event,User user);


}
