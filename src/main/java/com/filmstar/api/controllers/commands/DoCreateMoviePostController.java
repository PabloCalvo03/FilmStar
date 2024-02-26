package com.filmstar.api.controllers.commands;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmstar.api.entities.Movie;
import com.filmstar.api.services.MovieService;
import com.filmstar.api.util.EntityValidator;

@RestController
@RequestMapping("/api/v1/movies")
public class DoCreateMoviePostController {

	private final MovieService movieService;
	
	public DoCreateMoviePostController(MovieService movieService) {
		this.movieService = movieService;
	}
	

	/**
     * Crea una nueva película.
     *
     * @param movie          Datos de la película a crear.
     * @param bindingResult  Resultados de la validación.
     * @return Película creada o mensajes de error si la validación falla.
     */
	@PostMapping
	public ResponseEntity<?> execute(@Valid @RequestBody Movie movie, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(EntityValidator.execute(bindingResult), HttpStatus.BAD_REQUEST);
		}
		
        return new ResponseEntity<>(movieService.save(movie), HttpStatus.CREATED);
    }
	
}
