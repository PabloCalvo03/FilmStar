package com.filmstar.api.actions;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.filmstar.api.entities.FavoriteMovie;
import com.filmstar.api.entities.Movie;
import com.filmstar.api.entities.User;

@SpringBootTest
class FavoriteMovieEntityTests {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testFavoriteMovieWithValidUserAndMovie() {
        User user = new User();

        Movie movie = new Movie();
        movie.setTitle("Test Movie");

        FavoriteMovie favoriteMovie = new FavoriteMovie(null, user, movie);

        Set<ConstraintViolation<FavoriteMovie>> violations = validator.validate(favoriteMovie);
        assertThat(violations).isEmpty();
    }

    @Test
    void testFavoriteMovieWithoutUser() {
        Movie movie = new Movie("Test Movie", null, null, null, null);

        FavoriteMovie favoriteMovie = new FavoriteMovie(null, null, movie);

        Set<ConstraintViolation<FavoriteMovie>> violations = validator.validate(favoriteMovie);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("El usuario no puede ser nulo");
    }

    @Test
    void testFavoriteMovieWithoutMovie() {
        User user = new User();

        FavoriteMovie favoriteMovie = new FavoriteMovie(null, user, null);

        Set<ConstraintViolation<FavoriteMovie>> violations = validator.validate(favoriteMovie);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("La película no puede ser nula");
    }

    @Test
    void testFavoriteMovieWithNullId() {
        User user = new User();

        Movie movie = new Movie();
        movie.setTitle("Test Movie");

        FavoriteMovie favoriteMovie = new FavoriteMovie(null, user, movie);

        assertThat(favoriteMovie.getId()).isNull();
    }

    @Test
    void testFavoriteMovieGettersAndSetters() {
        FavoriteMovie favoriteMovie = new FavoriteMovie();

        User user = new User();

        Movie movie = new Movie();
        movie.setTitle("Test Movie");

        favoriteMovie.setId(1L);
        favoriteMovie.setUser(user);
        favoriteMovie.setMovie(movie);

        assertThat(favoriteMovie.getId()).isEqualTo(1L);
        assertThat(favoriteMovie.getUser()).isEqualTo(user);
        assertThat(favoriteMovie.getMovie()).isEqualTo(movie);
    }

    @Test
    void testFavoriteMovieConstructors() {
        User user = new User();

        Movie movie = new Movie();
        movie.setTitle("Test Movie");

        FavoriteMovie favoriteMovieWithParams = new FavoriteMovie(1L, user, movie);

        assertThat(favoriteMovieWithParams.getId()).isEqualTo(1L);
        assertThat(favoriteMovieWithParams.getUser()).isEqualTo(user);
        assertThat(favoriteMovieWithParams.getMovie()).isEqualTo(movie);
    }
}
