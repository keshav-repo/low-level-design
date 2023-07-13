package com.designing.bookmyshow.model;

import java.util.LinkedList;
import java.util.List;

public class Screen {
    private static int incrementer = 1;
    private String screenId;
    private String theatreId;
    List<String> seatIdList;

    public Screen(String theatreId) {
        this.screenId = "SCREEN "+incrementer++;
        this.theatreId = theatreId;
        this.seatIdList = new LinkedList<>();
    }

    public String getScreenId() {
        return screenId;
    }

    public List<String> getSeatIdList() {
        return seatIdList;
    }

    public void addSeatIdList(String seatId) {
        this.seatIdList.add(seatId);
    }

}
