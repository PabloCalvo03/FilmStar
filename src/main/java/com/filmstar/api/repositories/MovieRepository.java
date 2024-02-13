package com.filmstar.api.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.filmstar.api.entities.Movie;

public interface MovieRepository extends PagingAndSortingRepository<Movie, Integer>, CrudRepository<Movie, Integer>{
	
}
