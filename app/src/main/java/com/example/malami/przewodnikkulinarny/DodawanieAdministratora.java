package com.example.malami.przewodnikkulinarny;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DodawanieAdministratora extends AppCompatActivity {

    EditText NowyAdministrator;
    DatabaseReference administratorzy;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodawanie_administratora);
        NowyAdministrator=(findViewById(R.id.NowyAdministrator));
        administratorzy = FirebaseDatabase.getInstance().getReference("Administratorzy");
    }

    public void dodajAdministratora(View view) {
        email = NowyAdministrator.getText().toString().trim();

        if(!TextUtils.isEmpty(email))
        {
            admin admin = new admin(email);
            String id= administratorzy.push().getKey();
            administratorzy.child(id).setValue(admin);
            Toast.makeText(this, "Ok, dodano", Toast.LENGTH_SHORT).show();
        }
        else             Toast.makeText(this, "Nie dodano", Toast.LENGTH_SHORT).show();

    }

}
