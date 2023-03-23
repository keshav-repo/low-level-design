package com.designing.bookmyshow.service;

import com.designing.bookmyshow.exception.SeatLockedException;
import com.designing.bookmyshow.model.*;
import com.designing.bookmyshow.utility.MovieList;
import com.designing.bookmyshow.utility.TheatreList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PersonServiceTest {

    private PersonService personService;

    @BeforeEach
    public void setup(){
        personService = new PersonService();
    }

    @Test
    public void testCreateCustomer(){
        AuthInput authInput = new UserNamePasswordInput("userI", new char[]{'a','b','c','d'});
        Auth auth = new UserNamePasswordAuth(authInput);
        Customer customer = personService.createCustomer(auth);
        personService.validateCustomer(authInput);
    }


    public static class MovieServiceTest {
        private MovieService movieService;
        private TheatreService theatreService;
        private PersonService personService;

        private LockService lockService;
        private Movie bahubali;
        private Movie avengers;
        private Theatre inoxTheatre;

        private Booking booking1;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        private Customer customer1;
        private Customer customer2;

        @BeforeEach
        public void setup() {
            movieService = new MovieService();
            lockService = new InMemoryLockService(5);
            personService = new PersonService();
            theatreService = new TheatreService(lockService, personService, movieService);

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

            customer1 = personService.createCustomer(new UserNamePasswordAuth(new UserNamePasswordInput("user-1", new char[]{'a', 'b', 'c', 'd'})));
            customer2 = personService.createCustomer(new UserNamePasswordAuth(new UserNamePasswordInput("user-2", new char[]{'a', 'b', 'c', 'e'})));
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


        @Test
        public void bookTicketTest() {

            List<String> showIdList = inoxTheatre.getShowIdList();

            String showId1 = showIdList.get(0);
            List<Seat> seatList1 = new LinkedList<>();
            Show show1 = theatreService.getShow(showId1);
            Screen screen1 = theatreService.getScreen(show1.getScreenId());

            List<String> seatsToBeBooked = List.of(
                    theatreService.getSeat(screen1.getScreenId(), 5, 9).getSeatId(),
                    theatreService.getSeat(screen1.getScreenId(), 5, 10).getSeatId()
            );

            booking1 = theatreService.bookTicket(showId1, seatsToBeBooked, personService.getCustomer("user-1").getUserName());

            assertEquals(show1.getBookingIdList().size(), 1);
            for (String seatId : seatsToBeBooked) {
                assertTrue(lockService.isSeatLocked(theatreService.getSeat(seatId))); // checking all the seats are locked
            }

            // check if another user can book same ticket at same time
            try {
                theatreService.bookTicket(showId1, seatsToBeBooked, personService.getCustomer("user-2").getUserName());
            } catch (SeatLockedException exception) {
                assertEquals(exception.getMessage(), "Locked seat Id are SEAT 485 SEAT 495 ");
            }

            Payment payment = theatreService.doPayment(booking1.getBookingId());
            assertNotNull(payment.getBookingId());
            assertEquals(booking1.getBookingStatus(), BookingStatus.CONFIRMED);
            // check all seats are unlocked after seat booking
            for (String seatId : booking1.getSeatBookedIdList()) {
                assertFalse(lockService.isSeatLocked(theatreService.getSeat(seatId)));
            }

            printTicket();
        }


        public void printTicket() {
            String printTicketString = theatreService.printTicket(booking1.getBookingId(), customer1.getUserName());
            System.out.println(printTicketString);
        }

    }
}
