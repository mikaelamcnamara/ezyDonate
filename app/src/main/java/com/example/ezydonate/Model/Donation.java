package com.example.ezydonate.Model;

public class Donation {

    private long donation;
    public String uid;
    public String date;
    public String username;


    public Donation() {


    }


    public Donation(long donation, String uid, String date, String username) {

        this.donation = donation;
        this.uid = uid;
        this.date = date;
        this.username = username;

    }

    public long getDonation() { return donation; }
    public String getUid() { return uid; }
    public String getDate() { return date; }
    public String getUsername() { return username; }

    public void setDonation(Long donation) { this.donation = donation; }
    public void setUid(String uid) {  this.uid = uid; }
    public void setDate() { this.date = date; }
    public void setUsername() { this.username = username; }


}
