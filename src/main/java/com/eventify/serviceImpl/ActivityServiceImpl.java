package com.eventify.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventify.constants.PortalConstants;
import com.eventify.entity.Activity;
import com.eventify.entity.Event;
import com.eventify.entity.User;
import com.eventify.repository.ActivityRepository;
import com.eventify.repository.EventRepository;
import com.eventify.repository.UserRepository;
import com.eventify.service.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	ActivityRepository activityRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	EmailSender emailSender;

	@Override
	public List<Activity> getAll() {
		return activityRepository.findAll();
	}

	@Override
	public List<Activity> getByUser(User userId) {
		List<Activity> activities = activityRepository.findByUserId(userId);
		return activities;
	}

	@Override
	public String addActivity(Activity activity, Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			activity.setUserId(user);
			activityRepository.save(activity);
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
			return "Activity Added";
		}
		return "Invalid User";
	}

	@Override
	public String editActivity(Activity activity, Long activityId) {
		Optional<Activity> optionalActivity = activityRepository.findById(activityId);
		if (optionalActivity.isPresent()) {
			Activity existingActivity = optionalActivity.get();
			existingActivity.setDate(activity.getDate());
			existingActivity.setDescription(activity.getDescription());
			existingActivity.setLocation(activity.getLocation());
			existingActivity.setName(activity.getName());
			existingActivity.setType(activity.getType());
			existingActivity.setUserId(existingActivity.getUserId());		
			activityRepository.save(existingActivity);
			return "Activity Updated";
		}
		return "Activity not found";
	}

	@Override
	public String deleteActivity(Long activityId) {
		Optional<Activity> optionalActivity = activityRepository.findById(activityId);

		if (optionalActivity.isPresent()) {
			Activity activity = optionalActivity.get();
			activity.setIsDeleted(true);
			activityRepository.save(activity);
			return "Activity deleted successfully";
		} else {
			return "Activity not found";
		}
	}
}
