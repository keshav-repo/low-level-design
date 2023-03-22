package com.designing.bookmyshow.model;

import java.util.LinkedList;
import java.util.List;

public class Theatre {
    private String theatreName;

    private List<Screen> screenList;

    private List<Show> showList;

    public Theatre(String theatreName, List<Screen> screenList) {
        this.theatreName = theatreName;
        this.screenList = screenList;
        showList = new LinkedList<>();
    }

    public void addShow(Show show){
        this.showList.add(show);
    }

    public String getTheatreName() {
        return theatreName;
    }

    public List<Screen> getScreenList() {
        return screenList;
    }

    public List<Show> getShowList() {
        return showList;
    }
}
