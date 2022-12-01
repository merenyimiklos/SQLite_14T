package com.example.sqlite_14t;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdatrogzitesActivity extends AppCompatActivity {

    private EditText editTextVeznev;
    private EditText editTextKernev;
    private EditText editTextJegy;

    private Button buttonRogzites;
    private Button buttonVisszaRog;

    private DBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adatrogzites);
        init();

        buttonVisszaRog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdatrogzitesActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonRogzites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vezeteknev = editTextVeznev.getText().toString().trim();
                String keresztnev = editTextKernev.getText().toString().trim();
                String jegyString = editTextJegy.getText().toString().trim();
                if (vezeteknev.isEmpty()
                        || keresztnev.isEmpty()
                        || jegyString.isEmpty()) {
                    Toast.makeText(AdatrogzitesActivity.this,
                            "Minden mező kitöltése kötelező",
                            Toast.LENGTH_SHORT).show();
                } else {
                    int jegy = Integer.parseInt(jegyString);
                    if (adatbazis.rogzites(vezeteknev, keresztnev, jegy)) {
                        Toast.makeText(AdatrogzitesActivity.this,
                                "Sikeres adatrögzítés",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AdatrogzitesActivity.this,
                                "Sikertelen adatrögzítés",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    public void init() {
        editTextVeznev = findViewById(R.id.editTextVeznev);
        editTextKernev = findViewById(R.id.editTextKernev);
        editTextJegy = findViewById(R.id.editTextJegy);

        buttonRogzites = findViewById(R.id.buttonRogzites);
        buttonVisszaRog = findViewById(R.id.buttonVisszaRog);
        adatbazis = new DBHelper(AdatrogzitesActivity.this);
    }
}