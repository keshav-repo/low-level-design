package com.designing.bookmyshow.model;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Show {
    private static int incrementer = 1;
    private String showId;
    private LocalDateTime createdOn;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String screenId;
    private String movieId;
    private String theatreId;
    private List<String> bookingIdList;
    private List<String> bookedSeatIdList;

    public Show(LocalDateTime startTime, LocalDateTime endTime, String movieId, String screenId, String theatreId) {
        this.showId = "SHOW " + incrementer++;
        this.startTime = startTime;
        this.endTime = endTime;
        createdOn = LocalDateTime.now();
        this.movieId = movieId;
        this.screenId = screenId;
        this.theatreId = theatreId;
        this.bookingIdList = new LinkedList<>();
        this.bookedSeatIdList = new LinkedList<>();
    }

    public String getScreenId() {
        return screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
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

    public String getMovieId() {
        return movieId;
    }

    public String getShowId() {
        return showId;
    }

    public List<String> getBookingIdList() {
        return bookingIdList;
    }
    public void addBookingIdList(String bookingId) {
        this.bookingIdList.add(bookingId);
    }
}
