package com.filmstar.api.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "movies")
public class Movie {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título no puede estar en blanco")
    @Size(max = 255, message = "El título debe tener como máximo 255 caracteres")
    private String title;

    @NotBlank(message = "El título original no puede estar en blanco")
    private String originalTitle;

    private String posterPath;

    @NotNull(message = "La fecha de estreno no puede estar en blanco")
    private LocalDate releaseDate;

    @NotBlank(message = "La descripción no puede estar en blanco")
    @Size(max = 1000, message = "La descripción debe tener como máximo 1000 caracteres")
    private String overview;

    public Movie() {
    }

    public Movie(String title, String originalTitle, String posterPath, LocalDate releaseDate, String overview) {
        this.title = title;
        this.originalTitle = originalTitle;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.overview = overview;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
