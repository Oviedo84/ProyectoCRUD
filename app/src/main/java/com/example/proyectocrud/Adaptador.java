package com.example.proyectocrud;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class Adaptador extends ArrayAdapter<Productos> {
    Context context;
    List<Productos>arraylistProductos;
    public Adaptador(@NonNull Context context, List<Productos>arraylistProductos){
        super(context, R.layout.row_layout, arraylistProductos);
        this.context = context;
        this.arraylistProductos = arraylistProductos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, null, true);
        TextView nombre = view.findViewById(R.id.nombre);
        TextView precio = view.findViewById(R.id.precio);
        TextView cantidad = view.findViewById(R.id.cantidad);

        nombre.setText(arraylistProductos.get(position).getNombre());
        precio.setText(arraylistProductos.get(position).getP_venta());
        cantidad.setText(arraylistProductos.get(position).getCantidad());
        return view;
    }
}
