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
import javafx.scene.input.MouseEvent;

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
    private TextField txtContrasenaVisible;

    @FXML
    private Button btnMostrar;

    @FXML
    private Button btnIniciarSesion;

    @FXML
    private Button btnCrearCuenta;
    
     private boolean contrasenaVisible = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtContrasena.textProperty().addListener((obs, viejo, nuevo) -> {
            if (!contrasenaVisible) {
                txtContrasenaVisible.setText(nuevo);
            }
        });
        txtContrasenaVisible.textProperty().addListener((obs, viejo, nuevo) -> {
            if (contrasenaVisible) {
                txtContrasena.setText(nuevo);
            }
        });
    } 
    
    @FXML
    private void onFlechaAtras(javafx.scene.input.MouseEvent event) {
        util.Navegador.cambiar("Bienvenida", imgFlechaAtras);
    }
    
    @FXML
    private void onMostrar(ActionEvent event) {
        contrasenaVisible = !contrasenaVisible;

        if (contrasenaVisible) {
            // Copiamos el texto al TextField visible y lo mostramos.
            txtContrasenaVisible.setText(txtContrasena.getText());
            txtContrasenaVisible.setVisible(true);
            txtContrasenaVisible.setManaged(true);
            txtContrasena.setVisible(false);
            txtContrasena.setManaged(false);
            btnMostrar.setText("OCULTAR");
        } else {
            // Copiamos el texto al PasswordField y lo mostramos.
            txtContrasena.setText(txtContrasenaVisible.getText());
            txtContrasena.setVisible(true);
            txtContrasena.setManaged(true);
            txtContrasenaVisible.setVisible(false);
            txtContrasenaVisible.setManaged(false);
            btnMostrar.setText("MOSTRAR");
        }

    }
    
    @FXML
    private void onIniciarSesion(ActionEvent event) {
        String correo = txtCorreo.getText();
        String contrasena = txtContrasena.getText();

        util.Navegador.cambiar("Inicio", btnIniciarSesion);

    }
    
    @FXML
    private void onCrearCuenta(ActionEvent event) {
        util.Navegador.cambiar("CrearCuenta", btnCrearCuenta);
    }
    
}
