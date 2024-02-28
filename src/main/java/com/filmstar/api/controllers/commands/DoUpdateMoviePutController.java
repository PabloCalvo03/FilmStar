package com.filmstar.api.controllers.commands;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmstar.api.entities.Movie;
import com.filmstar.api.usecases.UpdateMovieUseCase;

/**
 * Controlador para manejar las actualizaciones de películas a través de solicitudes PUT.
 * Las solicitudes se gestionan mediante endpoints RESTful.
 */
@RestController
@RequestMapping("/api/v1/movies/{id}")
public class DoUpdateMoviePutController {
    
    private final UpdateMovieUseCase updateMovieUseCase;
    
    /**
     * Constructor que inyecta una instancia de UpdateMovieUseCase.
     *
     * @param updateMovieUseCase Caso de uso para la actualización de películas.
     */
    public DoUpdateMoviePutController(UpdateMovieUseCase updateMovieUseCase) {
        this.updateMovieUseCase = updateMovieUseCase;
    }

    /**
     * Maneja las solicitudes PUT para actualizar una película por su identificador.
     *
     * @param id           Identificador de la película que se va a actualizar.
     * @param updatedMovie Objeto que contiene la información actualizada de la película, validado con anotaciones de validación.
     * @param bindingResult Objeto que contiene los resultados de la validación de la entrada.
     * @return ResponseEntity con información sobre el resultado de la operación de actualización.
     */
    @PutMapping
    public ResponseEntity<?> execute(@PathVariable Long id, 
                                     @Valid @RequestBody Movie updatedMovie,
                                     BindingResult bindingResult) {
        ResponseEntity<?> response = updateMovieUseCase.execute(id, updatedMovie, bindingResult);
        return response;
    }
}
