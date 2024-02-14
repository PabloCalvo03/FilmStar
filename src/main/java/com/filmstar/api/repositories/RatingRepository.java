package com.filmstar.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.filmstar.api.entities.Rating;

/**
 * Repositorio de puntuaciones de peliculas.
 * @author pablo
 *
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
	
}
