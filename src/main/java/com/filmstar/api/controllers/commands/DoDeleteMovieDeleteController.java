package com.filmstar.api.controllers.commands;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmstar.api.usecases.DeleteMovieUseCase;

/**
 * Controlador para manejar la eliminación de películas a través de solicitudes DELETE.
 * Las solicitudes se gestionan mediante endpoints RESTful.
 */
@RestController
@RequestMapping("/api/v1/movies/{id}")
public class DoDeleteMovieDeleteController {
    
    private final DeleteMovieUseCase deleteMovieUseCase;
    
    /**
     * Constructor que inyecta una instancia de DeleteMovieUseCase.
     *
     * @param deleteMovieUseCase Caso de uso para la eliminación de películas.
     */
    public DoDeleteMovieDeleteController(DeleteMovieUseCase deleteMovieUseCase) {
        this.deleteMovieUseCase = deleteMovieUseCase;
    }

    /**
     * Maneja las solicitudes DELETE para eliminar una película por su identificador.
     *
     * @param id Identificador de la película que se va a eliminar.
     * @return ResponseEntity con un mapa que contiene información sobre el resultado de la operación.
     */
    @DeleteMapping
    public ResponseEntity<Map<String, String>> execute(@PathVariable Integer id) {
        ResponseEntity<Map<String, String>> result = this.deleteMovieUseCase.execute(id);
        return result;
    }
}
