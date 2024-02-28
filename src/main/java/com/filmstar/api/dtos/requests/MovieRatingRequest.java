package com.filmstar.api.dtos.requests;

public class MovieRatingRequest {
    private Long id;
    private int score;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

    
}