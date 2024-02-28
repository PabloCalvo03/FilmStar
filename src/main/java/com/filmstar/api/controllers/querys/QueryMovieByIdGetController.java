package com.filmstar.api.controllers.querys;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmstar.api.entities.Movie;
import com.filmstar.api.usecases.MovieByIdUseCase;

/**
 * Controlador para manejar las consultas de películas por su identificador a través de solicitudes GET.
 * Las solicitudes se gestionan mediante endpoints RESTful.
 */
@RestController
@RequestMapping("/api/v1/movies/{id}")
public class QueryMovieByIdGetController {
    
    private final MovieByIdUseCase movieByIdUseCase;
    
    /**
     * Constructor que inyecta una instancia de MovieByIdUseCase.
     *
     * @param movieByIdUseCase Caso de uso para obtener información de una película por su identificador.
     */
    public QueryMovieByIdGetController(MovieByIdUseCase movieByIdUseCase) {
        this.movieByIdUseCase = movieByIdUseCase;
    }

    /**
     * Maneja las solicitudes GET para obtener información de una película por su identificador.
     *
     * @param id Identificador de la película que se va a consultar.
     * @return ResponseEntity con información de la película solicitada.
     */
    @GetMapping
    public ResponseEntity<Movie> execute(@PathVariable Integer id) {
        ResponseEntity<Movie> response = movieByIdUseCase.execute(id);
        return response;
    }
}
