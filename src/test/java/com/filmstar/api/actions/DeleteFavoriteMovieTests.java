package com.filmstar.api.actions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.filmstar.api.controllers.commands.DoDeleteFavoriteMovieDeleteController;
import com.filmstar.api.entities.FavoriteMovie;
import com.filmstar.api.entities.Movie;
import com.filmstar.api.entities.User;
import com.filmstar.api.services.FavoriteMovieService;
import com.filmstar.api.services.MovieService;
import com.filmstar.api.usecases.DeleteFavoriteMovieUseCase;

@ExtendWith(MockitoExtension.class)
public class DeleteFavoriteMovieTests {

    @Mock
    private MovieService movieService;

    @Mock
    private FavoriteMovieService favoriteMovieService;

    @InjectMocks
    private DeleteFavoriteMovieUseCase deleteFavoriteMovieUseCase;

    @InjectMocks
    private DoDeleteFavoriteMovieDeleteController controller;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testDeleteFavoriteMovie() {
        Integer movieId = 1;
        User user = new User();

        when(movieService.findById(movieId)).thenReturn(new Movie());
        when(favoriteMovieService.removeFavoriteMovie(any(User.class), any(Movie.class))).thenReturn(Optional.of(new FavoriteMovie()));

        ResponseEntity<Map<String, String>> responseEntity = controller.execute(movieId, user);

        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        verify(movieService, times(1)).findById(movieId);
    }
}
