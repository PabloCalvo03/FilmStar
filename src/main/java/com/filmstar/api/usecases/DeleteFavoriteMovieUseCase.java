package com.filmstar.api.usecases;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.filmstar.api.entities.FavoriteMovie;
import com.filmstar.api.entities.Movie;
import com.filmstar.api.entities.User;
import com.filmstar.api.services.FavoriteMovieService;
import com.filmstar.api.services.MovieService;

@Component
public class DeleteFavoriteMovieUseCase {
	
	private final FavoriteMovieService favoriteMovieService;
    private final MovieService movieService;

    public DeleteFavoriteMovieUseCase(MovieService movieService, FavoriteMovieService favoriteMovieService) {
    	this.movieService = movieService;
		this.favoriteMovieService = favoriteMovieService;
	}

    public ResponseEntity<Map<String, String>> execute(Long movieId, User user) {
    	Movie movie = movieService.findById(movieId);
        Optional<FavoriteMovie> favoriteMovie = favoriteMovieService.removeFavoriteMovie(user, movie);
        
        if(favoriteMovie.isEmpty()) {
	        return new ResponseEntity<Map<String, String>>(Map.of("message", "Movie with id: " + movie.getId() + " is not in favorites."), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<Map<String, String>>(Map.of("message", "Movie with id: " + movie.getId() + " deleted from favorites"), HttpStatus.ACCEPTED);

    }

}
