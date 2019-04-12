package com.example.ezydonate;

public class Donation {

    int id;
    double amount;

    public Donation(int id, double amount) {

        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return this.id;
    }

    public double getAmount() {
        return this.amount;
    }

    @Override
    public String toString() {
        return this.id+"\t"+this.amount;
    }

}
