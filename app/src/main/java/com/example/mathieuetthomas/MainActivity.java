package com.example.mathieuetthomas;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.textView2) TextView nom;
    @BindView(R.id.textView) TextView prenom;
    @BindView(R.id.textView4) TextView date;
    @BindView(R.id.textView6) TextView ville_de_naissance;
    @BindView(R.id.editText2) EditText nomSaisi;
    public static final String NOM_SAISI = "com.example.mathieuetthomas.NOM_SAISI";
    @BindView(R.id.editText3) EditText prenomSaisi;
    public static final String PRENOM_SAISI = "com.example.mathieuetthomas.PRENOM_SAISI";
    @BindView(R.id.editText) EditText dateSaisie;
    public static final String DATE_SAISIE = "com.example.mathieuetthomas.DATE_SAISIE";
    @BindView(R.id.editText4) Spinner villeSaisie;
    public static final String VILLE_SAISIE = "com.example.mathieuetthomas.VILLE_SAISIE";
    @BindView(R.id.button) Button valider;

    private RecyclerView recyclerView;
    private List<Contact> contactList;
    private ContactsAdapter mAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        // create the data
        contactList = new ArrayList<>();
        contactList.add(new Contact("Nabila", "https://voi.img.pmdstatic.net/fit/http.3A.2F.2Fprd2-bone-image.2Es3-website-eu-west-1.2Eamazonaws.2Ecom.2Fvoi.2F2018.2F03.2F20.2Fd3bc6b27-9de7-4fd2-a9d5-010f117d02fa.2Ejpeg/480x270/quality/80/photos-nabilla-affiche-ses-formes-dans-un-bikini-qui-ne-cache-pas-grand-chose.jpg","06 66 66"));
        contactList.add(new Contact("Jean-Claude", "https://loopnewslive.blob.core.windows.net/liveimage/sites/default/files/2017-12/tb4klb9tqc.jpg","06 66 66"));
        contactList.add(new Contact("Patrick", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/29/Patrick_Bruel_Cabourg_2012.jpg/220px-Patrick_Bruel_Cabourg_2012.jpg","06 66 66"));
        contactList.add(new Contact("Brit'", "https://upload.wikimedia.org/wikipedia/commons/d/da/Britney_Spears_2013_%28Straighten_Crop%29.jpg","+31 66 66"));

        // handle the recycler view
        recyclerView = findViewById(R.id.recycler_view);
        mAdapter = new ContactsAdapter(this, contactList, (ContactsAdapter.ContactsAdapterListener) this); //(this, contactList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

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
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
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

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }


    public void onContactSelected(Contact contact) {
        Toast.makeText(getApplicationContext(), "Selected: " + contact.getName() + ", " + contact.getPhone(), Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.button)
    public void valider(View view) {
        //Toast.makeText(getApplicationContext(), "Nom: " + nomSaisi.getText() + "Prénom: " + prenomSaisi.getText() + "Date: " + dateSaisie.getText() + "Ville: " + villeSaisie.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
        Intent displayIntent = new Intent(this, DisplayActivity.class);
        displayIntent.putExtra(NOM_SAISI, this.nomSaisi.getText().toString());
        displayIntent.putExtra(PRENOM_SAISI, this.prenomSaisi.getText().toString());
        displayIntent.putExtra(VILLE_SAISIE, this.villeSaisie.getSelectedItem().toString());
        displayIntent.putExtra(DATE_SAISIE, this.dateSaisie.getText().toString());
        startActivity(displayIntent);
    }

    public void reset(MenuItem item) {
        nomSaisi.getText().clear();
        prenomSaisi.getText().clear();
        dateSaisie.getText().clear();
        villeSaisie.setSelection(0);
    }

    public void addPhoneNumber(MenuItem item) {
        LinearLayout rootView = findViewById(R.id.phoneNumberContainer);
        EditText phoneNumberInput = new EditText(this);
        phoneNumberInput.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        phoneNumberInput.setInputType(InputType.TYPE_CLASS_PHONE);
        rootView.addView(phoneNumberInput);
    }

    public void wikipedia(MenuItem item) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://fr.wikipedia.org"));
        startActivity(intent);
    }
}
