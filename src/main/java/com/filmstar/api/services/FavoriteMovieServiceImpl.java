package com.filmstar.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filmstar.api.entities.FavoriteMovie;
import com.filmstar.api.entities.Movie;
import com.filmstar.api.entities.User;
import com.filmstar.api.repositories.FavoriteMovieRepository;

@Service
public class FavoriteMovieServiceImpl implements FavoriteMovieService {

    @Autowired
    private FavoriteMovieRepository favoriteMovieRepository;

    public List<FavoriteMovie> getFavoritesByUser(User user) {
        return favoriteMovieRepository.findByUser(user);
    }

    public void addFavoriteMovie(User user, Movie movie) {
        if (!isMovieInFavorites(user, movie)) {
            FavoriteMovie favoriteMovie = new FavoriteMovie();
            favoriteMovie.setUser(user);
            favoriteMovie.setMovie(movie);
            favoriteMovieRepository.save(favoriteMovie);
        }
    }

    public Optional<FavoriteMovie> removeFavoriteMovie(User user, Movie movie) {
        Optional<FavoriteMovie> favoriteMovie = favoriteMovieRepository.findByUserAndMovie(user, movie);

        favoriteMovie.ifPresent(favoriteMovieRepository::delete);

        return favoriteMovie;
    }


    public boolean isMovieInFavorites(User user, Movie movie) {
        return favoriteMovieRepository.findByUserAndMovie(user, movie).isPresent();
    }
}
