package com.filmstar.api.controllers.querys;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmstar.api.dtos.responses.MovieFavoriteResponse;
import com.filmstar.api.entities.FavoriteMovie;
import com.filmstar.api.entities.User;
import com.filmstar.api.services.FavoriteMovieService;
import com.filmstar.api.services.MovieService;

/**
 * Controlador que gestiona las peliculas en favoritos de los usuarios.
 */
@RestController
@RequestMapping("/api/v1/favorites")
public class QueryFavoriteMoviesGetController {
	
	private final FavoriteMovieService favoriteMovieService;

    public QueryFavoriteMoviesGetController(MovieService movieService, FavoriteMovieService favoriteMovieService) {
		this.favoriteMovieService = favoriteMovieService;
	}

	/**
     * Obtiene la lista de películas favoritas para un usuario.
     *
     * @param user Usuario autenticado
     * @return ResponseEntity con la lista de respuestas MovieFavoriteResponse y HttpStatus OK
     */
    @GetMapping
    public ResponseEntity<List<MovieFavoriteResponse>> execute(@AuthenticationPrincipal User user) {
    	List<FavoriteMovie> favorites = favoriteMovieService.getFavoritesByUser(user);
        List<MovieFavoriteResponse> responseList = favorites.stream()
                .map(MovieFavoriteResponse::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }
	
}
