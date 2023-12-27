package com.eventify.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventify.entity.Activity;
import com.eventify.entity.User;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>{

	List<Activity> findByUserId(User userId);

	
}
