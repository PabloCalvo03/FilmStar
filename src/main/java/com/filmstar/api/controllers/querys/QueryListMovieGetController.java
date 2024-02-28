package com.filmstar.api.controllers.querys;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmstar.api.dtos.responses.MovieListPaginatedResponse;
import com.filmstar.api.usecases.ListMovieUseCase;

/**
 * Controlador para manejar las consultas de la lista de películas a través de solicitudes GET.
 * Las solicitudes se gestionan mediante endpoints RESTful.
 */
@RestController
@RequestMapping("/api/v1/movies")
public class QueryListMovieGetController {

    private final ListMovieUseCase listMovieUseCase;

    /**
     * Constructor que inyecta una instancia de ListMovieUseCase.
     *
     * @param listMovieUseCase Caso de uso para la obtención de la lista de películas.
     */
    public QueryListMovieGetController(ListMovieUseCase listMovieUseCase) {
        this.listMovieUseCase = listMovieUseCase;
    }

    /**
     * Maneja las solicitudes GET para obtener la lista paginada de películas.
     *
     * @param pageable Objeto que representa la información de paginación de la lista de películas.
     * @return ResponseEntity con información paginada sobre la lista de películas.
     */
    @GetMapping
    public ResponseEntity<MovieListPaginatedResponse> execute(Pageable pageable) {
        ResponseEntity<MovieListPaginatedResponse> response = listMovieUseCase.execute(pageable);
        return response;
    }
}
