package com.example.ezydonate;

import java.util.LinkedList;

public class History {

    private final App app;
    private final LinkedList<Donation> donationsMade;


    public History(App app) {

        this.app = app;
        donationsMade = new LinkedList();

    }


    private void makeDonation(Donation donation) {

            donationsMade.add(donation);

    }

    public User getUser(String username) {
        return this.app.getUser(username);
    }
}