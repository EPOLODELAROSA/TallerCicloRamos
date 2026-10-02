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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author efren
 */
public class IniciarSesionController implements Initializable {
    
    @FXML
    private ImageView imgFlechaAtras;

    @FXML
    private TextField txtCorreo;

    @FXML
    private PasswordField txtContrasena;

    @FXML
    private Button btnMostrar;

    @FXML
    private Button btnIniciarSesion;

    @FXML
    private Button btnCrearCuenta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    private void onFlechaAtras(javafx.scene.input.MouseEvent event) {
        util.Navegador.cambiar("Bienvenida", imgFlechaAtras);
    }
    
    @FXML
    private void onMostrar(ActionEvent event) {
        System.out.println("Clic en MOSTRAR");

    }
    
    @FXML
    private void onIniciarSesion(ActionEvent event) {
        String correo = txtCorreo.getText();
        String contrasena = txtContrasena.getText();

        System.out.println("Clic en INICIAR SESIÓN");
        System.out.println("Correo: " + correo);
        System.out.println("Contraseña: " + contrasena);

    }
    
    @FXML
    private void onCrearCuenta(ActionEvent event) {
        System.out.println("Clic en CREAR CUENTA");
    }
    
}
