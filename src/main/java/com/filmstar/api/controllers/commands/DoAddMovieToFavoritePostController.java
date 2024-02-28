package com.filmstar.api.controllers.commands;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmstar.api.entities.User;
import com.filmstar.api.usecases.AddMovieToFavoriteUseCase;

/**
 * Controlador para manejar la adición de películas a la lista de favoritos de un usuario.
 * Las solicitudes se gestionan a través de endpoints RESTful.
 */
@RestController
@RequestMapping("/api/v1/favorites/{movieId}")
public class DoAddMovieToFavoritePostController {

    private final AddMovieToFavoriteUseCase addMovieToFavoriteUseCase;

    /**
     * Constructor que inyecta una instancia de AddMovieToFavoriteUseCase.
     *
     * @param addMovieToFavoriteUseCase Caso de uso para agregar películas a la lista de favoritos.
     */
    public DoAddMovieToFavoritePostController(AddMovieToFavoriteUseCase addMovieToFavoriteUseCase) {
        this.addMovieToFavoriteUseCase = addMovieToFavoriteUseCase;
    }

    /**
     * Maneja las solicitudes POST para agregar una película a la lista de favoritos del usuario.
     *
     * @param movieId Identificador de la película que se va a agregar.
     * @param user    Usuario autenticado que realiza la solicitud.
     * @return ResponseEntity con un mapa que contiene información sobre el resultado de la operación.
     */
    @PostMapping
    public ResponseEntity<Map<String, String>> execute(@PathVariable Integer movieId,
                                                       @AuthenticationPrincipal User user) {
        ResponseEntity<Map<String, String>> response = addMovieToFavoriteUseCase.execute(movieId, user);
        return response;
    }
}
