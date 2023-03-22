package com.designing.bookmyshow.model;

import java.util.List;

public interface Search {
    public List<Show> searchByTitle();

    public List<Show> searchByLanguage();

    public List<Show> searchByCity();

}
