package com.example.contacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddContactActivity extends AppCompatActivity {

    public static final String EXTRA_FIRST_NAME = "com.example.contacts.EXTRA_FIRST_NAME";
    public static final String EXTRA_LAST_NAME = "com.example.contacts.EXTRA_LAST_NAME";
    public static final String EXTRA_PHONE_NUMBER = "com.example.contacts.EXTRA_PHONE_NUMBER";
    public static final String EXTRA_EMAIL_ADDRESS = "com.example.contacts.EXTRA_EMAIL_ADDRESS";

    EditText editTextFirstName;
    EditText editTextLastName;
    EditText editTextPhoneNumber;
    EditText editTextEmailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        editTextFirstName = findViewById(R.id.first_name_edit_text);
        editTextLastName = findViewById(R.id.last_name_edit_text);
        editTextPhoneNumber = findViewById(R.id.phone_edit_text);
        editTextEmailAddress = findViewById(R.id.email_edit_text);
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