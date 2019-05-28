package com.example.ezydonate;

import android.graphics.Bitmap;

import java.util.LinkedList;

public class User
{
    public String email;
    public String fullname;
    public String username;
    public String image;
    public String isAdmin;
    public double donation = 0;


    public User() {


    }

    public User(String email, String fullname, String username, String image)
    {
        this.email = email;
        this.fullname = fullname;
        this.username = username;
        this.image = image;
        this.isAdmin = "no";
        this.donation = 0;

    }


    public void setDonation (double donation) {

        this.donation = donation;
    }

    public double getDonation() {

        return donation;
    }

}
