package com.filmstar.api.entities;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FavoriteMovieEntityTests {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testUserNotNull() {
        FavoriteMovie favoriteMovie = new FavoriteMovie();
        favoriteMovie.setUser(null);
        favoriteMovie.setMovie(new Movie());

        Set<ConstraintViolation<FavoriteMovie>> violations = validator.validate(favoriteMovie);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("El usuario no puede ser nulo");
    }

    @Test
    void testMovieNotNull() {
        FavoriteMovie favoriteMovie = new FavoriteMovie();
        favoriteMovie.setUser(new User());
        favoriteMovie.setMovie(null);

        Set<ConstraintViolation<FavoriteMovie>> violations = validator.validate(favoriteMovie);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("La película no puede ser nula");
    }

    @Test
    void testValidFavoriteMovie() {
        FavoriteMovie favoriteMovie = new FavoriteMovie();
        favoriteMovie.setUser(new User());
        favoriteMovie.setMovie(new Movie());

        Set<ConstraintViolation<FavoriteMovie>> violations = validator.validate(favoriteMovie);
        assertThat(violations).isEmpty();
    }

    @Test
    void testFavoriteMovieGettersAndSetters() {
        FavoriteMovie favoriteMovie = new FavoriteMovie();
        User user = new User();
        Movie movie = new Movie();

        favoriteMovie.setId(1L);
        favoriteMovie.setUser(user);
        favoriteMovie.setMovie(movie);

        assertThat(favoriteMovie.getId()).isEqualTo(1);
        assertThat(favoriteMovie.getUser()).isEqualTo(user);
        assertThat(favoriteMovie.getMovie()).isEqualTo(movie);
    }

    @Test
    void testFavoriteMovieConstructors() {
        User user = new User();
        Movie movie = new Movie();

        FavoriteMovie favoriteMovieWithParams = new FavoriteMovie(1L, user, movie);

        assertThat(favoriteMovieWithParams.getId()).isEqualTo(1);
        assertThat(favoriteMovieWithParams.getUser()).isEqualTo(user);
        assertThat(favoriteMovieWithParams.getMovie()).isEqualTo(movie);
    }
}
