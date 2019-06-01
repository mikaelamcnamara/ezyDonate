package com.example.ezydonate;

public class Booking {

    public String user;
    public String date;
    public String time;
    public String location;


    public Booking () {


    }

    public Booking(String user, String time, String date, String location)
    {
        this.user = user;
        this.date = date;
        this.time = time;
        this.location = location;

    }

    public String getDate () {

        return date;
    }

    public String getTime () {

        return time;
    }

    public String getLocation () {

        return location;
    }
}

