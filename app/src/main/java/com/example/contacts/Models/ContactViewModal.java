package com.example.contacts.Models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ContactViewModal extends AndroidViewModel {

    private ContactRepository repository;
    private LiveData<List<Contact>> contacts;

    public ContactViewModal(@NonNull Application application) {
        super(application);
        repository = new ContactRepository(application);
        contacts = repository.getContacts();
    }

    public void insert(Contact contact) {
        repository.insert(contact);
    }

    public void update(Contact contact) {
        repository.update(contact);
    }

    public void delete(Contact contact) {
        repository.delete(contact);
    }

    public void deleteAllContacts() {
        repository.deleteAllContacts();
    }

    public LiveData<List<Contact>> getContacts() {
        return contacts;
    }
}
