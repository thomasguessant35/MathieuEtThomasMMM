package com.example.mathieuetthomas;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddClient.OnAddClientInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddClient#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddClient extends Fragment {

    private OnAddClientInteractionListener mListener;
    private List<Client> clients;
    private ViewModel viewModel;
    ClientAdapter adapter;

    public AddClient() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AddClient.
     */
    // TODO: Rename and change types and number of parameters
    public static AddClient newInstance() {
        AddClient fragment = new AddClient();
        Bundle args = new Bundle();
        fragment.clients = new ArrayList<>();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new ClientAdapter(this.clients);
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        // et on ajoute un observer sur les utilisateurs...
        viewModel.getAllClients().observe(this, clients -> adapter.setClients(clients));
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.delete(adapter.getClientAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getContext(), "Client deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView((RecyclerView) getView());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_client, container, false);
        // Lookup the recyclerview in activity layout
        RecyclerView rvContacts = view.findViewById(R.id.recyclerView);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(this.adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(getContext()));
        Button b = view.findViewById(R.id.add_client);
        b.setOnClickListener(viewLambda -> onButtonPressed(null));
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onAddClient(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAddClientInteractionListener) {
            mListener = (OnAddClientInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    public interface OnAddClientInteractionListener {
        void onAddClient(Uri uri);
    }
}
