package com.example.contacts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.contacts.Models.Contact;
import com.example.contacts.Models.ContactViewModal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int ADD_CONTACT_REQUEST_CODE = 200;
    public static final int EDIT_CONTACT_REQUEST_CODE = 201;

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

        contactViewModal = new ViewModelProvider(this).get(ContactViewModal.class);
        contactViewModal.getContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                adapter.setContacts(contacts);
            }
        });

        FloatingActionButton addContactButton = findViewById(R.id.add_contact_button);
        addContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContactActivity.class);
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
                Toast.makeText(MainActivity.this, contact.getFullName() + " deleted from contacts", Toast.LENGTH_LONG).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new ContactAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Contact contact) {
                Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                intent.putExtra(ContactActivity.EXTRA_ID, contact.getId());
                intent.putExtra(ContactActivity.EXTRA_FIRST_NAME, contact.getFirstName());
                intent.putExtra(ContactActivity.EXTRA_LAST_NAME, contact.getLastName());
                intent.putExtra(ContactActivity.EXTRA_PHONE_NUMBER, contact.getPhoneNumber());
                intent.putExtra(ContactActivity.EXTRA_EMAIL_ADDRESS, contact.getEmail());
                startActivityForResult(intent, EDIT_CONTACT_REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_CONTACT_REQUEST_CODE && resultCode == RESULT_OK) {
            String firstName = data.getStringExtra(ContactActivity.EXTRA_FIRST_NAME);
            String lastName = data.getStringExtra(ContactActivity.EXTRA_LAST_NAME);
            String phoneNumber = data.getStringExtra(ContactActivity.EXTRA_PHONE_NUMBER);
            String emailAddress = data.getStringExtra(ContactActivity.EXTRA_EMAIL_ADDRESS);

            Contact contact = new Contact(firstName, lastName, phoneNumber, emailAddress);
            contactViewModal.insert(contact);
            Toast.makeText(this, contact.getFullName() + " added to contacts", Toast.LENGTH_LONG).show();
        } else if (requestCode == EDIT_CONTACT_REQUEST_CODE && resultCode == RESULT_OK) {
            int id = data.getIntExtra(ContactActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Can't update", Toast.LENGTH_SHORT).show();
                return;
            }
            String firstName = data.getStringExtra(ContactActivity.EXTRA_FIRST_NAME);
            String lastName = data.getStringExtra(ContactActivity.EXTRA_LAST_NAME);
            String phoneNumber = data.getStringExtra(ContactActivity.EXTRA_PHONE_NUMBER);
            String emailAddress = data.getStringExtra(ContactActivity.EXTRA_EMAIL_ADDRESS);

            Contact updatedContact = new Contact(firstName, lastName, phoneNumber, emailAddress);
            updatedContact.setId(id);
            contactViewModal.update(updatedContact);

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