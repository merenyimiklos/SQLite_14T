package com.example.sqlite_14t;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TorlesActivity extends AppCompatActivity {

    private EditText editTextIdTor;
    private Button buttonTorles;
    private Button buttonVisszaTor;
    private DBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torles);
        init();
        buttonVisszaTor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TorlesActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonTorles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = editTextIdTor.getText().toString().trim();
                if (id.isEmpty()){
                    Toast.makeText(TorlesActivity.this,
                            "Minden mező kitöltése kötelező", Toast.LENGTH_SHORT).show();
                } else {
                    if (adatbazis.torles(id) == 0) {
                        Toast.makeText(TorlesActivity.this,
                                "Nincs ilyen ID-u rekord", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(TorlesActivity.this,
                                "Sikeres rekord törlés", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void init() {
        editTextIdTor = findViewById(R.id.editTextIdTor);
        buttonTorles = findViewById(R.id.buttonTorles);
        buttonVisszaTor = findViewById(R.id.buttonVisszaTor);
        adatbazis = new DBHelper(TorlesActivity.this);
    }
}