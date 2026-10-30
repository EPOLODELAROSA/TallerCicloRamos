/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import datos.UsuarioDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import modelo.Usuario;


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

        if (nombre.isEmpty() || correo.isEmpty() || telefono.isEmpty() || contrasena.isEmpty() || confirmar.isEmpty()) {
            mostrarAlerta("Campos vacíos", "Por favor, llene todos los campos.", Alert.AlertType.WARNING);
            return;
        }

        if (!contrasena.equals(confirmar)) {
            mostrarAlerta("Error", "Las contraseñas no coinciden.", Alert.AlertType.ERROR);
            return;
        }

        Usuario nuevoUsuario = new Usuario(nombre, correo, telefono, contrasena);
        boolean guardadoExitoso = UsuarioDAO.guardar(nuevoUsuario);

        if (guardadoExitoso) {
            mostrarAlerta("Éxito", "Cuenta creada correctamente. Ahora puedes iniciar sesión.", Alert.AlertType.INFORMATION);
            util.Navegador.cambiar("IniciarSesion", btnRegistrar);
        } else {
            mostrarAlerta("Error", "El correo electrónico ya está registrado.", Alert.AlertType.ERROR);
        }
    }
    
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @FXML
    private void onYaTengoCuenta(ActionEvent event) {
        util.Navegador.cambiar("IniciarSesion", btnYaTengoCuenta);
    }
    
}
