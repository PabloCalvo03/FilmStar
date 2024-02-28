package com.filmstar.api.entities;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RatingEntityTests {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testRatingWithoutMovie() {
        User user = new User();
        user.setId(1L);

        Rating rating = new Rating();
        rating.setUser(user);
        rating.setScore(5);

        Set<ConstraintViolation<Rating>> violations = validator.validate(rating);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("La película no puede ser nula");
    }

    @Test
    void testRatingWithoutUser() {
        Movie movie = new Movie();
        movie.setId(1L);

        Rating rating = new Rating();
        rating.setMovie(movie);
        rating.setScore(5);

        Set<ConstraintViolation<Rating>> violations = validator.validate(rating);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("El usuario no puede ser nulo");
    }

    @Test
    void testRatingWithoutScore() {
        Movie movie = new Movie();
        movie.setId(1L);

        User user = new User();
        user.setId(1L);

        Rating rating = new Rating();
        rating.setMovie(movie);
        rating.setUser(user);

        Set<ConstraintViolation<Rating>> violations = validator.validate(rating);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("La puntuación no puede ser nula");
    }

    @Test
    void testValidRating() {
        Movie movie = new Movie();
        movie.setId(1L);

        User user = new User();
        user.setId(1L);

        Rating rating = new Rating();
        rating.setMovie(movie);
        rating.setUser(user);
        rating.setScore(5);

        Set<ConstraintViolation<Rating>> violations = validator.validate(rating);
        assertThat(violations).isEmpty();
    }

    @Test
    void testRatingGettersAndSetters() {
        Rating rating = new Rating();
        rating.setId(1L);

        Movie movie = new Movie();
        movie.setId(1L);

        User user = new User();
        user.setId(1L);

        rating.setMovie(movie);
        rating.setUser(user);
        rating.setScore(5);

        assertThat(rating.getId()).isEqualTo(1L);
        assertThat(rating.getMovie()).isEqualTo(movie);
        assertThat(rating.getUser()).isEqualTo(user);
        assertThat(rating.getScore()).isEqualTo(5);
    }
    
    @Test
    void testRatingConstructors() {
    	Movie movie = new Movie();
    	User user = new User();
        Rating ratingWithParams = new Rating(1L, movie, user, 10);

        assertThat(ratingWithParams.getId()).isEqualTo(1L);
        assertThat(ratingWithParams.getMovie()).isEqualTo(movie);
        assertThat(ratingWithParams.getUser()).isEqualTo(user);
        assertThat(ratingWithParams.getScore()).isEqualTo(10);

    }
}
