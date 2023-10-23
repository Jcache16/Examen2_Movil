package com.example.examen2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class receta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receta);
        Button btnRegresarReceta = findViewById(R.id.btnRegresarReceta);
        TextView textViewVariable = findViewById(R.id.TVTitulo);
        EditText etTitulo = findViewById(R.id.etTituloReceta);
        EditText etID = findViewById(R.id.etIDReceta);
        EditText etDescripcion = findViewById(R.id.etDescripcionReceta);
        CRUDRecetas CRUD = new CRUDRecetas(this);

        // Obtener el título pasado desde la actividad principal
        String titulo = getIntent().getStringExtra("titulo");
        // Mostrar el título en el TextView
        textViewVariable.setText(titulo);

        btnRegresarReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento0 = new Intent(receta.this,MainActivity.class);
                startActivity(intento0);
            }
        });

        Cursor cursor = CRUD.mostrarRecetas();
        while (cursor.moveToNext()) {
            etID.setText(cursor.getString(0));
            etTitulo.setText(cursor.getString(1));
            etDescripcion.setText(cursor.getString(2));
        }
    }
}