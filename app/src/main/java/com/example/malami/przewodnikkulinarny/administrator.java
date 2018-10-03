package com.example.malami.przewodnikkulinarny;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class administrator extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator);

    }

    public void dodajAdministratora(View view) {
        Intent i = new Intent(administrator.this, DodawanieAdministratora.class);
        startActivity(i);
    }

    public void usunAdministratora(View view) {
        Intent i = new Intent (administrator.this, UsunAdministratora.class);
        startActivity(i);
    }


    public void dodawanieKategorii(View view) {
            Intent i = new Intent(administrator.this, DodawanieKategorii.class);
            startActivity(i);
        }


}



