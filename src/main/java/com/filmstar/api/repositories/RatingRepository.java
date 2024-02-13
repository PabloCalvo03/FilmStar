package com.filmstar.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filmstar.api.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {
	
}
