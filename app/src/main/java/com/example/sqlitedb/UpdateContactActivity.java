package com.example.sqlitedb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateContactActivity extends AppCompatActivity {

    // variables for our edit text, button, strings and dbhandler class.
    private EditText NameEdt, PhoneNumberEdt, EmailEdt, AddressEdt;
    private Button updateContactBtn;
    private Button deleteContactBtn;
    private DBHandler dbHandler;
    String  Name, PhoneNumber, Email, Address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);

        // initializing all our variables.
        NameEdt = findViewById(R.id.idEdtName);
        PhoneNumberEdt = findViewById(R.id.idEdtPhoneNumber);
        EmailEdt = findViewById(R.id.idEdtEmail);
        AddressEdt = findViewById(R.id.idEdtAddress);
        updateContactBtn = findViewById(R.id.idBtnUpdateContact);
        deleteContactBtn = findViewById(R.id.idBtnDelete);

        // on below line we are initialing our dbhandler class.
        dbHandler = new DBHandler(UpdateContactActivity.this);

        // on below lines we are getting data which
        // we passed in our adapter class.
        Name = getIntent().getStringExtra("Name");
        PhoneNumber = getIntent().getStringExtra("PhoneNumber");
        Email = getIntent().getStringExtra("Email");
        Address = getIntent().getStringExtra("Address");

        // setting data to edit text
        // of our update activity.
         NameEdt.setText(Name);
        PhoneNumberEdt.setText(PhoneNumber);
        EmailEdt.setText(Email);
        AddressEdt.setText(Address);

        // adding on click listener to our update contact button.
        updateContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // inside this method we are calling an update course
                // method and passing all our edit text values.
                dbHandler.updateContact(Name, NameEdt.getText().toString(), PhoneNumberEdt.getText().toString(), EmailEdt.getText().toString(), AddressEdt.getText().toString());

                // displaying a toast message that our course has been updated.
                Toast.makeText(UpdateContactActivity.this, "Contact Updated..", Toast.LENGTH_SHORT).show();

                // launching our main activity.
                Intent i = new Intent(UpdateContactActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        // adding on click listener for delete button to delete our contact.
        deleteContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to delete our contact.
                dbHandler.deleteContact(Name);
                Toast.makeText(UpdateContactActivity.this, "Deleted the contact", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateContactActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}
