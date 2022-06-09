package com.example.proyectocrud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainProductos extends AppCompatActivity {
    ListView listV;
    private String products = "http://192.168.0.8:8080/listProd";
    RequestQueue requestQueue;
    private FloatingActionButton button;
    Adaptador adaptador;
    public static ArrayList<Productos>producto = new ArrayList<>();
    Productos product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listarproductos);
        requestQueue = Volley.newRequestQueue(this);
        this.init();
        this.getProductos();
    }

    private void init(){
        listV = findViewById(R.id.showProducts);
        button = findViewById(R.id.insert);
        adaptador = new Adaptador(this, producto);
        listV.setAdapter(adaptador);
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
                                product = new Productos(producto_id, nombre, descripcion, p_venta, p_compra, fecha, activo, cantidad);
                                producto.add(product);
                                adaptador.notifyDataSetChanged();
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

    public void eliminarproducto(String id) {
        String deleteProduct = "http://192.168.0.8:8080/deleteProduct/" + id;
        StringRequest stringRequest = new StringRequest(
                Request.Method.DELETE,
                deleteProduct,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainProductos.this, "Datos eliminados correctamente", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(MainProductos.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(stringRequest);
        reloadProductos();
    }

    private void reloadProductos(){
        adaptador.clear();
        listV.destroyDrawingCache();
        overridePendingTransition(0, 0);
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }
}