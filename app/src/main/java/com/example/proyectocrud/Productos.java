package com.example.proyectocrud;

public class Productos {
    private String producto_id, nombre, descripcion, p_venta, p_compra, fecha, activo, cantidad;

    public Productos(){

    }

    public Productos(String producto_id, String nombre, String descripcion, String p_venta, String p_compra, String fecha, String activo, String cantidad) {
        this.producto_id = producto_id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.p_venta = p_venta;
        this.p_compra = p_compra;
        this.fecha = fecha;
        this.activo = activo;
        this.cantidad = cantidad;
    }

    public String getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(String producto_id) {
        this.producto_id = producto_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getP_venta() {
        return p_venta;
    }

    public void setP_venta(String p_venta) {
        this.p_venta = p_venta;
    }

    public String getP_compra() {
        return p_compra;
    }

    public void setP_compra(String p_compra) {
        this.p_compra = p_compra;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
}
