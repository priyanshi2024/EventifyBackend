package com.eventify.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
	
	private Date bookingDate;
	private BigDecimal amount;
	private Boolean status;
	private String note;
	private Boolean isCancelled;
	private String cancellationReason;
	
}
