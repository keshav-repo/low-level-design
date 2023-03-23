package com.designing.bookmyshow.service;

import com.designing.bookmyshow.model.Movie;
import com.designing.bookmyshow.model.Screen;
import com.designing.bookmyshow.model.Show;
import com.designing.bookmyshow.model.Theatre;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieService {
    Map<String, Movie> movieList = new HashMap<>();

    public Movie createMovie(String movieName, int duration){
        Movie movie = new Movie(movieName, duration);
        movieList.put(movie.getMovieId(), movie);
        return movie;
    }

    public Movie getMovie(String movieId){
        return movieList.get(movieId);
    }









}
