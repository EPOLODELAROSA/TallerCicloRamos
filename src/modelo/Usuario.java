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
public class Usuario {
    
    private String nombre;
    private String correo;
    private String telefono;
    private String contrasena;
    private String direccion;
    private String ciudad;
    private String referencia;
    
    public Usuario(String nombre, String correo, String telefono, String contrasena) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.direccion = "";
        this.ciudad = "";
        this.referencia = "";
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getReferencia() { return referencia; }
    public void setReferencia(String referencia) { this.referencia = referencia; }

    // Método para guardar en el archivo .txt (separado por ;)
    public String toArchivo() {
        return nombre + ";" + correo + ";" + telefono + ";" + contrasena + ";" + 
               direccion + ";" + ciudad + ";" + referencia;
    }
    
}
