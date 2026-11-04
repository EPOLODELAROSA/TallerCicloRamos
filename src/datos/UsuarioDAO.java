/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import data.ArchivoTexto;
import java.util.ArrayList;
import modelo.Usuario;

/**
 *
 * @author efren
 */
public class UsuarioDAO {

    private static final String ARCHIVO = "usuarios.txt";

    // Guardar un usuario nuevo
    public static boolean guardar(Usuario usuario) {
        // Verificar si el correo ya existe
        if (buscarPorCorreo(usuario.getCorreo()) != null) {
            return false; // El usuario ya existe
        }
        ArchivoTexto.agregarLinea(ARCHIVO, usuario.toArchivo());
        return true;
    }

    // Listar todos los usuarios
    public static ArrayList<Usuario> listar() {
        ArrayList<Usuario> lista = new ArrayList<>();
        ArrayList<String> lineas = ArchivoTexto.leer(ARCHIVO);

        for (String linea : lineas) {
            String[] partes = linea.split(";");
            if (partes.length >= 4) {
                Usuario u = new Usuario(partes[0], partes[1], partes[2], partes[3]);
                if (partes.length >= 7) {
                    u.setDireccion(partes[4]);
                    u.setCiudad(partes[5]);
                    u.setReferencia(partes[6]);
                }
                lista.add(u);
            }
        }
        return lista;
    }

    public static Usuario buscarPorCorreo(String correo) {
        for (Usuario u : listar()) {
            if (u.getCorreo().equalsIgnoreCase(correo)) {
                return u;
            }
        }
        return null;
    }

    public static void actualizar(Usuario usuario) {
        ArrayList<Usuario> lista = listar();
        ArrayList<String> nuevasLineas = new ArrayList<>();

        for (Usuario u : lista) {
            if (u.getCorreo().equals(usuario.getCorreo())) {
                nuevasLineas.add(usuario.toArchivo());
            } else {
                nuevasLineas.add(u.toArchivo());
            }
        }
        ArchivoTexto.escribir(ARCHIVO, nuevasLineas);
    }

    // Actualizar solo la contraseña de un usuario
    public static void actualizarContrasena(String correo, String nuevaContrasena) {
        ArrayList<Usuario> lista = listar();
        ArrayList<String> nuevasLineas = new ArrayList<>();

        for (Usuario u : lista) {
            if (u.getCorreo().equals(correo)) {
                u.setContrasena(nuevaContrasena);
            }
            nuevasLineas.add(u.toArchivo());
        }
        ArchivoTexto.escribir(ARCHIVO, nuevasLineas);
    }

}
