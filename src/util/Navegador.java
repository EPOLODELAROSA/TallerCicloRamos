/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author efren
 */
public class Navegador {
    
    public static void cambiar(String nombreFXML, Node origen) {
        try {
            
            URL rutaFXML = Navegador.class.getResource("/vista/" + nombreFXML + ".fxml");

           
            if (rutaFXML == null) {
                System.err.println("No se encontró el FXML: /vista/" + nombreFXML + ".fxml");
                return;
            }

            Parent root = FXMLLoader.load(rutaFXML);

            Scene nuevaEscena = new Scene(root, 390, 844);

            Stage ventana = (Stage) origen.getScene().getWindow();

            ventana.setScene(nuevaEscena);
            ventana.centerOnScreen();

        } catch (IOException e) {
            System.err.println("Error al cargar el FXML: " + nombreFXML);
            e.printStackTrace();
        }
    }
    
}
