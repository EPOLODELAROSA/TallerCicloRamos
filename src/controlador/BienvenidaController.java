/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author efren
 */
public class BienvenidaController implements Initializable {
    
    @FXML
    private ImageView imgBienvenida;
    
    @FXML
    private Button btnComenzar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
  
    @FXML
    private void onComenzar(ActionEvent event) {
        util.Navegador.cambiar("IniciarSesion", btnComenzar);
        
    }
}
