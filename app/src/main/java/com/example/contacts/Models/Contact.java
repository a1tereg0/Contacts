package com.example.contacts.Models;

public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    public Contact(String firstName, String lastName, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public boolean setFirstName(String firstName) {
        if (!firstName.trim().equals("")){
            this.firstName = firstName;
            return true;
        }
        return false;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean setLastName(String lastName) {
        if (!lastName.trim().equals("")){
            this.lastName = lastName;
            return true;
        }
        return false;
    }

    public String getFullName() {
        return this.firstName+" "+this.lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean setPhoneNumber(String phoneNumber) {
        if (!phoneNumber.trim().equals("")){
            this.phoneNumber = phoneNumber;
            return true;
        }
        return false;
    }

    public String getEmail() {
        return email;
    }

    public boolean setEmail(String email) {
        if (!email.trim().equals("")){
            this.email = email;
            return true;
        }
        return false;
    }
}
