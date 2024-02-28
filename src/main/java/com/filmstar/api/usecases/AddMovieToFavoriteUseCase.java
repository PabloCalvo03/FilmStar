package com.filmstar.api.usecases;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.filmstar.api.entities.Movie;
import com.filmstar.api.entities.User;
import com.filmstar.api.services.FavoriteMovieService;
import com.filmstar.api.services.MovieService;

@Component
public class AddMovieToFavoriteUseCase {

	private final FavoriteMovieService favoriteMovieService;
    private final MovieService movieService;

    public AddMovieToFavoriteUseCase(MovieService movieService, FavoriteMovieService favoriteMovieService) {
    	this.movieService = movieService;
		this.favoriteMovieService = favoriteMovieService;
	}

    public ResponseEntity<Map<String, String>> execute(Integer movieId, User user) {
    	Movie movie = movieService.findById(movieId);
    	if(favoriteMovieService.isMovieInFavorites(user, movie)) {
	        return new ResponseEntity<Map<String, String>>(Map.of("message", "Movie with id: " + movie.getId() + " was already in favorites"), HttpStatus.ACCEPTED);
        }
        favoriteMovieService.addFavoriteMovie(user, movie);
        return new ResponseEntity<Map<String, String>>(Map.of("message", "Movie with id: " + movie.getId() + " added to favorites"), HttpStatus.CREATED);
	
    }
}
