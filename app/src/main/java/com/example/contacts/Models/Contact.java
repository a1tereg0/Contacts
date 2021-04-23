package com.example.contacts.Models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact_table")
public class Contact {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    @ColumnInfo(name = "first_name")
    private String firstName;

    @NonNull
    @ColumnInfo(name = "last_name")
    private String lastName;

    @NonNull
    @ColumnInfo(name = "phone_number")
    private String phoneNumber;

    private String email;

    public Contact(@NonNull String firstName,@NonNull String lastName, @NonNull String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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
