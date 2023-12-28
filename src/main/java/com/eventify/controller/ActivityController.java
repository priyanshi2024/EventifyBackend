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

import com.eventify.entity.Activity;
import com.eventify.entity.User;
import com.eventify.service.ActivityService;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

	@Autowired
    private ActivityService activityService;

    @GetMapping("/all")
    public ResponseEntity<List<Activity>> getAllMovies() {
        List<Activity> activities = activityService.getAll();
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Activity>> getByUser(@RequestParam User userId) {
        List<Activity> activities = activityService.getByUser(userId);
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> addActivity(@RequestBody Activity activity, @RequestParam Long userId) {
        String result = activityService.addActivity(activity, userId);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    public ResponseEntity<String> editActivity(@RequestBody Activity activity, @RequestParam Long activityId) {
        String result = activityService.editActivity(activity, activityId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteActivity(@RequestParam Long activityId) {
        String result = activityService.deleteActivity(activityId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
