package com.example.proyectocrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class productos extends AppCompatActivity {
    TextView tView;
    private String products = "http://192.168.0.8:8080/listProd";
    RequestQueue requestQueue;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productos);
        requestQueue = Volley.newRequestQueue(this);
        this.init();
        this.getProductos();
    }

    private void init(){
        tView = (TextView) findViewById(R.id.showProducts);
        button = findViewById(R.id.insert);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                InsertarProducto();
            }
        });
    }

    public void InsertarProducto(){
        Intent intent = new Intent(this, InsertarProductos.class);
        startActivity(intent);
    }

    private void getProductos(){
        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                products,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int size = response.length();
                        for (int i = 0; i < size; i++) {
                            try {
                                JSONObject jsonObject = new JSONObject(response.get(i).toString());
                                String producto_id = jsonObject.getString("producto_id");
                                String nombre = jsonObject.getString("nombre");
                                String descripcion = jsonObject.getString("descripcion");
                                String p_venta = jsonObject.getString("p_venta");
                                String p_compra = jsonObject.getString("p_compra");
                                String fecha = jsonObject.getString("fecha");
                                String activo = jsonObject.getString("activo");
                                String cantidad = jsonObject.getString("cantidad");

                                tView.append("Nombre: " + nombre + ", ");
                                tView.append("Descripcion: " + descripcion + "\n");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(arrayRequest);
    }
}