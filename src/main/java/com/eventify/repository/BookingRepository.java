package com.eventify.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventify.entity.Booking;
import com.eventify.entity.User;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{

	List<Booking> findByUser(User userId);

}
