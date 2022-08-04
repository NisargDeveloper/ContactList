package com.example.sqlitedb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    // creating variables for our edittext, button and dbhandler
    private EditText NameEdt, phoneNumberEdt, EmailEdt, AddressEdt;
    private Button addContactBtn;
    private Button readContactBtn;
    private Button searchContactBtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing all our variables.
        NameEdt = findViewById(R.id.idEdtName);
        phoneNumberEdt = findViewById(R.id.idEdtphoneNumber);
        EmailEdt = findViewById(R.id.idEdtEmail);
        AddressEdt = findViewById(R.id.idEdtAddress);
        addContactBtn = findViewById(R.id.idBtnAddContact);
        readContactBtn = findViewById(R.id.idBtnReadContact);
        searchContactBtn = findViewById(R.id.idBtnSearchContact);

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(MainActivity.this);

        // below line is to add on click listener for our add course button.
        addContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String Name = NameEdt.getText().toString();
                String phoneNumber = phoneNumberEdt.getText().toString();
                String Address = AddressEdt.getText().toString();
                String Email = EmailEdt.getText().toString();

                // validating if the text fields are empty or not.
                if (Name.isEmpty() && phoneNumber.isEmpty() && Email.isEmpty() && Address.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method to add new
                // course to sqlite data and pass all our values to it.
                dbHandler.addNewContact(Name, phoneNumber, Email, Address);

                // after adding the data we are displaying a toast message.
                Toast.makeText(MainActivity.this, "Contact has been added.", Toast.LENGTH_SHORT).show();
                NameEdt.setText("");
                phoneNumberEdt.setText("");
                EmailEdt.setText("");
                AddressEdt.setText("");
            }
        });

    // Read all contacts
        readContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opening a new activity via a intent.
                Intent i = new Intent(MainActivity.this, ViewContacts.class);
                startActivity(i);
            }
        });

    // Search contact
        searchContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = NameEdt.getText().toString();
                //Check if Name field is not empty
                if(!Name.isEmpty()) {
                    // opening a new activity via a intent.
                    Intent i = new Intent(MainActivity.this, SearchContacts.class);
                    i.putExtra("name", Name);
                    startActivity(i);
                } else {
                    Toast.makeText(MainActivity.this, "Please provide search string in Enter Name field", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}