package com.filmstar.api.controllers.commands;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	public ResponseEntity<?> createMovie(@Valid @RequestBody Movie movie, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(err -> err.getDefaultMessage())
                    .collect(Collectors.toList());

            return new ResponseEntity<>(Map.of("errors", errors), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(movieService.save(movie), HttpStatus.CREATED);
    }
	
}
