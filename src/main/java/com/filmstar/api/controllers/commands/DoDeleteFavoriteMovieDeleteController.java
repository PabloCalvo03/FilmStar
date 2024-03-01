package com.filmstar.api.controllers.commands;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmstar.api.entities.User;
import com.filmstar.api.usecases.DeleteFavoriteMovieUseCase;

/**
 * Controlador para manejar la eliminación de películas de la lista de favoritos de un usuario.
 * Las solicitudes DELETE se gestionan a través de endpoints RESTful.
 */
@RestController
@RequestMapping("/api/v1/favorites/{movieId}")
public class DoDeleteFavoriteMovieDeleteController {
    
    private final DeleteFavoriteMovieUseCase deleteFavoriteMovieUseCase;

    /**
     * Constructor que inyecta una instancia de DeleteFavoriteMovieUseCase.
     *
     * @param deleteFavoriteMovieUseCase Caso de uso para la eliminación de películas de la lista de favoritos.
     */
    public DoDeleteFavoriteMovieDeleteController(DeleteFavoriteMovieUseCase deleteFavoriteMovieUseCase) {
        this.deleteFavoriteMovieUseCase = deleteFavoriteMovieUseCase;
    }

    /**
     * Maneja las solicitudes DELETE para eliminar una película de la lista de favoritos del usuario.
     *
     * @param movieId Identificador de la película que se va a eliminar de la lista de favoritos.
     * @param user    Usuario autenticado que realiza la solicitud.
     * @return ResponseEntity con un mapa que contiene información sobre el resultado de la operación.
     */
    @DeleteMapping
    public ResponseEntity<Map<String, String>> execute(@PathVariable Long movieId, @AuthenticationPrincipal User user) {
        ResponseEntity<Map<String, String>> response = deleteFavoriteMovieUseCase.execute(movieId, user);
        return response;
    }
}
