package com.example.malami.przewodnikkulinarny;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.solver.widgets.Snapshot;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.internal.cache.DiskLruCache;


public class WyborJedzenia extends AppCompatActivity{


    private Toolbar toolbar;
    DatabaseReference baza;
    Spinner spinner;
    FirebaseHelper firebase;
    Button wyloguj;
    Spinner spinner2;



    EditText NowyAdministrator;
    DatabaseReference administratorzy;
    FirebaseAuth firebaseAuth;
    String genere;
    Button zapytanie;
    DatabaseReference MDR;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybor_jedzenia);

        firebaseAuth = FirebaseAuth.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        wyloguj = findViewById(R.id.wyloguj);
        zapytanie = (Button) findViewById(R.id.button2);


       // NowyAdministrator = (findViewById(R.id.NowyAdministrator));
     //   administratorzy = FirebaseDatabase.getInstance().getReference("Administratorzy");


        spinner = (findViewById(R.id.spinner));
        baza = FirebaseDatabase.getInstance().getReference();
        firebase = new FirebaseHelper(baza);

        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ListaKategorie()));
        spinner2 = (findViewById(R.id.spinner2));

        View.OnClickListener l = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ArrayList<String> lista=new ArrayList<>();
                AlertDialog.Builder info = new AlertDialog.Builder(WyborJedzenia.this);
                genere = spinner.getSelectedItem().toString();
                MDR=FirebaseDatabase.getInstance().getReference("Kategorie").child(genere);
                info.setTitle(MDR.getKey() + " - informacja");
                info.setMessage(""+Info(MDR.getKey()));
                info.setPositiveButton("Ok", null);
                info.show();

            }
        };
        zapytanie.setOnClickListener(l);
    }



//lista do wyswietlania info
public ArrayList<String> Info(final String cos)
{
    final ArrayList<String> lista=new ArrayList<>();
    lista.clear();
   // lista.add("Kategoria");



    baza.addChildEventListener(new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            info(dataSnapshot,lista,cos);
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            info(dataSnapshot,lista,cos);

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

    private ArrayList<String> info(DataSnapshot snapshot,ArrayList<String> lista, String cos)
    {

        lista.clear();
        lista.add("Kategoria");
        for (DataSnapshot ds:snapshot.getChildren())
        {
            //lista.clear();
            if (cos.equals(ds.getValue(kategorie.class).getKategoria())) {
                String kat = ds.getValue(kategorie.class).getInformacja();
                lista.add(kat);
            }
        }
       // Toast.makeText(this, lista+"", Toast.LENGTH_LONG).show();
        return lista;
    }
//


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.wyloguj:
              //  AuthUI.getInstance().signOut(this);
                Intent i = new Intent(WyborJedzenia.this, Logowanie.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finish();
        }

        return super.onOptionsItemSelected(item);
    }


    public void zatwierdz(View view) {
        Intent i = new Intent (WyborJedzenia.this, MapsActivity.class);
         startActivity(i);

    }


//do spinnera kategorii
    public ArrayList<String> ListaKategorie()
    {
        final ArrayList<String> lista=new ArrayList<>();
        lista.clear();
        lista.add("Kategoria");


        baza.addChildEventListener(new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            fetchData(dataSnapshot,lista);
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            fetchData(dataSnapshot,lista);

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

    private void fetchData(DataSnapshot snapshot,ArrayList<String> lista)
    {

        lista.clear();
        lista.add("Kategoria");
        for (DataSnapshot ds:snapshot.getChildren())
        {
            String kategoria=ds.getValue(kategorie.class).getKategoria();
            lista.add(kategoria);
        }
    }
//

}

