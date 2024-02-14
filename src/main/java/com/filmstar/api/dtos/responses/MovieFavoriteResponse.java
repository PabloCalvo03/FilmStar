package com.filmstar.api.dtos.responses;

import com.filmstar.api.entities.FavoriteMovie;
import com.filmstar.api.entities.Movie;

public class MovieFavoriteResponse {
	
	private Long id;
    private Movie movie;
    private UserMovieFavoriteResponse user;


    public MovieFavoriteResponse(FavoriteMovie favorite) {
        this.id = favorite.getId();
        this.movie = favorite.getMovie();
        this.user = new UserMovieFavoriteResponse(favorite.getUser());
    }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Movie getMovie() {
		return movie;
	}


	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public UserMovieFavoriteResponse getUser() {
		return user;
	}


	public void setUser(UserMovieFavoriteResponse user) {
		this.user = user;
	}

}
