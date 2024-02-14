package com.filmstar.api.movies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.BindingResult;

import com.filmstar.api.controllers.MovieController;
import com.filmstar.api.dtos.responses.MovieRatingResponse;
import com.filmstar.api.entities.Movie;
import com.filmstar.api.entities.Rating;
import com.filmstar.api.entities.User;
import com.filmstar.api.services.MovieService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MovieControllerTest {

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;

    @Test
    void testListMovies() {
        MovieService movieService = mock(MovieService.class);
        MovieController movieController = new MovieController(movieService);

        when(movieService.findAll(any(Pageable.class))).thenReturn(List.of(new Movie(), new Movie()));

        ResponseEntity<List<Movie>> responseEntity = movieController.listMovies(1, "title", "asc");

        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(2, responseEntity.getBody().size());

        verify(movieService).findAll(PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "title")));
    }

    @Test
    void testFindMovie() {
        int movieId = 1;
        Movie mockMovie = new Movie();
        when(movieService.findById(movieId)).thenReturn(mockMovie);

        ResponseEntity<?> responseEntity = movieController.findMovie(movieId);

        verify(movieService, times(1)).findById(movieId);
        assertSame(mockMovie, responseEntity.getBody());
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
    }

    @Test
    void testCreateMovie() {
    	MovieService movieService = mock(MovieService.class);
    	MovieController movieController = new MovieController(movieService);

        Movie movieToCreate = new Movie(); 
        ResponseEntity<?> responseEntity = movieController.createMovie(movieToCreate, mock(BindingResult.class));

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void testUpdateMovie() {
        MovieService movieService = mock(MovieService.class);
        MovieController movieController = new MovieController(movieService);

        Movie existingMovie = new Movie();
        existingMovie.setId(1); 
        when(movieService.findById(1)).thenReturn(existingMovie);

        Movie updatedMovie = new Movie();
        updatedMovie.setTitle("Nuevo título"); 

        ResponseEntity<?> responseEntity = movieController.updateMovie(1, updatedMovie, mock(BindingResult.class));

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        verify(movieService).findById(1);
        verify(movieService).save(any(Movie.class)); 
    }

    @Test
    void testDeleteMovie() {
        MovieService movieService = mock(MovieService.class);
        MovieController movieController = new MovieController(movieService);

        Movie existingMovie = new Movie();
        existingMovie.setId(1); 
        when(movieService.findById(1)).thenReturn(existingMovie);

        ResponseEntity<?> responseEntity = movieController.deleteMovie(1);

        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());

        verify(movieService).findById(1);
        verify(movieService).deleteById(1);
    }

    @Test
    void testRateMovie() {
        MovieService movieService = mock(MovieService.class);
        MovieController movieController = new MovieController(movieService);

        Movie movie = new Movie();
        movie.setId(1);
        movie.setTitle("Ejemplo de Película");

        User user = new User();
        user.setId(123);
        user.setEmail("ejemplo_usuario");

        Rating rating = new Rating();
        rating.setId(456L); 
        rating.setScore(4);

        when(movieService.findById(1)).thenReturn(movie);
        when(movieService.rateMovie(eq(1), eq(4), eq(user))).thenReturn(rating);
       
    }



}
