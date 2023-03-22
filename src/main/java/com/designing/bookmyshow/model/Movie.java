package com.designing.bookmyshow.model;

public class Movie {

    private static int incrementer = 1;

    private String movieId;
    private String movieName;
    private int duratonInMin;

    public Movie(String movieName, int duratonInMin) {
        this.movieId = "MOVIE-"+incrementer++;
        this.movieName = movieName;
        this.duratonInMin = duratonInMin;
    }

    public String getMovieName() {
        return movieName;
    }

    public int getDuratonInMin() {
        return duratonInMin;
    }

    public String getMovieId() {
        return movieId;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId='" + movieId + '\'' +
                ", movieName='" + movieName + '\'' +
                '}';
    }

}
