/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import data.ArchivoTexto;
import java.util.ArrayList;

/**
 *
 * @author efren
 */
public class FavoritoDAO {

    private static final String ARCHIVO = "favoritos.txt";

    // Guardar un favorito: correoUsuario;idProducto
    public static void agregar(String correo, int idProducto) {
        // Verificar si ya existe
        if (esFavorito(correo, idProducto)) {
            return;
        }

        String linea = correo + ";" + idProducto;
        ArchivoTexto.agregarLinea(ARCHIVO, linea);
    }

    // Eliminar un favorito
    public static void eliminar(String correo, int idProducto) {
        ArrayList<String> lineas = ArchivoTexto.leer(ARCHIVO);
        ArrayList<String> nuevas = new ArrayList<>();

        for (String linea : lineas) {
            String[] partes = linea.split(";");
            if (partes.length >= 2) {
                if (!(partes[0].equals(correo) && Integer.parseInt(partes[1]) == idProducto)) {
                    nuevas.add(linea);
                }
            }
        }
        ArchivoTexto.escribir(ARCHIVO, nuevas);
    }

    // Obtener todos los IDs de productos favoritos de un usuario
    public static ArrayList<Integer> listarIds(String correo) {
        ArrayList<Integer> ids = new ArrayList<>();
        ArrayList<String> lineas = ArchivoTexto.leer(ARCHIVO);

        for (String linea : lineas) {
            String[] partes = linea.split(";");
            if (partes.length >= 2 && partes[0].equals(correo)) {
                ids.add(Integer.parseInt(partes[1]));
            }
        }
        return ids;
    }

    public static boolean esFavorito(String correo, int idProducto) {
        return listarIds(correo).contains(idProducto);
    }

}
