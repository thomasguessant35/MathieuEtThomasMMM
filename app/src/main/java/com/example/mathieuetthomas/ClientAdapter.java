package com.example.mathieuetthomas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClientAdapter extends  RecyclerView.Adapter<ClientAdapter.ViewHolder> {

    private List<Client> mContacts;

    public ClientAdapter(List<Client> mContacts) {
        this.mContacts = mContacts;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public ClientAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_client, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ClientAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Client contact = mContacts.get(position);

        // Set item views based on your views and data model
        TextView textViewNom = viewHolder.nomTextView;
        textViewNom.setText(contact.getNom());
        TextView textViewPrenom = viewHolder.prenomTextView;
        textViewPrenom.setText(contact.getPrenom());
        TextView textViewDate = viewHolder.dateTextView;
        textViewDate.setText(contact.getDate());
        TextView textViewVille = viewHolder.villeTextView;
        textViewVille.setText(contact.getVille());
        TextView textViewPhone = viewHolder.phoneTextView;
        textViewPhone.setText(contact.getPhone());
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nomTextView;
        public TextView prenomTextView;
        public TextView dateTextView;
        public TextView villeTextView;
        public TextView phoneTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nomTextView = (TextView) itemView.findViewById(R.id.client_nom);
            prenomTextView = (TextView) itemView.findViewById(R.id.client_prenom);
            dateTextView = (TextView) itemView.findViewById(R.id.client_date);
            villeTextView = (TextView) itemView.findViewById(R.id.client_ville);
            phoneTextView = (TextView) itemView.findViewById(R.id.client_phone);
        }
    }
}
