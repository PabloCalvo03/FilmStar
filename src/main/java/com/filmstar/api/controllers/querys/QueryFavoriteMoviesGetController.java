package com.filmstar.api.controllers.querys;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmstar.api.dtos.responses.FavoriteMovieListPaginatedResponse;
import com.filmstar.api.entities.User;
import com.filmstar.api.usecases.ListFavoriteMoviesUseCase;

/**
 * Controlador para manejar las consultas de la lista de películas favoritas de un usuario a través de solicitudes GET.
 * Las solicitudes se gestionan mediante endpoints RESTful.
 */
@RestController
@RequestMapping("/api/v1/favorites")
public class QueryFavoriteMoviesGetController {
    
    private final ListFavoriteMoviesUseCase listFavoriteMoviesUseCase;

    /**
     * Constructor que inyecta una instancia de ListFavoriteMoviesUseCase.
     *
     * @param listFavoriteMoviesUseCase Caso de uso para la obtención de la lista de películas favoritas de un usuario.
     */
    public QueryFavoriteMoviesGetController(ListFavoriteMoviesUseCase listFavoriteMoviesUseCase) {
        this.listFavoriteMoviesUseCase = listFavoriteMoviesUseCase;
    }

    /**
     * Maneja las solicitudes GET para obtener la lista paginada de películas favoritas de un usuario.
     *
     * @param user     Usuario autenticado que realiza la solicitud.
     * @param pageable Objeto que representa la información de paginación de la lista de películas favoritas.
     * @return ResponseEntity con información paginada sobre la lista de películas favoritas del usuario.
     */
    @GetMapping
    public ResponseEntity<FavoriteMovieListPaginatedResponse> execute(@AuthenticationPrincipal User user, Pageable pageable) {
        ResponseEntity<FavoriteMovieListPaginatedResponse> response = listFavoriteMoviesUseCase.execute(user, pageable);
        return response;
    }
}
