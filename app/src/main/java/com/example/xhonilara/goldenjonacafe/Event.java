package com.example.xhonilara.goldenjonacafe;

import com.google.firebase.database.ChildEventListener;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by xhonilara on 19/05/17.
 */

public class Event {

    private String name;
    private String date;
    private String description;

    public Event(){}
    public Event(String name, String date, String description) {

        this.name = name;
        this.date = date;
        this.description = description;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return name + "\n" + date + "\n" + description + "\n";
    }
}

