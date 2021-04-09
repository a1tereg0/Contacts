package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.contacts.Models.Contact;
import com.example.contacts.Models.ContactList;

public class ContactActivity extends AppCompatActivity {

    ImageButton callButton;
    ImageButton messageButton;
    ImageButton mailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        int position = getIntent().getIntExtra("EXTRA_LIST_ITEM_POSITION", -1);
        Contact contact = ContactList.getContacts().get(position);

        TextView firstName = (TextView) findViewById(R.id.text_view_firstName);
        TextView lastName = (TextView) findViewById(R.id.text_view_lastName);
        TextView phoneNumber = (TextView) findViewById(R.id.text_view_phone);
        TextView email = (TextView) findViewById(R.id.text_view_email);
        firstName.setText(contact.getFirstName());
        lastName.setText(contact.getLastName());
        phoneNumber.setText(contact.getPhoneNumber());
        email.setText(contact.getEmail());
        callButton = (ImageButton) findViewById(R.id.call_button);
        messageButton = (ImageButton) findViewById(R.id.message_button);
        mailButton = (ImageButton) findViewById(R.id.mail_button);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+contact.getPhoneNumber()));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:"+contact.getPhoneNumber()));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        mailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"+contact.getEmail()));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });


    }



}