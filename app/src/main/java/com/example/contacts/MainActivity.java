package com.example.contacts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.contacts.Models.Contact;
import com.example.contacts.Models.ContactList;
import com.example.contacts.Models.ContactViewModal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int ADD_CONTACT_REQUEST_CODE = 200;

    private ContactViewModal contactViewModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        ContactAdapter adapter = new ContactAdapter();
        recyclerView.setAdapter(adapter);

        contactViewModal = ViewModelProviders.of(this).get(ContactViewModal.class);
        contactViewModal.getContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                adapter.setContacts(contacts);
            }
        });

        FloatingActionButton  addContactButton = findViewById(R.id.add_contact_button);
        addContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
                startActivityForResult(intent, ADD_CONTACT_REQUEST_CODE);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Contact contact = adapter.getContact(position);
                contactViewModal.delete(contact);
                Toast.makeText(MainActivity.this, contact.getFullName()+" deleted from contacts", Toast.LENGTH_LONG).show();
            }
        }).attachToRecyclerView(recyclerView);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_CONTACT_REQUEST_CODE && resultCode == RESULT_OK) {
            String firstName = data.getStringExtra(AddContactActivity.EXTRA_FIRST_NAME);
            String lastName = data.getStringExtra(AddContactActivity.EXTRA_LAST_NAME);
            String phoneNumber = data.getStringExtra(AddContactActivity.EXTRA_PHONE_NUMBER);
            String emailAddress = data.getStringExtra(AddContactActivity.EXTRA_EMAIL_ADDRESS);

            Contact contact = new Contact(firstName, lastName, phoneNumber, emailAddress);
            contactViewModal.insert(contact);
            Toast.makeText(this, contact.getFullName()+" added to contacts", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Contact not saved", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_contacts:
                contactViewModal.deleteAllContacts();
                Toast.makeText(MainActivity.this, "All contacts deleted", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}