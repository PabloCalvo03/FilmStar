package com.filmstar.api.controllers.querys;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmstar.api.entities.Movie;
import com.filmstar.api.exceptions.MovieNotFoundException;
import com.filmstar.api.services.MovieService;

/**
 * Controlador obtener pelicula por id.
 */
@RestController
@RequestMapping("/api/v1/movies/{id}")
public class QueryMovieByIdGetController {
	
private final MovieService movieService;
	
	public QueryMovieByIdGetController(MovieService movieService) {
		this.movieService = movieService;
	}
	
	/**
     * Obtiene una película por su ID.
     *
     * @param id ID de la película.
     * @return Película encontrada o mensaje de error si no se encuentra.
     */
	@GetMapping
	public ResponseEntity<?> execute(@PathVariable Integer id){
		try {
			return new ResponseEntity<Movie>(movieService.findById(id), HttpStatus.ACCEPTED);
		} catch(MovieNotFoundException e) {
			throw e;
		}
	}

}
