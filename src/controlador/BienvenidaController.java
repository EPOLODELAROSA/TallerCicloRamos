/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author efren
 */
public class BienvenidaController implements Initializable {
    
    @FXML
    private ImageView imgBienvenida;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        aplicarEsquinasRedondeadas();
    }    
    
    private void aplicarEsquinasRedondeadas() {
        
        double ancho = imgBienvenida.getFitWidth();
        double alto = imgBienvenida.getFitHeight();

        Rectangle clip = new Rectangle(ancho, alto);
        clip.setArcWidth(60);   
        clip.setArcHeight(60);  
        imgBienvenida.setClip(clip);
    }
}
