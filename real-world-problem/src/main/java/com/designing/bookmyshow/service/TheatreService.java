package com.designing.bookmyshow.service;

import com.designing.bookmyshow.exception.BookingTimeExceedException;
import com.designing.bookmyshow.exception.PaymentAlreadyCompleted;
import com.designing.bookmyshow.exception.SeatLockedException;
import com.designing.bookmyshow.exception.SeatNotFound;
import com.designing.bookmyshow.model.*;
import com.designing.bookmyshow.service.PersonService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TheatreService {

    Map<String, Theatre> theatreMap = new HashMap<>();

    Map<String, Show> showMap = new HashMap<>();

    Map<String, Screen> screenMap = new HashMap<>();

    Map<String, Seat> seatMap = new HashMap<>();

    Map<String, Booking> bookingMap = new HashMap<>();

    Map<String, Payment> paymentMap = new HashMap<>();

    private LockService lockService;
    private PersonService personService;
    private MovieService movieService;

    public TheatreService(LockService lockService, PersonService personService, MovieService movieService) {
        this.lockService = lockService;
        this.personService = personService;
        this.movieService = movieService;
    }

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
        return show;
    }

    public Show getShow(String showId) {
        return showMap.get(showId);
    }

    public Seat addSeats(SeatType seatType, String screenId, int rowNo, int colNo) {
        Seat seat = null;
        switch (seatType) {
            case PREMIUM:
                seat = new PremiumSeat(screenId, rowNo, colNo);
                break;
            case REGULAR:
                seat = new RegularSeat(screenId, rowNo, colNo);
                break;
            default:
                throw new RuntimeException("Seat not found");
        }
        seatMap.put(seat.getSeatId(), seat);
        Screen screen = screenMap.get(screenId);
        screen.addSeatIdList(seat.getSeatId());
        return seat;
    }

    public Seat getSeat(String seatId){
        return seatMap.get(seatId);
    }

    public Seat getSeat(String screenId, int row, int col){
        Screen screen = screenMap.get(screenId);
        for(String seatId: screen.getSeatIdList()){
            Seat seat = seatMap.get(seatId);
            if(Integer.compare(seat.getColNo(), col) == 0 && Integer.compare(seat.getRowNo(), row) ==0  ){
                return seat;
            }
        }
        throw new SeatNotFound(String.format("seat now found with row %s, col %s", row, col));
    }

    public Booking bookTicket(String showId, List<String> seatIdList,String customerId){
        Show show = showMap.get(showId);
        List<Seat> seatList = new LinkedList<>();
        for(int i=0; i<seatIdList.size(); i++){
            Seat seat = seatMap.get(seatIdList.get(i));
            seatList.add(seat);
        }
        // check if any of ticket is booked , if yes throw exception with reason


        // check if any of the ticket is locked from booking
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Locked seat Id are ");
        boolean isSomeOfSeatocked = false;
        for(Seat seat: seatList){
            if(lockService.isSeatLocked(seat)){
                stringBuilder.append(seat.getSeatId()+" ");
                isSomeOfSeatocked = true;
            }
        }
        if(isSomeOfSeatocked){
            System.out.println("string builder is");
            System.out.println(stringBuilder.toString());
            throw new SeatLockedException(stringBuilder.toString());
        }

        // lock the seat and create booking
        seatList.forEach(seat -> lockService.lockSeat(seat));
        Booking booking = new Booking(showId, seatIdList, customerId);
        setBookingAmount(booking);
        bookingMap.put(booking.getBookingId(), booking);
        show.addBookingIdList(booking.getBookingId());

        return booking;
    }

    public Booking getBooking(String bookingId){
        return bookingMap.get(bookingId);
    }

    public Payment doPayment(String bookingId){
        Booking booking = bookingMap.get(bookingId);
        if(booking.getBookingStatus().equals(BookingStatus.CONFIRMED)){
            throw new PaymentAlreadyCompleted(String.format("payment already done for bookingId %s", booking.getBookingId()));
        }

        // if seat is not locked, booking can't be done
        List<String> seatbookedIdList =  booking.getSeatBookedIdList();
        for(String seatId: seatbookedIdList){
            if( !lockService.isSeatLocked(getSeat(seatId))){
                throw new BookingTimeExceedException("Booking time exceed, Please try again");
            }
        }

        // if yes, do the payment, complete the booking and remove the lock from seats
        Payment payment = new Payment(bookingId, "paypal", booking.getBookingAmount());
        paymentMap.put(payment.getPaymentId(), payment);
        booking.setPaymentId(payment.getPaymentId());
        booking.conformBooking();
        for(String seatId: seatbookedIdList){
            lockService.unlockSeat(getSeat(seatId));
        }
        return payment;
    }

    public Payment getPayment(String paymentId){
        return paymentMap.get(paymentId);
    }

    public double setBookingAmount(Booking booking){
        List<String> seatIdList =  booking.getSeatBookedIdList();
        double totalAmount = 0;
        for(String seatId: seatIdList){
            totalAmount += getSeat(seatId).getPrice();
        }
        booking.setBookingAmount(totalAmount);
        return totalAmount;
    }


    public String printTicket(String bookingId, String userName) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Booking details are\n");
        Customer customer = personService.getCustomer(userName);
        Booking booking = getBooking(bookingId);
        Payment payment = getPayment(booking.getPaymentId());
        Show show =  getShow(booking.getShowId());
        Movie movie = movieService.getMovie(show.getMovieId());
        Screen screen = getScreen(show.getScreenId());

        stringBuilder.append("customer Name: "+customer.getUserName());
        stringBuilder.append("\nMovie Name: "+movie.getMovieName());
        stringBuilder.append("\nStart timing: "+show.getStartTime().format(dateTimeFormatter));
        stringBuilder.append("\nEnd timing: "+show.getEndTime().format(dateTimeFormatter));
        stringBuilder.append("\nScreen Id "+screen.getScreenId());

        stringBuilder.append("\npayment status: "+booking.getBookingStatus().toString());
        stringBuilder.append("\ntotal amount INR : "+payment.getAmount());
        stringBuilder.append("\nbooked seats are:\n");
        booking.getSeatBookedIdList().forEach(seatId->{
            stringBuilder.append(seatId+",");
        });

        stringBuilder.append( "\npayment date is: " +payment.getPaymentDate().format(dateTimeFormatter));
        return stringBuilder.toString();
    }

}
