package com.filmstar.api.controllers.commands;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmstar.api.entities.Movie;
import com.filmstar.api.exceptions.MovieNotFoundException;
import com.filmstar.api.services.MovieService;
import com.filmstar.api.util.EntityValidator;

@RestController
@RequestMapping("/api/v1/movies/{id}")
public class DoUpdateMoviePutController {
	
	private final MovieService movieService;
	
	public DoUpdateMoviePutController(MovieService movieService) {
		this.movieService = movieService;
	}
	
	/**
	 * Actualiza los detalles de una película existente.
	 *
	 * @param id            ID de la película a actualizar.
	 * @param updatedMovie  Datos actualizados de la película.
	 * @param bindingResult Resultados de la validación.
	 * @return Película actualizada o mensajes de error si la validación falla o no se encuentra la película.
	 */
	@PutMapping
	public ResponseEntity<?> execute(@PathVariable Integer id, @Valid @RequestBody Movie updatedMovie, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(EntityValidator.execute(bindingResult), HttpStatus.BAD_REQUEST);
		}

		try {
			Movie existingMovie = movieService.findById(id);

	        existingMovie.setTitle(updatedMovie.getTitle());
	        existingMovie.setOriginalTitle(updatedMovie.getOriginalTitle());
	        existingMovie.setPosterPath(updatedMovie.getPosterPath());
	        existingMovie.setReleaseDate(updatedMovie.getReleaseDate());
	        existingMovie.setOverview(updatedMovie.getOverview());

	        return new ResponseEntity<>(movieService.save(existingMovie), HttpStatus.OK);
	    
		} catch(MovieNotFoundException e) {
			throw e;
		}
	}

}
