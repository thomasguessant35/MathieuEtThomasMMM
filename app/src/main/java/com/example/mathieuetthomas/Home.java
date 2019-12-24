package com.example.mathieuetthomas;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import butterknife.BindView;

public class Home extends Fragment {

    private OnHomeInteractionListener mListener;
    @BindView(R.id.editText2) EditText nomSaisi;
    @BindView(R.id.editText3) EditText prenomSaisi;
    @BindView(R.id.editText) EditText dateSaisie;
    @BindView(R.id.editText4) Spinner villeSaisie;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_home, container, false);
        this.nomSaisi = v.findViewById(R.id.editText2);
        this.prenomSaisi = v.findViewById(R.id.editText3);
        this.dateSaisie = v.findViewById(R.id.editText);
        this.villeSaisie = v.findViewById(R.id.editText4);
        Button b = v.findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed(null);
            }
        });

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            String nomSaisiString = this.nomSaisi.getText().toString();
            String prenomSaisiString = this.prenomSaisi.getText().toString();
            String dateSaisieString = this.dateSaisie.getText().toString();
            String villeSaisie = this.villeSaisie.getSelectedItem().toString();
            mListener.onHomeActivityInteraction(uri, nomSaisiString, prenomSaisiString, dateSaisieString, villeSaisie);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnHomeInteractionListener) {
            mListener = (OnHomeInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public interface OnHomeInteractionListener {
        // TODO: Update argument type and name
        void onHomeActivityInteraction(Uri uri, String nomSaisi, String prenomSaisi, String dateSaisie, String villeSaisie);
    }
}
