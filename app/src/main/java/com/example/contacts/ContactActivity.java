package com.example.contacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ContactActivity extends AppCompatActivity {


    public static final String EXTRA_ID = "com.example.contacts.EXTRA_ID";
    public static final String EXTRA_FIRST_NAME = "com.example.contacts.EXTRA_FIRST_NAME";
    public static final String EXTRA_LAST_NAME = "com.example.contacts.EXTRA_LAST_NAME";
    public static final String EXTRA_PHONE_NUMBER = "com.example.contacts.EXTRA_PHONE_NUMBER";
    public static final String EXTRA_EMAIL_ADDRESS = "com.example.contacts.EXTRA_EMAIL_ADDRESS";

    ImageButton callButton;
    ImageButton messageButton;
    ImageButton mailButton;

    EditText editTextFirstName;
    EditText editTextLastName;
    EditText editTextPhoneNumber;
    EditText editTextEmailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        editTextFirstName = findViewById(R.id.first_name_edit_text);
        editTextLastName = findViewById(R.id.last_name_edit_text);
        editTextPhoneNumber = findViewById(R.id.phone_edit_text);
        editTextEmailAddress = findViewById(R.id.email_edit_text);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit a contact");
            editTextFirstName.setText(intent.getStringExtra(EXTRA_FIRST_NAME));
            editTextLastName.setText(intent.getStringExtra(EXTRA_LAST_NAME));
            editTextEmailAddress.setText(intent.getStringExtra(EXTRA_EMAIL_ADDRESS));
            editTextPhoneNumber.setText(intent.getStringExtra(EXTRA_PHONE_NUMBER));
            callButton = (ImageButton) findViewById(R.id.call_button);
            messageButton = (ImageButton) findViewById(R.id.message_button);
            mailButton = (ImageButton) findViewById(R.id.mail_button);
            callButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+editTextPhoneNumber.getText().toString()));
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
            });

            messageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("smsto:"+editTextPhoneNumber.getText().toString()));
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
            });

            mailButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:"+editTextEmailAddress.getText().toString()));
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
            });
        } else {
            setTitle("Add a contact");
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_add_contact, menu);
        return true;
    }

    private void saveContact() {
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();
        String emailAddress = editTextEmailAddress.getText().toString().trim();
        if (firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() || emailAddress.isEmpty()) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_FIRST_NAME, firstName);
        intent.putExtra(EXTRA_LAST_NAME, lastName);
        intent.putExtra(EXTRA_PHONE_NUMBER, phoneNumber);
        intent.putExtra(EXTRA_EMAIL_ADDRESS, emailAddress);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if ( id != -1) {
            intent.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_contact:
                saveContact();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}