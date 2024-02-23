package com.filmstar.api.dtos.requests;

public class MovieRatingRequest {
    private Integer id;
    private int score;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

    
}