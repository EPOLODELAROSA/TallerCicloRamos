/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import data.ArchivoTexto;
import java.util.ArrayList;
import modelo.Compra;
import modelo.DetalleCompra;

/**
 *
 * @author efren
 */
public class CompraDAO {
    
    private static final String ARCHIVO_COMPRAS = "compras.txt";
    private static final String ARCHIVO_DETALLES = "detalles.txt";

    public static int guardarCompra(Compra compra, ArrayList<DetalleCompra> detalles) {
        // 1. Generar nuevo ID (simulando autoincremental)
        int nuevoId = listarCompras().size() + 1;
        compra.setId(nuevoId);

        // 2. Guardar cabecera de la compra
        ArchivoTexto.agregarLinea(ARCHIVO_COMPRAS, compra.toArchivo());

        // 3. Guardar detalles (asociados al ID de la compra)
        for (DetalleCompra d : detalles) {
            DetalleCompra detalleFinal = new DetalleCompra(nuevoId, d.getIdProducto(), d.getCantidad(), d.getPrecioUnitario(), d.getSubtotal());
            ArchivoTexto.agregarLinea(ARCHIVO_DETALLES, detalleFinal.toArchivo());
        }
        
        return nuevoId; 
    }

    public static ArrayList<Compra> listarCompras() {
        ArrayList<Compra> lista = new ArrayList<>();
        ArrayList<String> lineas = ArchivoTexto.leer(ARCHIVO_COMPRAS);
        for (String linea : lineas) {
            String[] p = linea.split(";");
            if (p.length >= 9) {
                Compra c = new Compra(p[1], p[2], p[3], p[4], p[5], p[6], p[7], Double.parseDouble(p[8]));
                c.setId(Integer.parseInt(p[0]));
                lista.add(c);
            }
        }
        return lista;
    }

    public static ArrayList<DetalleCompra> listarDetalles(int idCompra) {
        ArrayList<DetalleCompra> lista = new ArrayList<>();
        ArrayList<String> lineas = ArchivoTexto.leer(ARCHIVO_DETALLES);
        for (String linea : lineas) {
            String[] p = linea.split(";");
            if (p.length >= 5 && Integer.parseInt(p[0]) == idCompra) {
                lista.add(new DetalleCompra(Integer.parseInt(p[0]), Integer.parseInt(p[1]), 
                        Integer.parseInt(p[2]), Double.parseDouble(p[3]), Double.parseDouble(p[4])));
            }
        }
        return lista;
    }
}
