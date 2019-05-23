package com.example.ezydonate;

import android.graphics.Bitmap;

public class User
{
    public String email;
    public String fullname;
    public String username;
    public String image;
    public User(String email, String fullname, String username, String image)
    {
        this.email = email;
        this.fullname = fullname;
        this.username = username;
        this.image = image;
    }
}
