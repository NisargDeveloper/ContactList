package com.example.sqlitedb;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactRVAdapter extends RecyclerView.Adapter<ContactRVAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<ContactModal> contactModalArrayList;
    private Context context;

    // constructor
    public ContactRVAdapter(ArrayList<ContactModal> courseModalArrayList, Context context) {
        this.contactModalArrayList = courseModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        ContactModal modal = contactModalArrayList.get(position);
        holder.NameTV.setText(modal.getName());
        holder.PhoneNumberTV.setText(modal.getPhoneNumber());
        holder.EmailTV.setText(modal.getEmail());
        holder.AddressTV.setText(modal.getAddress());


        // below line is to add on click listener for our recycler view item.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on below line we are calling an intent.
                Intent i = new Intent(context, UpdateContactActivity.class);

                // below we are passing all our values.
                i.putExtra("Name", modal.getName());
                i.putExtra("PhoneNumber", modal.getPhoneNumber());
                i.putExtra("Email", modal.getEmail());
                i.putExtra("Address", modal.getAddress());

                // starting our activity.
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return contactModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView NameTV, PhoneNumberTV, EmailTV, AddressTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
             NameTV = itemView.findViewById(R.id.idTVContactName);
             PhoneNumberTV = itemView.findViewById(R.id.idTVPhoneNumber);
             EmailTV = itemView.findViewById(R.id.idTVEmail);
             AddressTV = itemView.findViewById(R.id.idTVAddress);
        }
    }
}
