package com.filmstar.api.usecases;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.filmstar.api.entities.Movie;
import com.filmstar.api.services.MovieService;
import com.filmstar.api.util.EntityValidator;

@Component
public class UpdateMovieUseCase {

	private final MovieService movieService;
	
	public UpdateMovieUseCase(MovieService movieService) {
		this.movieService = movieService;
	}

	public ResponseEntity<?> execute(Integer id, Movie updatedMovie, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(EntityValidator.execute(bindingResult), HttpStatus.BAD_REQUEST);
		}

		Movie existingMovie = movieService.findById(id);

        existingMovie.setTitle(updatedMovie.getTitle());
        existingMovie.setOriginalTitle(updatedMovie.getOriginalTitle());
        existingMovie.setPosterPath(updatedMovie.getPosterPath());
        existingMovie.setReleaseDate(updatedMovie.getReleaseDate());
        existingMovie.setOverview(updatedMovie.getOverview());

        return new ResponseEntity<>(movieService.save(existingMovie), HttpStatus.OK);
	}
}
