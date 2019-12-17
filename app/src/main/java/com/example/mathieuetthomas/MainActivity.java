package com.example.mathieuetthomas;

import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.textView2) TextView nom;
    @BindView(R.id.textView) TextView prenom;
    @BindView(R.id.textView4) TextView date;
    @BindView(R.id.textView6) TextView ville_de_naissance;
    @BindView(R.id.editText2) EditText nomSaisi;
    @BindView(R.id.editText3) EditText prenomSaisi;
    @BindView(R.id.editText) EditText dateSaisie;
    @BindView(R.id.editText4) EditText villeSaisie;
    @BindView(R.id.button) Button valider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

    @OnClick(R.id.button)
    public void valider(View view) {
        Toast.makeText(getApplicationContext(), "Nom: " + nomSaisi.getText() + "Prénom: " + prenomSaisi.getText() + "Date: " + dateSaisie.getText() + "Ville: " + villeSaisie.getText(), Toast.LENGTH_LONG).show();
    }

    public void reset(MenuItem item) {
        nomSaisi.getText().clear();
        prenomSaisi.getText().clear();
        dateSaisie.getText().clear();
        villeSaisie.getText().clear();
    }

    public void addPhoneNumber(MenuItem item) {
        LinearLayout rootView = findViewById(R.id.phoneNumberContainer);
        EditText phoneNumberInput = new EditText(this);
        phoneNumberInput.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        phoneNumberInput.setInputType(InputType.TYPE_CLASS_PHONE);
        rootView.addView(phoneNumberInput);
    }
}
