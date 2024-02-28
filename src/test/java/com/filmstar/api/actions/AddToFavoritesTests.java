package com.filmstar.api.actions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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

import com.filmstar.api.controllers.commands.DoAddMovieToFavoritePostController;
import com.filmstar.api.entities.User;
import com.filmstar.api.usecases.AddMovieToFavoriteUseCase;

@SpringBootTest
public class AddToFavoritesTests {
	
    @Mock
    private AddMovieToFavoriteUseCase addMovieToFavoriteUseCase;
    
    @InjectMocks
    private DoAddMovieToFavoritePostController addMovieToFavoritesController;
	
	@Test
    void testExecute_AddToFavorite_Success() {
        Integer movieId = 1;
        User user = new User(); // Assuming User class exists

        when(addMovieToFavoriteUseCase.execute(anyInt(), any(User.class)))
                .thenReturn(new ResponseEntity<>(Map.of("message", "Movie with id: 1 added to favorites"), HttpStatus.CREATED));

        ResponseEntity<Map<String, String>> response = addMovieToFavoritesController.execute(movieId, user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Movie with id: 1 added to favorites", response.getBody().get("message"));
        verify(addMovieToFavoriteUseCase, times(1)).execute(movieId, user);
    }

    @Test
    void testExecute_MovieAlreadyInFavorites_Accepted() {
        Integer movieId = 1;
        User user = new User(); // Assuming User class exists

        when(addMovieToFavoriteUseCase.execute(anyInt(), any(User.class)))
                .thenReturn(new ResponseEntity<>(Map.of("message", "Movie with id: 1 was already in favorites"), HttpStatus.ACCEPTED));

        ResponseEntity<Map<String, String>> response = addMovieToFavoritesController.execute(movieId, user);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("Movie with id: 1 was already in favorites", response.getBody().get("message"));
        verify(addMovieToFavoriteUseCase, times(1)).execute(movieId, user);
    }

}
