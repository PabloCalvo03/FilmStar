package com.filmstar.api.controllers;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmstar.api.dtos.responses.MovieFavoriteResponse;
import com.filmstar.api.entities.FavoriteMovie;
import com.filmstar.api.entities.Movie;
import com.filmstar.api.entities.User;
import com.filmstar.api.exceptions.MovieNotFoundException;
import com.filmstar.api.services.FavoriteMovieService;
import com.filmstar.api.services.MovieService;

/**
 * Controlador que gestiona las peliculas en favoritos de los usuarios.
 */
@RestController
@RequestMapping("/api/v1/favorites")
public class FavoriteMovieController {

    private final FavoriteMovieService favoriteMovieService;
    private final MovieService movieService;

    public FavoriteMovieController(MovieService movieService, FavoriteMovieService favoriteMovieService) {
    	this.movieService = movieService;
		this.favoriteMovieService = favoriteMovieService;
	}

	

	/**
     * Obtiene la lista de películas favoritas para un usuario.
     *
     * @param user Usuario autenticado
     * @return ResponseEntity con la lista de respuestas MovieFavoriteResponse y HttpStatus OK
     */
    @GetMapping
    public ResponseEntity<List<MovieFavoriteResponse>> getFavorites(@AuthenticationPrincipal User user) {
    	List<FavoriteMovie> favorites = favoriteMovieService.getFavoritesByUser(user);
        List<MovieFavoriteResponse> responseList = favorites.stream()
                .map(MovieFavoriteResponse::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    /**
     * Añade una película a la lista de favoritos para un usuario.
     *
     * @param movieId ID de la película a añadir
     * @param user Usuario autenticado
     * @return ResponseEntity con el mensaje y HttpStatus correspondientes
     */
    @PostMapping("/{movieId}")
    public ResponseEntity<?> addFavorite(
            @PathVariable Integer movieId,
            @AuthenticationPrincipal User user) {
		try {
	    	Movie movie = movieService.findById(movieId);
	    	if(favoriteMovieService.isMovieInFavorites(user, movie)) {
		        return new ResponseEntity<Map<String, String>>(Map.of("message", "La pelicula ya esta en favoritos."), HttpStatus.CREATED);
	        }
	        favoriteMovieService.addFavoriteMovie(user, movie);
	        return new ResponseEntity<Map<String, String>>(Map.of("message", "Pelicula añadida a favoritos."), HttpStatus.CREATED);
		} catch(MovieNotFoundException e) {
			throw e;
		}
    }

    /**
     * Elimina una película de la lista de favoritos para un usuario.
     *
     * @param movieId ID de la película a eliminar
     * @param user Usuario autenticado
     * @return ResponseEntity con el mensaje y HttpStatus correspondientes
     */
    @DeleteMapping("/{movieId}")
    public ResponseEntity<?> removeFavorite(
            @PathVariable Integer movieId,
            @AuthenticationPrincipal User user) {
    	try {
	    	Movie movie = movieService.findById(movieId);
	        Optional<FavoriteMovie> favoriteMovie = favoriteMovieService.removeFavoriteMovie(user, movie);
	        if(favoriteMovie.isEmpty()) {
		        return new ResponseEntity<Map<String, String>>(Map.of("message", "La pelicula ya estaba borrada de favoritas o nunca estuvo en favoritos"), HttpStatus.OK);
	        }
	        return new ResponseEntity<Map<String, String>>(Map.of("message", "Pelicula borrada de favoritos."), HttpStatus.OK);
	    } catch(MovieNotFoundException e) {
	    	throw e;
	    }
    }
}
