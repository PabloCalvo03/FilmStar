package com.filmstar.api.dtos.responses;

import java.util.List;


public class FavoriteMovieListPaginatedResponse extends PaginatedResponse<List<MovieFavoriteResponse>>{

	public FavoriteMovieListPaginatedResponse(Long size, Integer pages, Integer currentPage, List<MovieFavoriteResponse> content) {
		super(size, pages, currentPage, content);
	}

}
