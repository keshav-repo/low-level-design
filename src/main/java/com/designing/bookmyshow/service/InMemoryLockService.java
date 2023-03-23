package com.designing.bookmyshow.service;

import com.designing.bookmyshow.exception.SeatLockedException;
import com.designing.bookmyshow.model.Seat;
import com.designing.bookmyshow.model.SeatLocker;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemoryLockService implements LockService{
    private int lockInTimeMinutes;
    ConcurrentMap<String, SeatLocker> lockerMap;
    public InMemoryLockService(int lockInTimeMinutes) {
        this.lockerMap = new ConcurrentHashMap<>();
        this.lockInTimeMinutes = lockInTimeMinutes;
    }

    @Override
    public void lockSeat(Seat seat) {
        // check if seat already locked
        if(isSeatLocked(seat)){
            throw new SeatLockedException(String.format("seat already locked with row %s, col %s", seat.getRowNo(), seat.getColNo()));
        }
        //lock seat
        SeatLocker seatLocker = new SeatLocker(seat, LocalDateTime.now(), lockInTimeMinutes);
        lockerMap.put(seat.getSeatId(), seatLocker);
    }

    @Override
    public void unlockSeat(Seat seat) {
        lockerMap.remove(seat.getSeatId());
    }

    @Override
    public boolean isSeatLocked(Seat seat) {
        SeatLocker seatLocker = lockerMap.get(seat.getSeatId());
        if(Objects.isNull(seatLocker)) return false;
        boolean isSeatLocked =  seatLocker.isLockActive();
        if(!isSeatLocked){
            lockerMap.remove(seat.getSeatId());
        }
        return isSeatLocked;
    }
}
