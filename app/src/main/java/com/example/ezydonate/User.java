package com.example.ezydonate;

import android.graphics.Bitmap;

import java.util.LinkedList;

public class User
{
    public String email;
    public String fullname;
    public String username;

    public String isAdmin;
    public double donation = 0;


    public User() {


    }

    public User(String email, String fullname, String username)
    {
        this.email = email;
        this.fullname = fullname;
        this.username = username;

        this.isAdmin = "no";
        this.donation = 0;

    }


    public void setDonation (double donation) {

       this.donation = donation;
    }

    public void setName (String name) {

        this.fullname = name;
    }

    public void setUsername (String name) {

        this.username = name;
    }

    public double getDonation() {

        return donation;
    }

    public String getUsername() {

        return username;
    }
    public String getName() {

        return fullname;
    }

    public String getEmail() {

        return email;
    }



}
