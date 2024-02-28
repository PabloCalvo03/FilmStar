package com.filmstar.api.actions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import com.filmstar.api.controllers.commands.DoCreateMoviePostController;
import com.filmstar.api.entities.Movie;
import com.filmstar.api.usecases.CreateMovieUseCase;


@SpringBootTest
class CreateMovieTests {

    @Mock
    private CreateMovieUseCase createMovieUseCase;

    @InjectMocks
    private DoCreateMoviePostController controller;

    @Test
    void testExecute_ValidMovie_Created() {
        Movie validMovie = createValidMovie();
        BindingResult bindingResult = new BeanPropertyBindingResult(validMovie, "validMovie");

        Mockito.<ResponseEntity<?>>when(createMovieUseCase.execute(any(Movie.class), any(BindingResult.class)))
        .thenReturn(new ResponseEntity<>(validMovie, HttpStatus.CREATED));

        ResponseEntity<?> responseEntity = controller.execute(validMovie, bindingResult);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(validMovie, responseEntity.getBody());
        verify(createMovieUseCase, times(1)).execute(any(Movie.class), any(BindingResult.class));
    }
    
    @Test
    void testExecute_CreateMovieUseCaseFails_BadRequest() {
        Movie invalidMovie = createInvalidMovie();
        BindingResult bindingResult = new BeanPropertyBindingResult(invalidMovie, "invalidMovie");

        System.out.println("Before controller method - Validation errors: " + bindingResult.getAllErrors());

        Mockito.<ResponseEntity<?>>when(createMovieUseCase.execute(any(Movie.class), any(BindingResult.class)))
                .thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

        ResponseEntity<?> responseEntity = controller.execute(invalidMovie, bindingResult);

        System.out.println("After controller method - Validation errors: " + bindingResult.getAllErrors());

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());

        // Debugging statements
        System.out.println("Validation errors: " + bindingResult.getAllErrors());
        verify(createMovieUseCase, times(1)).execute(any(Movie.class), any(BindingResult.class));
    }


    private Movie createValidMovie() {
        return new Movie(
                "Test Movie",
                "Test Original Title",
                "/path/to/poster.jpg",
                LocalDate.now(),
                "This is a test movie overview. It can be up to 1000 characters long. ".repeat(10)
        );
    }
    
    private Movie createInvalidMovie() {
        return new Movie(
                "",
                "Test Original Title",
                "/path/to/poster.jpg",
                LocalDate.now(),
                "This is a test movie overview. It can be up to 1000 characters long. ".repeat(10)
        );
    }
}
