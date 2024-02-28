package com.filmstar.api.actions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.filmstar.api.controllers.querys.QueryListMovieGetController;
import com.filmstar.api.dtos.responses.MovieListPaginatedResponse;
import com.filmstar.api.entities.Movie;
import com.filmstar.api.usecases.ListMovieUseCase;

@ExtendWith(MockitoExtension.class)
public class ListMoviesTest {

    @Mock
    private ListMovieUseCase listMovieUseCase;

    @InjectMocks
    private QueryListMovieGetController queryListMovieGetController;

    @Test
    void testExecute() {
        Pageable pageable = mock(Pageable.class);
        Page<Movie> mockedMovies = mock(Page.class);
        
        Mockito.<ResponseEntity<?>>when(listMovieUseCase.execute(pageable)).thenReturn(new ResponseEntity<MovieListPaginatedResponse>(new MovieListPaginatedResponse(), HttpStatus.ACCEPTED));

        ResponseEntity<MovieListPaginatedResponse> responseEntity = queryListMovieGetController.execute(pageable);

        verify(listMovieUseCase, times(1)).execute(pageable);

        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
    }


}
