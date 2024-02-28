package com.filmstar.api.usecases;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.filmstar.api.dtos.responses.FavoriteMovieListPaginatedResponse;
import com.filmstar.api.dtos.responses.MovieFavoriteResponse;
import com.filmstar.api.entities.FavoriteMovie;
import com.filmstar.api.entities.User;
import com.filmstar.api.services.FavoriteMovieService;

@Component
public class ListFavoriteMoviesUseCase {
	
	private final FavoriteMovieService favoriteMovieService;
	
	public ListFavoriteMoviesUseCase(FavoriteMovieService favoriteMovieService) {
		this.favoriteMovieService = favoriteMovieService;
	}
	
	public ResponseEntity<FavoriteMovieListPaginatedResponse> execute(User user, Pageable pageable){
		Page<FavoriteMovie> favoriteMovies = favoriteMovieService.getFavoritesByUser(user, pageable);
		FavoriteMovieListPaginatedResponse response = new FavoriteMovieListPaginatedResponse(favoriteMovies.getTotalElements(), favoriteMovies.getTotalPages(), favoriteMovies.getNumber(), favoriteMovies.stream().map(item -> new MovieFavoriteResponse(item)).toList());
		return new ResponseEntity<FavoriteMovieListPaginatedResponse>(response, HttpStatus.ACCEPTED);
	}

}
