/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import data.ArchivoTexto;
import java.util.ArrayList;
import modelo.Producto;

/**
 *
 * @author efren
 */
public class ProductoDAO {
 
    private static final String ARCHIVO = "productos.txt";

    // Guardar un producto nuevo
    public static void guardar(Producto producto) {
        // Generar un ID automático basado en la cantidad de productos
        int nuevoId = listar().size() + 1;
        producto.setId(nuevoId);
        
        ArchivoTexto.agregarLinea(ARCHIVO, producto.toArchivo());
    }

    // Listar todos los productos
    public static ArrayList<Producto> listar() {
        ArrayList<Producto> lista = new ArrayList<>();
        ArrayList<String> lineas = ArchivoTexto.leer(ARCHIVO);

        for (String linea : lineas) {
            String[] partes = linea.split(";");
            if (partes.length >= 7) {
                Producto p = new Producto(partes[1], partes[2], 
                        Double.parseDouble(partes[3]), Integer.parseInt(partes[4]), 
                        partes[5], partes[6]);
                p.setId(Integer.parseInt(partes[0]));
                lista.add(p);
            }
        }
        return lista;
    }

    // Eliminar un producto por su ID
    public static void eliminar(int id) {
        ArrayList<Producto> lista = listar();
        ArrayList<String> nuevasLineas = new ArrayList<>();
        
        for (Producto p : lista) {
            if (p.getId() != id) {
                nuevasLineas.add(p.toArchivo());
            }
        }
        ArchivoTexto.escribir(ARCHIVO, nuevasLineas);
    }
    
    // Guardar un producto manteniendo su ID (para edición)
    public static void guardarConId(Producto producto) {
        ArchivoTexto.agregarLinea(ARCHIVO, producto.toArchivo());
    }
    
    
}
