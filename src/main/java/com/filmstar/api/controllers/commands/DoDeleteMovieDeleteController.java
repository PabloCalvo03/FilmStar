package com.filmstar.api.controllers.commands;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmstar.api.entities.Movie;
import com.filmstar.api.exceptions.MovieNotFoundException;
import com.filmstar.api.services.MovieService;

@RestController
@RequestMapping("/api/v1/movies/{id}")
public class DoDeleteMovieDeleteController {
	
	private final MovieService movieService;
	
	public DoDeleteMovieDeleteController(MovieService movieService) {
		this.movieService = movieService;
	}

	/**
	 * Elimina una película por su ID.
	 *
	 * @param id ID de la película a eliminar.
	 * @return Respuesta vacía con estado de aceptación o mensaje de error si no se encuentra la película.
	 */
	@DeleteMapping
	public ResponseEntity<?> execute(@PathVariable Integer id) {
	    try {
	        Movie movie = movieService.findById(id);
	        movieService.deleteById(id);  
	        return new ResponseEntity<String>(HttpStatus.ACCEPTED);

	    } catch (MovieNotFoundException e) {
	        throw e;
	    }
	}
	
}
