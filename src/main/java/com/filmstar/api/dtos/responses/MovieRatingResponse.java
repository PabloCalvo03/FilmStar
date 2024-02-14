package com.filmstar.api.dtos.responses;

import com.filmstar.api.entities.Movie;
import com.filmstar.api.entities.Rating;

public class MovieRatingResponse {
	
    private Long id;
    private Movie movie;
    private UserMovieRatingResponse user;
    private int score;


    public MovieRatingResponse(Rating rating) {
        this.id = rating.getId();
        this.movie = rating.getMovie();
        this.user = new UserMovieRatingResponse(rating.getUser());
        this.score = rating.getScore();
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

	public UserMovieRatingResponse getUser() {
		return user;
	}


	public void setUser(UserMovieRatingResponse user) {
		this.user = user;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}
    
    

}