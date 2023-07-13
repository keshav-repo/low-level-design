package com.designing.bookmyshow.model;

import java.time.LocalDateTime;

public class SeatLocker {
    private final Seat seat;
    private LocalDateTime lockedAt;
    private long lockInTimeMinutes;
    public SeatLocker(Seat seat, LocalDateTime lockedAt, long lockInTimeMinutes) {
        this.seat = seat;
        this.lockedAt = lockedAt;
        this.lockInTimeMinutes = lockInTimeMinutes;
    }
    public boolean isLockActive(){
        return lockedAt.plusMinutes(lockInTimeMinutes).isAfter(lockedAt);
    }
}
