/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author efren
 */
public class Producto {
    
    private int id;
    private String nombre;
    private String imagen;
    private double precio;
    private int stock;
    private String categoria;
    private String descripcion;
    
    // Constructor para productos nuevos (sin ID)
    public Producto(String nombre, String imagen, double precio, int stock, String categoria, String descripcion) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    // Método para guardar en el archivo .txt
    public String toArchivo() {
        return id + ";" + nombre + ";" + imagen + ";" + precio + ";" + stock + ";" + categoria + ";" + descripcion;
    }
    
  
    
}
