package com.ejemplo.sqllite;

/**
 * Created by lchang on 25/09/17.
 */

public class Datos {
    private String id;
    private String nombre;
    private String stock;

    public Datos(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Datos(String id, String nombre, String stock) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}
