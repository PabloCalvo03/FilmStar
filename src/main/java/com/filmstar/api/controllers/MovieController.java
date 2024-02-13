package com.filmstar.api.controllers;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.filmstar.api.entities.Movie;
import com.filmstar.api.entities.Rating;
import com.filmstar.api.entities.User;
import com.filmstar.api.services.MovieService;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@GetMapping
	public ResponseEntity<List<Movie>> listMovies(
			@RequestParam(required= false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue= "title") String sortBy,
			@RequestParam(required = false, defaultValue="asc") String sortOrder){
	    Pageable pageable = PageRequest.of(page - 1, 5, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
		return new ResponseEntity<List<Movie>>(movieService.findAll(pageable), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findMovie(@PathVariable Integer id){
		try {
			return new ResponseEntity<Movie>(movieService.findById(id), HttpStatus.ACCEPTED);
		} catch(NoSuchElementException e) {
			return new ResponseEntity<Map<String, String>>(Map.of("error", "No se ha encontrado la pelicula."), HttpStatus.NOT_FOUND);
		}
	}

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
	    
		} catch(NoSuchElementException e) {
			return new ResponseEntity<Map<String, String>>(Map.of("error", "No se ha encontrado la pelicula."), HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable Integer id){
		try {
			Movie movie = movieService.findById(id);
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
			
		} catch(NoSuchElementException e) {
			return new ResponseEntity<Map<String, String>>(Map.of("error", "No se ha encontrado la pelicula."), HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping("/{id}/rate")
    public ResponseEntity<?> rateMovie(
            @PathVariable Integer id,
            @RequestParam int score,
            @AuthenticationPrincipal User user) {
        
        try {
            if (user == null) {
                return new ResponseEntity<>(Map.of("error", "User not authenticated"), HttpStatus.UNAUTHORIZED);
            }

            // userDetails.getUsername() will give you the username of the authenticated user
            Rating rating = movieService.rateMovie(id, score, user);
            return new ResponseEntity<>(rating, HttpStatus.CREATED);

        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(Map.of("error", "No se ha encontrado la pelicula."), HttpStatus.NOT_FOUND);
        }
    }
}