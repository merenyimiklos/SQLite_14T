package com.example.sqlite_14t;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModositasActivity extends AppCompatActivity {

    private EditText editTextIdMod;
    private EditText editTextVezNevMod;
    private EditText editTextKerNevMod;
    private EditText editTextJegyMod;
    private Button buttonModositas;
    private Button buttonVisszaMod;
    private DBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modositas);
        init();

        buttonVisszaMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ModositasActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonModositas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = editTextIdMod.getText().toString().trim();
                String vezeteknev = editTextVezNevMod.getText().toString().trim();
                String keresztnev = editTextKerNevMod.getText().toString().trim();
                String jegy = editTextJegyMod.getText().toString().trim();
                if (id.isEmpty() ||
                    vezeteknev.isEmpty() ||
                    keresztnev.isEmpty() ||
                    jegy.isEmpty()){
                    Toast.makeText(ModositasActivity.this,
                            "Minden mező kitöltése kötelező", Toast.LENGTH_SHORT).show();
                } else {
                    if (adatbazis.modositas(id, vezeteknev, keresztnev, jegy) == 0){
                        Toast.makeText(ModositasActivity.this,
                                "Sikertelen módosítás, az adott id-val nem létezik rekord",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ModositasActivity.this,
                                "Sikeres módosítás", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void init() {
        editTextIdMod = findViewById(R.id.editTextIdMod);
        editTextVezNevMod = findViewById(R.id.editTextVezNevMod);
        editTextKerNevMod = findViewById(R.id.editTextKerNevMod);
        editTextJegyMod = findViewById(R.id.editTextJegyMod);
        buttonModositas = findViewById(R.id.buttonModosit);
        buttonVisszaMod = findViewById(R.id.buttonVisszaMod);
        adatbazis = new DBHelper(ModositasActivity.this);
    }
}