package com.filmstar.api.controllers.querys;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.filmstar.api.entities.Movie;
import com.filmstar.api.services.MovieService;

/**
 * Controlador para listar todas peliculas.
 */
@RestController
@RequestMapping("/api/v1/movies")
public class QueryListMovieGetController {

private final MovieService movieService;
	
	public QueryListMovieGetController(MovieService movieService) {
		this.movieService = movieService;
	}
	
	/**
     * Obtiene la lista de películas paginada y ordenada.
     *
     * @param page      Número de página.
     * @param sortBy    Campo por el cual se ordenarán las películas.
     * @param sortOrder Dirección de la ordenación (ascendente o descendente).
     * @return Lista paginada y ordenada de películas.
     */
	@GetMapping
	public ResponseEntity<List<Movie>> execute(
			@RequestParam(required= false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue= "title") String sortBy,
			@RequestParam(required = false, defaultValue="asc") String sortOrder){
	    Pageable pageable = PageRequest.of(page - 1, 5, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
		return new ResponseEntity<List<Movie>>(movieService.findAll(pageable), HttpStatus.ACCEPTED);
	}
	
}
