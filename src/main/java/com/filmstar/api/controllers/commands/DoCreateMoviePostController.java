package com.filmstar.api.controllers.commands;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmstar.api.entities.Movie;
import com.filmstar.api.services.MovieService;
import com.filmstar.api.usecases.CreateMovieUseCase;

/**
 * Controlador para manejar la creación de películas a través de solicitudes POST.
 * Las películas se crean en base a la entrada proporcionada y se valida con anotaciones de validación.
 */
@RestController
@RequestMapping("/api/v1/movies")
public class DoCreateMoviePostController {

    private final CreateMovieUseCase createMovieUseCase;

    /**
     * Constructor que inyecta una instancia de CreateMovieUseCase.
     *
     * @param createMovieUseCase Caso de uso para la creación de películas.
     */
    public DoCreateMoviePostController(MovieService movieService, CreateMovieUseCase createMovieUseCase) {
        this.createMovieUseCase = createMovieUseCase;
    }

    /**
     * Maneja las solicitudes POST para la creación de películas.
     *
     * @param movie          La película que se va a crear, validada con anotaciones de validación.
     * @param bindingResult  Objeto que contiene los resultados de la validación de la entrada.
     * @return ResponseEntity con información sobre el resultado de la operación de creación.
     */
    @PostMapping
    public ResponseEntity<?> execute(@Valid @RequestBody Movie movie, BindingResult bindingResult) {
        ResponseEntity<?> result = this.createMovieUseCase.execute(movie, bindingResult);
        return result;
    }
}
