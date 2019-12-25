package com.example.mathieuetthomas;

import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity implements Home.OnHomeInteractionListener, Display.OnDisplayInteractionListener {

    private NavController navController;

    @BindView(R.id.button) Button valider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onHomeActivityInteraction(Uri uri, String nomSaisi, String prenomSaisi, String dateSaisie, String villeSaisie) {
        Bundle bundle = new Bundle();
        bundle.putString("nomSaisi", nomSaisi);
        bundle.putString("prenomSaisi", prenomSaisi);
        bundle.putString("dateSaisie", dateSaisie);
        bundle.putString("villeSaisie", villeSaisie);
        EditText phoneNumber = findViewById(R.id.edit_text_phone);
        if (phoneNumber != null) bundle.putString("phoneNumberSaisi", phoneNumber.getText().toString());
        navController.navigate(R.id.action_home_to_display, bundle);
    }

    @Override
    public void onDisplayInteraction(Uri uri) {
        navController.navigate(R.id.action_display_to_home);
    }

    public void reset(MenuItem item) {
        EditText nomSaisi = findViewById(R.id.editText2);
        if (nomSaisi != null) nomSaisi.setText("");
        EditText prenomSaisi = findViewById(R.id.editText3);
        if (prenomSaisi != null) prenomSaisi.setText("");
        EditText dateSaisie = findViewById(R.id.editText);
        if (dateSaisie != null) dateSaisie.setText("");
        Spinner villeSaisie = findViewById(R.id.editText4);
        if (villeSaisie != null) villeSaisie.setSelection(0);
        EditText phoneNumberSaisi = findViewById(R.id.edit_text_phone);
        if (phoneNumberSaisi != null) phoneNumberSaisi.setText("");
    }

    public void addPhoneNumber(MenuItem item) {
        LinearLayout rootView = findViewById(R.id.phoneNumberContainer);
        EditText phoneNumberInput = new EditText(this);
        phoneNumberInput.setId(R.id.edit_text_phone);
        phoneNumberInput.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        phoneNumberInput.setInputType(InputType.TYPE_CLASS_PHONE);
        rootView.addView(phoneNumberInput);
    }
}
