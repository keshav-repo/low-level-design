package com.designing.bookmyshow.model;

import java.time.LocalDateTime;
import java.util.List;

public class Show {
    private String showId;
    private LocalDateTime createdOn;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Screen screen;
    private Movie movie;
    private Theatre theatre;
    private List<Booking> bookingList;

    public Show(String showId,LocalDateTime startTime, LocalDateTime endTime, Movie movie, Screen screen, Theatre theatre) {
        this.showId = showId;
        this.startTime = startTime;
        this.endTime = endTime;
        createdOn = LocalDateTime.now();
        this.movie = movie;
        this.screen = screen;
        this.theatre = theatre;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getShowId() {
        return showId;
    }

    public String toString() {
        return getScreen().getHallId();
    }

    public void addSeatsBooked(){

    }
}
