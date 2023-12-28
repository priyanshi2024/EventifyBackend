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

import com.eventify.entity.Event;
import com.eventify.entity.Movie;
import com.eventify.entity.User;
import com.eventify.service.MovieService;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
	
	@Autowired
    private MovieService movieService;

    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAll();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Movie>> getByUser(@RequestParam User userId) {
        List<Movie> movies = movieService.getByUser(userId);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie, @RequestParam Long userId) {
        String result = movieService.addMovie(movie, userId);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    public ResponseEntity<String> editMovie(@RequestBody Movie movie, @RequestParam Long movieId) {
        String result = movieService.editMovie(movie, movieId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteMovie(@RequestParam Long movieId) {
        String result = movieService.deleteMovie(movieId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
