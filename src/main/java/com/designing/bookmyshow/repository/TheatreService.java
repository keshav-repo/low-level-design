package com.designing.bookmyshow.repository;

import com.designing.bookmyshow.model.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheatreService {

    Map<String, Theatre> theatreMap = new HashMap<>();

    Map<String, Show> showMap = new HashMap<>();

    Map<String, Screen> screenMap = new HashMap<>();

    Map<String, Seat> seatMap = new HashMap<>();

    public Theatre addTheatre(String name) {
        Theatre theatre = new Theatre(name);
        theatreMap.put(theatre.getThretreId(), theatre);
        return theatre;
    }

    public Theatre getTheatre(String name) {
        return theatreMap.get(name);
    }

    public Screen addScreen(String theatreId) {
        Screen screen = new Screen(theatreId);
        screenMap.put(screen.getScreenId(), screen);
        Theatre theatre = theatreMap.get(theatreId);
        theatre.addScreenIdList(screen.getScreenId());
        theatreMap.put(theatreId, theatre);
        return screen;
    }

    public Screen getScreen(String screenId) {
        return screenMap.get(screenId);
    }

    public Show addShow(LocalDateTime startTime, LocalDateTime endTime, String movieId, String screenId, String theatreId) {
        Show show = new Show(startTime, endTime, movieId, screenId, theatreId);
        showMap.put(show.getShowId(), show);
        Theatre theatre = theatreMap.get(theatreId);
        theatre.addShow(show.getShowId());
        theatreMap.put(theatreId, theatre);
        return show;
    }

    public Show getShow(String showId) {
        return showMap.get(showId);
    }

    public Seat addSeats(SeatType seatType, String screenId, int rowNo, int colNo) {
        Seat seat = null;
        switch (seatType) {
            case PREMIUM: seat =  new PremiumSeat(screenId, rowNo, colNo); break;
            case REGULAR: seat =  new RegularSeat(screenId, rowNo, colNo); break;
            default: System.out.println("Seat type not found");
        }
        if( seat !=null ){
            seatMap.put(seat.getSeatId(), seat);
            Screen screen = screenMap.get(screenId);
            screen.addSeatIdList(seat.getSeatId());
            return seat;
        }else {
            throw new RuntimeException("Seat not found");
        }
    }
}
