package com.example.proyectocrud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InsertarProductos extends AppCompatActivity {
    EditText insertNombre, insertDescripcion, insertPventa, insertPcompra, insertFecha, insertCantidad, insertActivo;
    private Button buttonInsert;
    private String insertProduct = "http://192.168.0.8:8080/insertProd";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insertarproductos);
        requestQueue = Volley.newRequestQueue(this);

        insertNombre = findViewById(R.id.insertNombre);
        insertDescripcion = findViewById(R.id.insertDescripcion);
        insertPventa = findViewById(R.id.insertPventa);
        insertPcompra = findViewById(R.id.insertPcompra);
        insertFecha = findViewById(R.id.insertFecha);
        insertCantidad = findViewById(R.id.insertCantidad);
        insertActivo = findViewById(R.id.insertActivo);

        buttonInsert = findViewById(R.id.Insertar);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v){
               insertar();
           }
        });
    }

    private void insertar() {
        String nombre = insertNombre.getText().toString().trim();
        String Descripcion = insertDescripcion.getText().toString().trim();
        String Pventa = insertPventa.getText().toString().trim();
        String Pcompra = insertPcompra.getText().toString().trim();
        String Fecha = insertFecha.getText().toString().trim();
        String Cantidad = insertCantidad.getText().toString().trim();
        String Activo = insertActivo.getText().toString().trim();

        ProgressDialog progressDialog = new ProgressDialog(this);
        if (nombre.isEmpty()) {
            insertNombre.setError("Complete el campo");
        } else if (Descripcion.isEmpty()) {
            insertDescripcion.setError("Complete el campo");
        } else if (Pventa.isEmpty()) {
            insertPventa.setError("Complete el campo");
        } else if (Pcompra.isEmpty()) {
            insertPcompra.setError("Complete el campo");
        } else if (Fecha.isEmpty()) {
            insertFecha.setError("Complete el campo");
        } else if (Cantidad.isEmpty()) {
            insertCantidad.setError("Complete el campo");
        } else if (Activo.isEmpty()) {
            insertActivo.setError("Complete el campo");
        } else {
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST,
                    insertProduct,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(InsertarProductos.this, "Datos insertados correctamente", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error){
                                Toast.makeText(InsertarProductos.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                    }
            )
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError{
                      Map<String, String>params=new HashMap<String, String>();
                      params.put("nombre", nombre);
                      params.put("descripcion", Descripcion);
                      params.put("p_venta", Pventa);
                      params.put("p_compra", Pcompra);
                      params.put("fecha", Fecha);
                      params.put("activo", Activo);
                      params.put("cantidad", Cantidad);
                      return params;
                }
            };
            requestQueue.add(stringRequest);
        }
    }
}