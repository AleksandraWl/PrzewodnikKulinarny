package com.example.malami.przewodnikkulinarny;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;


public class WyborJedzenia extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    private Toolbar toolbar;
    DatabaseReference db;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybor_jedzenia);

        firebaseAuth = FirebaseAuth.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        spinner = (findViewById(R.id.spinner));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.wyloguj:
                firebaseAuth.signOut();
                Toast.makeText(this, "Wylogowano", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(WyborJedzenia.this, Logowanie.class);
                startActivity(i);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void zatwierdz(View view) {
        Intent i = new Intent (WyborJedzenia.this, MapsActivity.class);
         startActivity(i);
    }

    public void Administrator(View view) {
        Intent i = new Intent (WyborJedzenia.this, administrator.class);
        startActivity(i);
    }
}

