package com.example.sqlite_14t;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonAdatokOlvasas;
    private Button buttonAdatokRogzitese;
    private Button buttonAdatokModositasa;
    private Button buttonAdatokTorlese;

    private TextView textViewLista;

    private DBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        textViewLista.setMovementMethod(new ScrollingMovementMethod());
        buttonAdatokRogzitese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AdatrogzitesActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonAdatokOlvasas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor adatok = adatbazis.listaz();
                if (adatok.getCount() == 0) {
                    Toast.makeText(MainActivity.this,
                            "Nincs az adatbázisban bejegyzés", Toast.LENGTH_SHORT).show();
                } else {
                    StringBuilder bobTheBuilder = new StringBuilder();
                    while (adatok.moveToNext()){
                        bobTheBuilder.append("ID: ").append(adatok.getInt(0));
                        bobTheBuilder.append(System.lineSeparator());
                        bobTheBuilder.append("Vezetéknév: ").append(adatok.getString(1));
                        bobTheBuilder.append(System.lineSeparator());
                        bobTheBuilder.append("Keresztnév: ").append(adatok.getString(2));
                        bobTheBuilder.append(System.lineSeparator());
                        bobTheBuilder.append("JEGY: ").append(adatok.getInt(3));
                        bobTheBuilder.append(System.lineSeparator());
                        bobTheBuilder.append(System.lineSeparator());
                    }
                    textViewLista.setText(bobTheBuilder.toString());
                }
            }
        });
    }

    public void init() {
        buttonAdatokRogzitese = findViewById(R.id.buttonAdatokRogzitesere);
        buttonAdatokOlvasas = findViewById(R.id.buttonAdatolvasas);
        buttonAdatokModositasa = findViewById(R.id.buttonAdatokModositasra);
        buttonAdatokTorlese = findViewById(R.id.buttonAdatokTorlese);

        textViewLista = findViewById(R.id.textViewLista);

        adatbazis = new DBHelper(MainActivity.this);
    }
}