/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author efren
 */
public class ArchivoTexto {
    
    private static final String RUTA_BASE = "data/";

    public static ArrayList<String> leer(String nombreArchivo) {
        ArrayList<String> lineas = new ArrayList<>();
        File archivo = new File(RUTA_BASE + nombreArchivo);

        if (!archivo.exists()) {
            return lineas;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    lineas.add(linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo " + nombreArchivo + ": " + e.getMessage());
        }
        return lineas;
    }

    public static void escribir(String nombreArchivo, ArrayList<String> lineas) {
        // Aseguramos que la carpeta data exista
        File carpeta = new File(RUTA_BASE);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_BASE + nombreArchivo))) {
            for (String linea : lineas) {
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo " + nombreArchivo + ": " + e.getMessage());
        }
    }

    public static void agregarLinea(String nombreArchivo, String linea) {
        ArrayList<String> lineas = leer(nombreArchivo);
        lineas.add(linea);
        escribir(nombreArchivo, lineas);
    }
    
}
