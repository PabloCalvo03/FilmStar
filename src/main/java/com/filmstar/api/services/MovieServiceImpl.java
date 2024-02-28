package com.filmstar.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.filmstar.api.entities.Movie;
import com.filmstar.api.entities.Rating;
import com.filmstar.api.entities.User;
import com.filmstar.api.exceptions.MovieNotFoundException;
import com.filmstar.api.repositories.MovieRepository;
import com.filmstar.api.repositories.RatingRepository;

@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	private MovieRepository movieRepository;

	
	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public Page<Movie> findAll(Pageable pageable) {
		return movieRepository.findAll(pageable);
	}

	@Override
	public Movie findById(Integer id){
		Optional<Movie> optionalMovie = movieRepository.findById(id);
	    return optionalMovie.orElseThrow(() -> new MovieNotFoundException("Movie not found with id: " + id));
	}

	@Override
	public Movie save(Movie movie) {
		return movieRepository.save(movie);
	}

	@Override
	public void deleteById(Integer id) {
		this.findById(id);
		movieRepository.deleteById(id);
	}
	
	@Override
    public Rating rateMovie(Integer movieId, int score, User user) {
        Movie movie = findById(movieId);

        Rating rating = new Rating();
        rating.setMovie(movie);
        rating.setUser(user);
        rating.setScore(score);
        return ratingRepository.save(rating);
    }

}
