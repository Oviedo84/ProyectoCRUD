package com.example.proyectocrud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class EditarProducto extends AppCompatActivity {
    EditText editNombre, editDescripcion, editPventa, editPcompra, editFecha, editCantidad, editActivo;
    private Button buttonEdit;
    private String editProduct = "http://192.168.0.8:8080/editProd";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editarproducto);

        requestQueue = Volley.newRequestQueue(this);

        editNombre = findViewById(R.id.editNombre);
        editDescripcion = findViewById(R.id.editDescripcion);
        editPventa = findViewById(R.id.editPventa);
        editPcompra = findViewById(R.id.editPcompra);
        editFecha = findViewById(R.id.editFecha);
        editCantidad = findViewById(R.id.editCantidad);
        editActivo = findViewById(R.id.editActivo);

        buttonEdit = findViewById(R.id.edit);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

            }
        });
    }
}