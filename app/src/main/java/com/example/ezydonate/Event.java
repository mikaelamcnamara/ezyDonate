package com.example.ezydonate;

public class Event {

    String date;
    String name;

    Event(String data, String name) {

        this.date = date;
        this.name = name;
    }

    public void setDate(String date) {

        this.date = date;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getDate() {

        return date;
    }

    public String getName() {

        return name;
    }

}
