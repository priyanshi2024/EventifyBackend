package com.eventify.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventify.entity.Movie;
import com.eventify.entity.User;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{

	List<Movie> findByUserId(User userId);

}
