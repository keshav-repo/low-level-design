package com.designing.bookmyshow.service;

import com.designing.bookmyshow.model.Seat;

public interface LockService {
    public void lockSeat(Seat seat);
    public void unlockSeat(Seat seat);
    public boolean isSeatLocked(Seat seat);
}
