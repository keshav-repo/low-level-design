package com.designing.bookmyshow.repository;

import com.designing.bookmyshow.model.Screen;
import com.designing.bookmyshow.model.Show;
import com.designing.bookmyshow.model.Theatre;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheatreService {

    Map<String, Theatre> theatreMap = new HashMap<>();

    Map<String, Show> showMap = new HashMap<>();

    public void addTheatre(String name, List<Screen> screenList) {
        Theatre theatre = new Theatre(name, screenList);
        theatreMap.put(name, theatre);
    }

    public Theatre getTheatre(String name) {
        return theatreMap.get(name);
    }

    public void addShow(Show show, String theatreName) {
        Theatre theatre = theatreMap.get(theatreName);
        theatre.addShow(show);
        theatreMap.put(theatre.getTheatreName(), theatre);
        showMap.put(show.getShowId(), show);
    }
}
