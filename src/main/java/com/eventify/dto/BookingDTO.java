package com.eventify.dto;

import java.util.Date;

import com.eventify.entity.Event;
import com.eventify.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {

	private Event event;
	private User user;
	private Date bookingDate;
	private Boolean status;
	private String note;
	private Boolean isCancelled;
	private String cancellationReason;
	private String bookingId;
	private Integer ticketQty;

}
