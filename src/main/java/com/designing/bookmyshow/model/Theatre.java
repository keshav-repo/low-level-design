package com.designing.bookmyshow.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Theatre {

    private static int incrementer = 1;
    private String thretreId;
    private String theatreName;

    private List<String> screenIdList;

    private List<String> showIdList;

    public Theatre(String theatreName) {
        this.thretreId = "THEATRE " + incrementer++;
        this.theatreName = theatreName;
        this.screenIdList = new ArrayList<>();
        showIdList = new LinkedList<>();
    }

    public void addShow(String showId) {
        this.showIdList.add(showId);
    }

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public List<String> getScreenIdList() {
        return screenIdList;
    }

    public void setScreenIdList(List<String> screenIdList) {
        this.screenIdList = screenIdList;
    }

    public void addScreenIdList(String screenId){
        this.screenIdList.add(screenId);
    }

    public List<String> getShowIdList() {
        return showIdList;
    }

    public void setShowIdList(List<String> showIdList) {
        this.showIdList = showIdList;
    }

    public String getThretreId() {
        return thretreId;
    }
}
