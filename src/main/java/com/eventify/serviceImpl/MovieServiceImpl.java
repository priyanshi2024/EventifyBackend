package com.eventify.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventify.constants.PortalConstants;
import com.eventify.entity.Movie;
import com.eventify.entity.User;
import com.eventify.repository.MovieRepository;
import com.eventify.repository.UserRepository;
import com.eventify.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
	@Autowired
	MovieRepository movieRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	EmailSender emailSender;

	@Override
	public List<Movie> getAll() {
		return movieRepository.findAll();
	}

	@Override
	public List<Movie> getByUser(User userId) {
		List<Movie> movies = movieRepository.findByUserId(userId);
		return movies;
	}

	@Override
	public String addMovie(Movie movie, Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			movie.setUserId(user);
			movieRepository.save(movie);
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
			return "Movie Added";
		}
		return "Invalid User";
	}

	@Override
	public String editMovie(Movie movie, Long movieId) {
		Optional<Movie> optionalMovie = movieRepository.findById(movieId);
		if (optionalMovie.isPresent()) {
			Movie existingMovie = optionalMovie.get();
			existingMovie.setTitle(movie.getTitle());
			existingMovie.setDescription(movie.getDescription());
			existingMovie.setGenre(movie.getGenre());
			existingMovie.setDirector(movie.getDirector());
			existingMovie.setCast(movie.getCast());
			existingMovie.setReleaseDate(movie.getReleaseDate());
			existingMovie.setDuration(movie.getDuration());
			existingMovie.setRating(movie.getRating());
			existingMovie.setLanguage(movie.getLanguage());
			existingMovie.setPosterUrl(movie.getPosterUrl());
			existingMovie.setTrailerUrl(movie.getTrailerUrl());
			existingMovie.setCountry(movie.getCountry());
			existingMovie.setLocation(movie.getLocation());
			existingMovie.setUserId(existingMovie.getUserId());

			// Save the updated movie entity
			movieRepository.save(existingMovie);

			return "Movie Updated";
		}
		return "Movie not found";
	}

	@Override
	public String deleteMovie(Long movieId) {
		Optional<Movie> optionalMovie = movieRepository.findById(movieId);

		if (optionalMovie.isPresent()) {
			Movie movie = optionalMovie.get();
			movie.setIsDeleted(true);
			movieRepository.save(movie);
			return "Movie deleted successfully";
		} else {
			return "Movie not found";
		}
	}

}
