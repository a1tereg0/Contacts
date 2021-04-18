package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.contacts.Models.Contact;
import com.example.contacts.Models.ContactList;
import com.example.contacts.Models.ContactViewModal;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ContactViewModal contactViewModal;

    ListView listView;
    ArrayList<Contact> contacts = ContactList.getContacts();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactViewModal = new ViewModelProvider(this).get(ContactViewModal.class);
        contactViewModal.getContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                // update RecyclerView or ListView
                Toast.makeText(MainActivity.this, "onChanged", Toast.LENGTH_LONG).show();
            }
        });

        ContactAdapter adapter = new ContactAdapter(this, contacts);
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                intent.putExtra("EXTRA_LIST_ITEM_POSITION", position);
                startActivity(intent);
            }
        });
    }
}