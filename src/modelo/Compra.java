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
public class Compra {
    
    private int id;
    private String correoCliente;
    private String fecha;
    private String estado;
    private String metodoPago;
    private String direccion;
    private String ciudad;
    private String referencia;
    private double total;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    
    
    
    public Compra(String correoCliente, String fecha, String estado, String metodoPago, 
                  String direccion, String ciudad, String referencia, double total) {
        this.correoCliente = correoCliente;
        this.fecha = fecha;
        this.estado = estado;
        this.metodoPago = metodoPago;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.referencia = referencia;
        this.total = total;
    }

    public String toArchivo() {
        return id + ";" + correoCliente + ";" + fecha + ";" + estado + ";" + metodoPago + ";" + 
               direccion + ";" + ciudad + ";" + referencia + ";" + total;
    }
    
}
