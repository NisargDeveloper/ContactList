package com.example.sqlitedb;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewContacts extends AppCompatActivity {

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<ContactModal> contactModalArrayList;
    private DBHandler dbHandler;
    private ContactRVAdapter contactRVAdapter;
    private RecyclerView contactsRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contacts);

        // initializing our all variables.
        contactModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ViewContacts.this);

        // getting our course array
        // list from db handler class.
        contactModalArrayList = dbHandler.readContacts();

        // on below line passing our array lost to our adapter class.
        contactRVAdapter = new ContactRVAdapter(contactModalArrayList, ViewContacts.this);
        contactsRV = findViewById(R.id.idTVContacts);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewContacts.this, RecyclerView.VERTICAL, false);
        contactsRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        contactsRV.setAdapter(contactRVAdapter);
    }
}
