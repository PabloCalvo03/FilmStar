package com.filmstar.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.filmstar.api.entities.FavoriteMovie;
import com.filmstar.api.entities.Movie;
import com.filmstar.api.entities.User;

/**
 * Repositorio de peliculas favoritas.
 * @author pablo
 *
 */
@Repository
public interface FavoriteMovieRepository extends JpaRepository<FavoriteMovie, Long> {

    List<FavoriteMovie> findByUser(User user);

    Optional<FavoriteMovie> findByUserAndMovie(User user, Movie movie);
}