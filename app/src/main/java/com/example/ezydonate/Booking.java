package com.example.ezydonate;

public class Booking {

    public String user;
    public String date;
    public String time;
    public String description;


    public Booking () {


    }

    public Booking(String user, String time, String date, String Description)
    {
        this.user = user;
        this.date = date;
        this.time = time;
        this.description = Description;

    }

    public String getDate () {

        return date;
    }

    public String getTime () {

        return time;
    }

    public String getDescription () {

        return description;
    }

}

