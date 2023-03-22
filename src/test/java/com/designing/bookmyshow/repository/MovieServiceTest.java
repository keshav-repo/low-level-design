package com.designing.bookmyshow.repository;

import com.designing.bookmyshow.model.Movie;
import com.designing.bookmyshow.model.SeatType;
import com.designing.bookmyshow.model.Show;
import com.designing.bookmyshow.model.Theatre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieServiceTest {

    private MovieService movieService;
    private TheatreService theatreService;

    private Movie bahubali;
    private Movie avengers;
    private Theatre inoxTheatre;

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    @BeforeEach
    public void setup() {
        movieService = new MovieService();
        theatreService = new TheatreService();

        // add movie bahubali and avengers
        bahubali = movieService.createMovie(MovieList.BAHUBALI.toString(), 190);
        avengers = movieService.createMovie(MovieList.AVENGER.toString(), 180);

        // add theatre
        inoxTheatre = theatreService.addTheatre(TheatreList.INOX.toString());

        // add theatre which have screen and each screen have seats

        //add screen
        theatreService.addScreen(inoxTheatre.getThretreId());
        theatreService.addScreen(inoxTheatre.getThretreId());

        List<String> screenIdList = inoxTheatre.getScreenIdList();


        // add seats, 100 seats in both
        for (String screenId : screenIdList) {
            for (int row = 1; row <= 5; row++) {
                for (int col = 1; col <= 10; col++) {
                    theatreService.addSeats(SeatType.REGULAR, screenId, row, col);
                }
            }
            for (int row = 6; row <= 10; row++) {
                for (int col = 1; col <= 10; col++) {
                    theatreService.addSeats(SeatType.PREMIUM, screenId, row, col);
                }
            }
        }

        LocalDateTime startTime = LocalDateTime.parse("22-03-2023 15:00", dateTimeFormatter);
        LocalDateTime endTime = LocalDateTime.parse("22-03-2023 17:30", dateTimeFormatter);

        // add show
        Show bahubaliShow = theatreService.addShow(startTime, endTime, bahubali.getMovieId(), screenIdList.get(0), inoxTheatre.getThretreId());
        Show avengersShow = theatreService.addShow(startTime, endTime, avengers.getMovieId(), screenIdList.get(1), inoxTheatre.getThretreId());
    }

    @Test
    public void createMovieTest() {
        assertEquals(MovieList.BAHUBALI.toString(), bahubali.getMovieName());
    }

    @Test
    public void addTheatreTest() {
        assertEquals(inoxTheatre.getTheatreName(), TheatreList.INOX.toString());
        assertEquals(inoxTheatre.getScreenIdList().size(), 2);
    }

    @Test
    public void checkShow() {
        assertEquals(inoxTheatre.getShowIdList().size(), 2);
    }


}
