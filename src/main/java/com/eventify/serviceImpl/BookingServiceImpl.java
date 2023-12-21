package com.eventify.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventify.entity.Booking;
import com.eventify.entity.Event;
import com.eventify.entity.User;
import com.eventify.repository.BookingRepository;
import com.eventify.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Override
	public List<Booking> getAllBookings() {
		return bookingRepository.findAll();
	}

	@Override
	public List<Booking> getByUser(User userId) {
		return bookingRepository.findByUser(userId);
	}

	@Override
	public String registerEvent(Booking booking,Event event ,User user) {
		if (booking != null) {
			booking.setIsCancelled(false);
			bookingRepository.save(booking);
			return "Booking confirmed";
		}
		return "Something went wrong";
	}
}
