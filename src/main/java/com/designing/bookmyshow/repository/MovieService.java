package com.designing.bookmyshow.repository;

import com.designing.bookmyshow.model.Movie;
import com.designing.bookmyshow.model.Screen;
import com.designing.bookmyshow.model.Show;
import com.designing.bookmyshow.model.Theatre;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieService {
    Map<String, Movie> movieList = new HashMap<>();


    public void createMovie(String movieName, int duration){
        Movie movie = new Movie(movieName, duration);
        movieList.put(movieName, movie);
    }

    public Movie getMovie(String name){
        return movieList.get(name);
    }









}
