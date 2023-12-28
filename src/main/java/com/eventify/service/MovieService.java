package com.eventify.service;

import java.util.List;

import com.eventify.entity.Movie;
import com.eventify.entity.User;

public interface MovieService {

	public List<Movie> getAll();
	public List<Movie> getByUser(User userId);
	public String addMovie(Movie movie,Long userId);
	public String editMovie(Movie movie,Long movieId);
	public String deleteMovie(Long movieId);
}
