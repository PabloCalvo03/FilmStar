package com.filmstar.api.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.filmstar.api.entities.Movie;

/**
 * Repositorio de peliculas.
 * @author pablo
 *
 */
@Repository
public interface MovieRepository extends PagingAndSortingRepository<Movie, Integer>, CrudRepository<Movie, Integer>{
	
}
