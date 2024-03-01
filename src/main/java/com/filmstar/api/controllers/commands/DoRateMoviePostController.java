package com.filmstar.api.controllers.commands;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmstar.api.dtos.requests.MovieRatingRequest;
import com.filmstar.api.entities.User;
import com.filmstar.api.usecases.RateMovieUseCase;

/**
 * Controlador para manejar la calificación de películas a través de solicitudes POST.
 * Las solicitudes se gestionan mediante endpoints RESTful.
 */
@RestController
@RequestMapping("/api/v1/movies/rate")
public class DoRateMoviePostController {
    
    private final RateMovieUseCase rateMovieUseCase;
    
    /**
     * Constructor que inyecta una instancia de RateMovieUseCase.
     *
     * @param rateMovieUseCase Caso de uso para la calificación de películas.
     */
    public DoRateMoviePostController(RateMovieUseCase rateMovieUseCase) {
        this.rateMovieUseCase = rateMovieUseCase;
    }

    /**
     * Maneja las solicitudes POST para calificar una película.
     *
     * @param requestBody Objeto que contiene la solicitud de calificación de la película.
     * @param user         Usuario autenticado que realiza la calificación.
     * @return ResponseEntity con información sobre el resultado de la operación de calificación.
     */
    @PostMapping
    public ResponseEntity<?> execute(@RequestBody MovieRatingRequest requestBody, @AuthenticationPrincipal User user) {
        ResponseEntity<?> response = rateMovieUseCase.execute(requestBody, user);
        return response;
    }
}
