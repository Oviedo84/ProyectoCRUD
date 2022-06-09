package com.example.proyectocrud;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.List;

public class Adaptador extends ArrayAdapter<Productos> {
    Context context;
    List<Productos>arraylistProductos;
    public String id;
    private Context mContext;
    public Adaptador(@NonNull Context context, List<Productos>arraylistProductos){
        super(context, R.layout.row_layout, arraylistProductos);
        this.context = context;
        this.arraylistProductos = arraylistProductos;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, null, true);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.nombre = (TextView) convertView.findViewById(R.id.nombre);
        viewHolder.precio = (TextView) convertView.findViewById(R.id.precio);
        viewHolder.cantidad = (TextView) convertView.findViewById(R.id.cantidad);
        viewHolder.editProduct = (ImageButton) convertView.findViewById(R.id.editProduct);
        viewHolder.deleteProduct = (ImageButton)  convertView.findViewById(R.id.deleteProduct);
        viewHolder.infoProduct = (ImageButton) convertView.findViewById(R.id.infoProduct);

        viewHolder.infoProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), arraylistProductos.get(position).getDescripcion() + " " + arraylistProductos.get(position).getProducto_id(), Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.deleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = arraylistProductos.get(position).getProducto_id();
                if(mContext instanceof MainProductos){
                    ((MainProductos)mContext).eliminarproducto(id);
                }
            }
        });

        viewHolder.nombre.setText(arraylistProductos.get(position).getNombre());
        viewHolder.precio.setText(arraylistProductos.get(position).getP_venta());
        viewHolder.cantidad.setText(arraylistProductos.get(position).getCantidad());
        return convertView;
    }

    public class ViewHolder{
        TextView nombre;
        TextView precio;
        TextView cantidad;
        ImageButton editProduct;
        ImageButton deleteProduct;
        ImageButton infoProduct;
    }
}
