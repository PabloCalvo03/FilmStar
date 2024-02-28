package com.filmstar.api.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.filmstar.api.entities.Movie;
import com.filmstar.api.entities.Rating;
import com.filmstar.api.entities.User;

public interface MovieService {

	Page<Movie> findAll(Pageable pageable);
	Movie findById(Long id);
	Movie save(Movie movie);
	void deleteById(Long id);
	Rating rateMovie(Long movieId, int score, User user);
	
}
