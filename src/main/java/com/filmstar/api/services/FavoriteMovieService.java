package com.filmstar.api.services;

import java.util.List;
import java.util.Optional;

import com.filmstar.api.entities.FavoriteMovie;
import com.filmstar.api.entities.Movie;
import com.filmstar.api.entities.User;

public interface FavoriteMovieService {
	
	List<FavoriteMovie> getFavoritesByUser(User user);
	void addFavoriteMovie(User user, Movie movie);
	Optional<FavoriteMovie> removeFavoriteMovie(User user, Movie movie);
	boolean isMovieInFavorites(User user, Movie movie);
}
