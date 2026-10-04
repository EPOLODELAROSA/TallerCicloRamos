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
public class CrearCuentaController implements Initializable {
    
    @FXML
    private ImageView imgFlechaAtras;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCorreoRegistro;

    @FXML
    private TextField txtTelefono;

    @FXML
    private PasswordField txtContrasenaRegistro;

    @FXML
    private PasswordField txtConfirmar;

    @FXML
    private Button btnRegistrar;

    @FXML
    private Button btnYaTengoCuenta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("IniciarSesion", imgFlechaAtras);
    }
    
    @FXML
    private void onRegistrar(ActionEvent event) {
        String nombre = txtNombre.getText();
        String correo = txtCorreoRegistro.getText();
        String telefono = txtTelefono.getText();
        String contrasena = txtContrasenaRegistro.getText();
        String confirmar = txtConfirmar.getText();

        System.out.println("Clic en CREAR CUENTA");
        System.out.println("Nombre: " + nombre);
        System.out.println("Correo: " + correo);
        System.out.println("Telefono: " + telefono);
        System.out.println("Contrasena: " + contrasena);
        System.out.println("Confirmar: " + confirmar);
    }

    @FXML
    private void onYaTengoCuenta(ActionEvent event) {
        util.Navegador.cambiar("IniciarSesion", btnYaTengoCuenta);
    }
    
}
