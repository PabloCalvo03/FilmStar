package com.filmstar.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.filmstar.api.entities.FavoriteMovie;
import com.filmstar.api.entities.Movie;
import com.filmstar.api.entities.User;

public interface FavoriteMovieService {
	
	Page<FavoriteMovie> getFavoritesByUser(User user, Pageable pageable);
	void addFavoriteMovie(User user, Movie movie);
	Optional<FavoriteMovie> removeFavoriteMovie(User user, Movie movie);
	boolean isMovieInFavorites(User user, Movie movie);
}
