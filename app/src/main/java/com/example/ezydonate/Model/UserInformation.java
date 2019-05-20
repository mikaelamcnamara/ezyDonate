package com.example.ezydonate.Model;

public class UserInformation {

    private String fullName;
    private String email;
   // private String password;
    private String UID;

    public UserInformation(){}

    public UserInformation(String fullname, String email) {
        this.fullName = fullname;
        this.email = email;
        //this.password = password;
    }

    public String getFullname() { return fullName; }

    public void setFullname(String fullname) { this.fullName = fullname; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

   // public String getPassword() {return password; }

    //public void setPassword(String password) { this.password = password; }

    public String getUID() {return UID;}

    public void setUID(String UID) { this.UID = UID; }




}
