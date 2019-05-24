package com.example.ezydonate;

/*Created a separate class for events */

public class EventHistory {

    public String title;
    public String description;
    public String location;
    public String eventDate;
    public String time;
    public String image;

    public EventHistory(String title, String description, String location, String eventDate, String time, String image) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.eventDate = eventDate;
        this.time = time;
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getLocation() {
        return location;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}


