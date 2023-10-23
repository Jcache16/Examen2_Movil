package com.example.examen2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnBorrar= findViewById(R.id.btnBorrar);
        Button btnInsertar = findViewById(R.id.btnInsertar);
        Button btnEditar = findViewById(R.id.btnEditar);
        CRUDRecetas CRUD = new CRUDRecetas(this);
        ArrayList<String> listaRecetitas = new ArrayList<String>();

        ListView listaRecetas=findViewById(R.id.listarecetas);
        Cursor informacion=CRUD.mostrarRecetas();

        while (informacion.moveToNext()){
            String titulo=informacion.getString(1);
            listaRecetitas.add(titulo);
        }


        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listaRecetitas);
        listaRecetas.setAdapter(adaptador);

        listaRecetas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                for (int i = 0; i < listaRecetitas.size(); i++) {
                    Toast.makeText(MainActivity.this, (String) listaRecetitas.get(position), Toast.LENGTH_SHORT).show();
                    Intent intent12 = new Intent(MainActivity.this, receta.class);
                    String selectedItem0 = (String) listaRecetitas.get(position);
                    intent12.putExtra("titulo", selectedItem0);
                    startActivity(intent12);
                    finish();
                }
            }
        });
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento0 = new Intent(MainActivity.this,editar_receta.class);
                startActivity(intento0);
            }
        });
        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento1 = new Intent(MainActivity.this,insertar_receta.class);
                startActivity(intento1);
            }
        });
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crea un cuadro de diálogo de entrada
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Ingrese el ID a borrar");

                // Agrega un EditText para que el usuario ingrese el ID
                final EditText input = new EditText(MainActivity.this);
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input);

                // Agrega un botón de "Borrar" al cuadro de diálogo
                builder.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Obtiene el ID ingresado por el usuario
                        String idString = input.getText().toString();
                        if (!idString.isEmpty()) {
                            int id = Integer.parseInt(idString);
                            CRUD.eliminarReceta(id);
                            Toast.makeText(MainActivity.this, "Se elimino correctamente", Toast.LENGTH_SHORT).show();
                        } else {
                            // Maneja el caso en el que no se ingresó un ID válido
                            Toast.makeText(MainActivity.this, "ID no válido", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                // Agrega un botón de "Cancelar" al cuadro de diálogo
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                // Muestra el cuadro de diálogo
                builder.show();
            }
        });
    }
}