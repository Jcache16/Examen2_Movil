package com.example.examen2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class editar_receta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_receta);
        EditText etTitulo = findViewById(R.id.etTituloedit);
        EditText etID = findViewById(R.id.etIDedit);
        EditText etDescripcion = findViewById(R.id.etDescripcionedit);
        CRUDRecetas CRUD = new CRUDRecetas(this);
        Button btnRegresar = findViewById(R.id.btnRegresar);
        Button btnGuardarReceta = findViewById(R.id.btnGuardarEdit);

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento0 = new Intent(editar_receta.this,MainActivity.class);
                startActivity(intento0);
            }
        });

        btnGuardarReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = etTitulo.getText().toString();
                String descripcion = etDescripcion.getText().toString();
                String idText = etID.getText().toString();
                Integer id = Integer.parseInt(idText);
                if (!titulo.isEmpty() && !descripcion.isEmpty()) {
                    CRUD.actualizarReceta(id,titulo, descripcion);
                    Toast.makeText(getApplicationContext(), "Actualizacion correcta", Toast.LENGTH_SHORT).show();
                } else {
                    // Muestra un Toast para informar al usuario
                    Toast.makeText(getApplicationContext(), "Tanto el título como la descripción son obligatorios.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}