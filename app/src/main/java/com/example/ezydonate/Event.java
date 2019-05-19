package com.example.ezydonate;

/*Created a separate class for events */

public class Event {


        public String title;
        public String description;
        public String location;
        public String eventDate;
        public String time;
        public int image;

        public Event (String title, String description, String location, String eventDate, String time, int image) {

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

        public int getThumbnail() {
            return image;
        }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setImage(int image) {
        this.image = image;
    }
}


