package com.filmstar.api.controllers;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.filmstar.api.dtos.responses.MovieRatingResponse;
import com.filmstar.api.entities.Movie;
import com.filmstar.api.entities.Rating;
import com.filmstar.api.entities.User;
import com.filmstar.api.exceptions.MovieNotFoundException;
import com.filmstar.api.services.MovieService;

/**
 * Controlador que gestiona las operaciones relacionadas con peliculas.
 */
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
	
	private final MovieService movieService;
	
	public MovieController(MovieService movieService) {
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
	public ResponseEntity<List<Movie>> listMovies(
			@RequestParam(required= false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue= "title") String sortBy,
			@RequestParam(required = false, defaultValue="asc") String sortOrder){
	    Pageable pageable = PageRequest.of(page - 1, 5, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
		return new ResponseEntity<List<Movie>>(movieService.findAll(pageable), HttpStatus.ACCEPTED);
	}
	
	/**
     * Obtiene una película por su ID.
     *
     * @param id ID de la película.
     * @return Película encontrada o mensaje de error si no se encuentra.
     */
	@GetMapping("/{id}")
	public ResponseEntity<?> findMovie(@PathVariable Integer id){
		try {
			return new ResponseEntity<Movie>(movieService.findById(id), HttpStatus.ACCEPTED);
		} catch(MovieNotFoundException e) {
			throw e;
		}
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

	/**
	 * Actualiza los detalles de una película existente.
	 *
	 * @param id            ID de la película a actualizar.
	 * @param updatedMovie  Datos actualizados de la película.
	 * @param bindingResult Resultados de la validación.
	 * @return Película actualizada o mensajes de error si la validación falla o no se encuentra la película.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> updateMovie(@PathVariable Integer id, @Valid @RequestBody Movie updatedMovie, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(err -> err.getDefaultMessage())
                    .collect(Collectors.toList());

            return new ResponseEntity<>(Map.of("errors", errors), HttpStatus.BAD_REQUEST);
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
	
	/**
	 * Elimina una película por su ID.
	 *
	 * @param id ID de la película a eliminar.
	 * @return Respuesta vacía con estado de aceptación o mensaje de error si no se encuentra la película.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable Integer id) {
	    try {
	        Movie movie = movieService.findById(id);
	        movieService.deleteById(id);  
	        return new ResponseEntity<String>(HttpStatus.ACCEPTED);

	    } catch (MovieNotFoundException e) {
	        throw e;
	    }
	}
	
	/**
	 * Realiza una calificación para una película.
	 *
	 * @param id    ID de la película a calificar.
	 * @param score Puntuación de la calificación.
	 * @param user  Usuario autenticado que realiza la calificación.
	 * @return Calificación creada o mensaje de error si no se encuentra la película o el usuario no está autenticado.
	 */
	@PostMapping("/{id}/rate")
    public ResponseEntity<?> rateMovie(
            @PathVariable Integer id,
            @RequestParam int score,
            @AuthenticationPrincipal User user) {
        
        try {
            if (user == null) {
                return new ResponseEntity<>(Map.of("error", "Usuario no autenticado."), HttpStatus.UNAUTHORIZED);
            }

            Rating rating = movieService.rateMovie(id, score, user);
            MovieRatingResponse ratingResponse = new MovieRatingResponse(rating);
            return new ResponseEntity<>(ratingResponse, HttpStatus.CREATED);

        } catch (MovieNotFoundException e) {
            throw e;
        }
    }
}