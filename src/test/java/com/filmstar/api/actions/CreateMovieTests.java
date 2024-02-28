package com.filmstar.api.actions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import com.filmstar.api.controllers.commands.DoCreateMoviePostController;
import com.filmstar.api.entities.Movie;
import com.filmstar.api.usecases.CreateMovieUseCase;

@SpringBootTest
class DoCreateMoviePostControllerTest {

    @Mock
    private CreateMovieUseCase createMovieUseCase;

    @InjectMocks
    private DoCreateMoviePostController controller;

    @Test
    void testExecute_ValidMovie_Created() {
        Movie validMovie = new Movie(
        		"Sample Title",
                "Sample Original Title",
                "/path/to/poster.jpg",
                LocalDate.now(),
                "Sample movie overview"
            );

        BindingResult bindingResult = new BeanPropertyBindingResult(validMovie, "validMovie");

        when(createMovieUseCase.execute(any(), any()))
            .thenAnswer(invocation -> {
                Movie movieArgument = invocation.getArgument(0);
                return ResponseEntity.status(HttpStatus.CREATED).body(movieArgument);
            });

        ResponseEntity<?> response = controller.execute(validMovie, bindingResult);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof Movie);
        assertEquals(validMovie, response.getBody());
        verify(createMovieUseCase, times(1)).execute(any(), any());
    }

}
