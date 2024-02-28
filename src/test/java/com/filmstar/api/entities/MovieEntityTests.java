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
class MovieEntityTests {
	
	private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testTitleNotBlank() {
        Movie movie = new Movie();
        movie.setTitle("");
        movie.setOriginalTitle("Test Original Title");
        movie.setPosterPath("/test/poster.jpg");
        movie.setReleaseDate(LocalDate.of(2022, 1, 1));
        movie.setOverview("Test overview");

        Set<ConstraintViolation<Movie>> violations = validator.validate(movie);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("El título no puede estar en blanco");
    }

    @Test
    void testOriginalTitleMaxSize() {
        Movie movie = new Movie();
        movie.setTitle("This title is too long and exceeds the maximum allowed characters.");
        movie.setOriginalTitle("");
        movie.setPosterPath("/test/poster.jpg");
        movie.setReleaseDate(LocalDate.of(2022, 1, 1));
        movie.setOverview("Test overview");

        Set<ConstraintViolation<Movie>> violations = validator.validate(movie);

        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("El título original no puede estar en blanco");
    }
    @Test
    void testReleaseDateNotNull() {
        Movie movie = new Movie();
        movie.setTitle("Test Title");
        movie.setOriginalTitle("Test Original Title");
        movie.setPosterPath("/test/poster.jpg");
        movie.setReleaseDate(null);
        movie.setOverview("Test overview");

        Set<ConstraintViolation<Movie>> violations = validator.validate(movie);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("La fecha de estreno no puede estar en blanco");
    }

    @Test
    void testOverviewNotBlank() {
        Movie movie = new Movie();
        movie.setTitle("Test Title");
        movie.setOriginalTitle("Test Original Title");
        movie.setPosterPath("/test/poster.jpg");
        movie.setReleaseDate(LocalDate.of(2022, 1, 1));
        movie.setOverview("");

        Set<ConstraintViolation<Movie>> violations = validator.validate(movie);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("La descripción no puede estar en blanco");
    }

    @Test
    void testOverviewMaxSize() {
        Movie movie = new Movie();
        movie.setTitle("Test Title");
        movie.setOriginalTitle("Test Original Title");
        movie.setPosterPath("/test/poster.jpg");
        movie.setReleaseDate(LocalDate.of(2022, 1, 1));
        movie.setOverview("This overview is too long and exceeds the maximum allowed characters. This overview is too long and exceeds the maximum allowed characters. This overview is too long and exceeds the maximum allowed characters. This overview is too long and exceeds the maximum allowed characters. This overview is too long and exceeds the maximum allowed characters. This overview is too long and exceeds the maximum allowed characters. This overview is too long and exceeds the maximum allowed characters. This overview is too long and exceeds the maximum allowed characters. This overview is too long and exceeds the maximum allowed characters. This overview is too long and exceeds the maximum allowed characters. This overview is too long and exceeds the maximum allowed characters. This overview is too long and exceeds the maximum allowed characters. This overview is too long and exceeds the maximum allowed characters. This overview is too long and exceeds the maximum allowed characters. This overview is too long and exceeds the maximum allowed characters. This overview is too long and exceeds the maximum allowed characters. This overview is too long and exceeds the maximum allowed characters. This overview is too long and exceeds the maximum allowed characters. This overview is too long and exceeds the maximum allowed characters. This overview is too long and exceeds the maximum allowed characters. This overview is too long and exceeds the maximum allowed characters. This overview is too long and exceeds the maximum allowed characters. This overview is too long and exceeds the maximum allowed characters. This overview is too long and exceeds the maximum allowed characters. This overview is too long and exceeds the maximum allowed characters. This overview is too long and exceeds the maximum allowed characters. This overview is too long and exceeds the maximum allowed characters. This overview is too long and exceeds the maximum allowed characters. This overview is too long and exceeds the maximum allowed characters. This overview is too long and exceeds the maximum allowed characters. This overview is too long and exceeds the maximum allowed characters. This overview is too long and exceeds the maximum allowed characters. This overview is too long and exceeds the maximum allowed characters. This overview is too long and exceeds the maximum allowed characters.");

        System.out.println("Longitud actual de overview: " + movie.getOverview().length());

        Set<ConstraintViolation<Movie>> violations = validator.validate(movie);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("La descripción debe tener como máximo 1000 caracteres");
    }



    @Test
    void testValidMovie() {
        Movie movie = new Movie();
        movie.setTitle("Test Title");
        movie.setOriginalTitle("Test Original Title");
        movie.setPosterPath("/test/poster.jpg");
        movie.setReleaseDate(LocalDate.of(2022, 1, 1));
        movie.setOverview("Test overview");

        Set<ConstraintViolation<Movie>> violations = validator.validate(movie);
        assertThat(violations).isEmpty();
    }
    @Test
    void testMovieGettersAndSetters() {
        Movie movie = new Movie();

        movie.setId(1L);
        movie.setTitle("Test Title");
        movie.setOriginalTitle("Test Original Title");
        movie.setPosterPath("/test/poster.jpg");
        movie.setReleaseDate(LocalDate.of(2022, 1, 1));
        movie.setOverview("Test overview");

        assertThat(movie.getId()).isEqualTo(1);
        assertThat(movie.getTitle()).isEqualTo("Test Title");
        assertThat(movie.getOriginalTitle()).isEqualTo("Test Original Title");
        assertThat(movie.getPosterPath()).isEqualTo("/test/poster.jpg");
        assertThat(movie.getReleaseDate()).isEqualTo(LocalDate.of(2022, 1, 1));
        assertThat(movie.getOverview()).isEqualTo("Test overview");
    }

    @Test
    void testMovieConstructors() {
        Movie movieWithParams = new Movie("Test Title", "Test Original Title", "/test/poster.jpg", LocalDate.of(2022, 1, 1), "Test overview");

        assertThat(movieWithParams.getTitle()).isEqualTo("Test Title");
        assertThat(movieWithParams.getOriginalTitle()).isEqualTo("Test Original Title");
        assertThat(movieWithParams.getPosterPath()).isEqualTo("/test/poster.jpg");
        assertThat(movieWithParams.getReleaseDate()).isEqualTo(LocalDate.of(2022, 1, 1));
        assertThat(movieWithParams.getOverview()).isEqualTo("Test overview");

    }
}
