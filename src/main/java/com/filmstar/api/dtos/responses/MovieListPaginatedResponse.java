package com.filmstar.api.dtos.responses;

import java.util.List;

import com.filmstar.api.entities.Movie;

public class MovieListPaginatedResponse extends PaginatedResponse<List<Movie>>{
	
	public MovieListPaginatedResponse() {
		super();
	}

	public MovieListPaginatedResponse(Long size, Integer pages, Integer currentPage, List<Movie> content) {
		super(size, pages, currentPage, content);
	}

}
