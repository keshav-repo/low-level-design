package com.designing.bookmyshow.repository;

import com.designing.bookmyshow.model.*;
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
        movieService.createMovie(MovieList.BAHUBALI.toString(), 190);
        bahubali = movieService.getMovie(MovieList.BAHUBALI.toString());
        movieService.createMovie(MovieList.AVENGER.toString(), 180);
        avengers = movieService.getMovie(MovieList.AVENGER.toString());

        // add theatre which have screen and each screen have seats
        List<Screen> theatreList = List.of(
                getScreen("SCREEN-I"),  getScreen("SCREEN-II"),  getScreen("SCREEN-III")
        );
        theatreService.addTheatre(TheatreList.INOX.toString(), theatreList);
        inoxTheatre = theatreService.getTheatre(TheatreList.INOX.toString());
        List<Screen> screens = inoxTheatre.getScreenList();

        // add show
        LocalDateTime startTime = LocalDateTime.parse("22-03-2023 15:00", dateTimeFormatter);
        LocalDateTime endTime = LocalDateTime.parse("22-03-2023 17:30",dateTimeFormatter);
        List<Show> showList = List.of(
                new Show("show-1",startTime, endTime, bahubali,screens.get(0), inoxTheatre),
                new Show("show-2",startTime, endTime, avengers, screens.get(1), inoxTheatre)
        );

        // add show to theatre
        for(Show show: showList){
            inoxTheatre.addShow(show);
        }

    }

    private Screen getScreen(String screenName){
        Screen screen = new Screen(screenName);
        for(int i=0; i<100; i++){
            if(i<=60){
                screen.addSeats(new RegularSeat(i, i%10));
            }else{
                screen.addSeats(new PremiumSeat(i,i%10));
            }
        }
        return screen;
    }

    @Test
    public void createMovieTest() {
        assertEquals(MovieList.BAHUBALI.toString(), bahubali.getMovieName());
    }

    @Test
    public void addTheatreTest() {
        assertEquals(inoxTheatre.getTheatreName(), TheatreList.INOX.toString());
        assertEquals(inoxTheatre.getScreenList().size(), 3);
    }

    @Test
    public void checkShow(){
        assertEquals(inoxTheatre.getShowList().size(), 2);
    }




}
