package com.example.contacts.Models;

import java.util.ArrayList;

public class ContactList {
    private static final ContactList instance = new ContactList();
    private static ArrayList<Contact> contacts;


    private ContactList() {
        contacts = new ArrayList<>();
        contacts.add(new Contact("Prashant", "Dahal", "9843456287", "prashantdah41@gmail.com"));
        contacts.add(new Contact("Rikesh", "Awale", "555555555", "awalerikesh@gmail.com"));
        contacts.add(new Contact("Sulav", "Panday", "123222444", "sulavpanday@gmail.com"));
        contacts.add(new Contact("Prakriti", "Dahal", "9832555224", "dahalprakriti@gmail.com"));
        contacts.add(new Contact("Sujan", "Shrestha", "9841454289", "suzanshrestha1@gmail.com"));
        contacts.add(new Contact("Sujan", "Shrestha", "9841454289", "suzanshrestha1@gmail.com"));
        contacts.add(new Contact("Sujan", "Shrestha", "9841454289", "suzanshrestha1@gmail.com"));
        contacts.add(new Contact("Sujan", "Shrestha", "9841454289", "suzanshrestha1@gmail.com"));
        contacts.add(new Contact("Sujan", "Shrestha", "9841454289", "suzanshrestha1@gmail.com"));
        contacts.add(new Contact("Sujan", "Shrestha", "9841454289", "suzanshrestha1@gmail.com"));
        contacts.add(new Contact("Sujan", "Shrestha", "9841454289", "suzanshrestha1@gmail.com"));
    }

    public static ContactList getInstance() {
        return instance;
    }

    public static ArrayList<Contact> getContacts() {
        return contacts;
    }

    public static void setContacts(ArrayList<Contact> contacts) {
        ContactList.contacts = contacts;
    }
}
