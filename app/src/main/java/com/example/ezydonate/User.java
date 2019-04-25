package com.example.ezydonate;


// User class of donors that will be using the application //
public class User {

    private String username;
    private String password;
    private double donated;


    // Will require encryption of password in future release //
    User( String new_username, String new_password) {
        this.username = new_username;
        this.password = new_password ;
    }

    // Sets the username of the user if a username change is prompted //
    public void setUsername(String new_username) {

        new_username = this.username;
    }

    // Sets the password of the user if a username change is prompted //
    public void setPassword(String new_password) {

        new_password = this.password;
    }

    // Verifies the login input and returns a true value if input is correct //
    public boolean login(String username, String password) {

        return username.equals(this.username) && password.equals(this.password);
    }

    // Gets the Username //
    public String getUsername() {

        return username;
    }

    // Gets the Password //
    public String getPassword() {

        return password;
    }

    public void makeDonation(int amount) {

        donated += amount;

    }


}
