package com.eventify.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventify.constants.PortalConstants;
import com.eventify.entity.Event;
import com.eventify.entity.User;
import com.eventify.repository.EventRepository;
import com.eventify.repository.UserRepository;
import com.eventify.service.EventService;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	EventRepository eventRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	EmailSender emailSender;

	public List<Event> getAll() {
		return eventRepository.findAll();
	}

	@Override
	public List<Event> getByUser(User userId) {
		List<Event> events = eventRepository.findByUserId(userId);
		return events;
	}

	@Override
	public String addEvent(Event event, Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			event.setUserId(user);
			eventRepository.save(event);
			EmailTemplateRenderer emailTemplateRenderer = new EmailTemplateRenderer();
			String recipient = PortalConstants.EMAIL_RECIPIENT;
			String subject = "Event Added";
			HashMap<String, String> map = new HashMap<>();
//			map.put("RMA_QUALIFIER", getRmaaQualifier());
			String template = emailTemplateRenderer.getTemplateContent();
			try {
				emailSender.sendEmail(recipient, template, subject, map);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			return "Event Added";
		}
		return "Invalid User";
	}

	@Override
	public String editEvent(Event event, Long eventId) {
		Optional<Event> optionalEvent = eventRepository.findById(eventId);
		if (optionalEvent.isPresent()) {
			Event existingEvent = optionalEvent.get();
			existingEvent.setEventName(event.getEventName());
			existingEvent.setDescription(event.getDescription());
			existingEvent.setStartDate(event.getStartDate());
			existingEvent.setEndDate(event.getEndDate());
			existingEvent.setLocation(event.getLocation());
			existingEvent.setImage(event.getImage());
			existingEvent.setFee(event.getFee());
			existingEvent.setDuration(event.getDuration());
			existingEvent.setUserId(existingEvent.getUserId());
			eventRepository.save(existingEvent);
			return "Event Updated";
		}
		return "Event not found";
	}

	@Override
	public String deleteEvent(Long eventId) {
		Optional<Event> optionalEvent = eventRepository.findById(eventId);

		if (optionalEvent.isPresent()) {
			Event event = optionalEvent.get();
			event.setIsDeleted(false);
			eventRepository.save(event);
			return "Event deleted successfully";
		} else {
			return "Event not found";
		}
	}

}
