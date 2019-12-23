package com.example.mathieuetthomas;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // sets values given by main activity
        Intent givenIntent = getIntent();
        TextView displayNom = findViewById(R.id.displayNom);
        displayNom.setText(givenIntent.getStringExtra(MainActivity.NOM_SAISI));
        TextView displayPrenom = findViewById(R.id.displayPrenom);
        displayPrenom.setText(givenIntent.getStringExtra(MainActivity.PRENOM_SAISI));
        TextView displayVilleNaissance = findViewById(R.id.displayVilleNaissance);
        displayVilleNaissance.setText(givenIntent.getStringExtra(MainActivity.VILLE_SAISIE));
        TextView displayDateNaissance = findViewById(R.id.displayDateNaissance);
        displayDateNaissance.setText(givenIntent.getStringExtra(MainActivity.DATE_SAISIE));
    }
}
