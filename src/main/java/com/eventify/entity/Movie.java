package com.eventify.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
    private String description;
    private String genre;
    private String director;
    private String cast;
    private Date releaseDate;
    private String duration;
    private String rating;
    private String language;	
    private String posterUrl;
    private String trailerUrl;
    private String country;
    @Column(nullable = false)
	private Boolean isDeleted = false;
    
    @ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User userId;
}
