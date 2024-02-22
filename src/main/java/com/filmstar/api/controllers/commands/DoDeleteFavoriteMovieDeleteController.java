package com.filmstar.api.controllers.commands;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmstar.api.entities.FavoriteMovie;
import com.filmstar.api.entities.Movie;
import com.filmstar.api.entities.User;
import com.filmstar.api.exceptions.MovieNotFoundException;
import com.filmstar.api.services.FavoriteMovieService;
import com.filmstar.api.services.MovieService;

@RestController
@RequestMapping("/api/v1/favorites/{movieId}")
public class DoDeleteFavoriteMovieDeleteController {
	

    private final FavoriteMovieService favoriteMovieService;
    private final MovieService movieService;

    public DoDeleteFavoriteMovieDeleteController(MovieService movieService, FavoriteMovieService favoriteMovieService) {
    	this.movieService = movieService;
		this.favoriteMovieService = favoriteMovieService;
	}


	/**
     * Elimina una película de la lista de favoritos para un usuario.
     *
     * @param movieId ID de la película a eliminar
     * @param user Usuario autenticado
     * @return ResponseEntity con el mensaje y HttpStatus correspondientes
     */
    @DeleteMapping
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
