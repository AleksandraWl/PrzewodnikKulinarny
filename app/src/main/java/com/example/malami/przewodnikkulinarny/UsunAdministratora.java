package com.example.malami.przewodnikkulinarny;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UsunAdministratora extends AppCompatActivity {

    Spinner spinner;
    FirebaseHelper helper;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usun_administratora);

        spinner = (findViewById(R.id.spinner));
        db = FirebaseDatabase.getInstance().getReference();
        helper = new FirebaseHelper(db);

        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ListaAdministratorow()));

    }


    public ArrayList<String> ListaAdministratorow()
    {
        final ArrayList<String> lista=new ArrayList<>();
        lista.clear();
        lista.add("Administratorzy");


        db.addChildEventListener(new ChildEventListener() {
            @Override

            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Administratorzy(dataSnapshot,lista);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Administratorzy(dataSnapshot,lista);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return lista;
    }

    private void Administratorzy(DataSnapshot snapshot, ArrayList<String> lista)
    {
        lista.clear();
        lista.add("Administratorzy");
        for (DataSnapshot ds:snapshot.getChildren())
        {
            String administrator=ds.getValue(admin.class).getEmail();
            lista.add(administrator);
        }
    }
}
