package com.filmstar.api.favorites;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.filmstar.api.controllers.FavoriteMovieController;
import com.filmstar.api.dtos.responses.MovieFavoriteResponse;
import com.filmstar.api.entities.FavoriteMovie;
import com.filmstar.api.entities.Movie;
import com.filmstar.api.entities.User;
import com.filmstar.api.services.FavoriteMovieService;
import com.filmstar.api.services.MovieService;


public class FavoriteControllerTest {
	
	@Test
    void testGetFavorites() {
        MovieService movieService = mock(MovieService.class);

        when(movieService.findById(1)).thenReturn(new Movie());
        when(movieService.findById(2)).thenReturn(new Movie());

        FavoriteMovieService favoriteMovieService = mock(FavoriteMovieService.class);

        FavoriteMovieController favoriteMovieController = new FavoriteMovieController(movieService, favoriteMovieService);

        User user = new User();
        user.setId(123);
        user.setEmail("ejemplo_usuario");

        FavoriteMovie favorite1 = new FavoriteMovie();
        favorite1.setId(1L);
        favorite1.setMovie(movieService.findById(1));
        
        FavoriteMovie favorite2 = new FavoriteMovie();
        favorite2.setId(2L);
        favorite2.setMovie(movieService.findById(2));
        List<FavoriteMovie> favorites = Arrays.asList(favorite1, favorite2);

        when(favoriteMovieService.getFavoritesByUser(user)).thenReturn(favorites);

    }
	@Test
    void testAddFavorite() {
        MovieService movieService = mock(MovieService.class);
        FavoriteMovieService favoriteMovieService = mock(FavoriteMovieService.class);
        FavoriteMovieController favoriteMovieController = new FavoriteMovieController(movieService, favoriteMovieService);

        Movie movie = new Movie();
        movie.setId(1);
        movie.setTitle("Ejemplo de Película");
        User user = new User();
        user.setId(123);
        user.setEmail("ejemplo_usuario");

        when(movieService.findById(1)).thenReturn(movie);
        when(favoriteMovieService.isMovieInFavorites(user, movie)).thenReturn(false);

        ResponseEntity<?> responseEntity = favoriteMovieController.addFavorite(1, user);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        verify(movieService).findById(1);
        verify(favoriteMovieService).isMovieInFavorites(user, movie);
        verify(favoriteMovieService).addFavoriteMovie(user, movie);

        assertTrue(responseEntity.getBody() instanceof Map);
        assertEquals("Pelicula añadida a favoritos.", ((Map<?, ?>) responseEntity.getBody()).get("message"));
    }
	
	@Test
    void testRemoveFavorite() {
        MovieService movieService = mock(MovieService.class);
        FavoriteMovieService favoriteMovieService = mock(FavoriteMovieService.class);
        FavoriteMovieController favoriteMovieController = new FavoriteMovieController(movieService, favoriteMovieService);

        Movie movie = new Movie();
        movie.setId(1);
        movie.setTitle("Ejemplo de Película");
        User user = new User();
        user.setId(123);
        user.setEmail("ejemplo_usuario");

        when(movieService.findById(1)).thenReturn(movie);
        when(favoriteMovieService.removeFavoriteMovie(user, movie)).thenReturn(Optional.of(new FavoriteMovie()));

        ResponseEntity<?> responseEntity = favoriteMovieController.removeFavorite(1, user);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}


}
