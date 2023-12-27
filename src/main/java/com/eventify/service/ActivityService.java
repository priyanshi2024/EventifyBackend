package com.eventify.service;

import java.util.List;

import com.eventify.entity.Activity;
import com.eventify.entity.User;

public interface ActivityService {

	public List<Activity> getAll();
	public List<Activity> getByUser(User userId);
	public String addActivity(Activity activity,Long userId);
	public String editActivity(Activity activity,Long activityId);
	public String deleteActivity(Long activityId);
}
