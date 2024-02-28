package com.filmstar.api.actions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.filmstar.api.controllers.commands.DoDeleteFavoriteMovieDeleteController;
import com.filmstar.api.entities.Movie;
import com.filmstar.api.entities.User;
import com.filmstar.api.usecases.DeleteFavoriteMovieUseCase;

@SpringBootTest
public class DeleteFavoriteMovieTests {

    @Mock
    private DeleteFavoriteMovieUseCase deleteFavoriteMovieUseCase;

    @InjectMocks
    private DoDeleteFavoriteMovieDeleteController controller;


    @Test
    public void testExecute_DeleteFavoriteMovie_Accepted() {
        Long movieId = 1L;
        User user = new User();

        Movie movie = new Movie();
        when(deleteFavoriteMovieUseCase.execute(movieId, user)).thenReturn(new ResponseEntity<>(Map.of("message", "Movie with id: 1 deleted from favorites"), HttpStatus.ACCEPTED));

        ResponseEntity<Map<String, String>> responseEntity = controller.execute(movieId, user);

        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        assertEquals("Movie with id: 1 deleted from favorites", responseEntity.getBody().get("message"));
        verify(deleteFavoriteMovieUseCase, times(1)).execute(movieId, user);
    }
}
