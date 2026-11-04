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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import modelo.Usuario;

/**
 * FXML Controller class
 *
 * @author efren
 */
public class CambiarContrasenaController implements Initializable {

    @FXML
    private ImageView imgFlechaAtrasPass;
    @FXML
    private Label lblTituloCambiarPass;
    @FXML
    private PasswordField txtPassActual;
    @FXML
    private PasswordField txtPassNueva;
    @FXML
    private PasswordField txtPassConfirmar;
    @FXML
    private Button btnGuardarPass;
    @FXML
    private Button btnCancelarPass;
    @FXML
    private ImageView imgNavInicio;
    @FXML
    private ImageView imgNavCategorias;
    @FXML
    private ImageView imgNavDeseos;
    @FXML
    private ImageView imgNavCarrito;
    @FXML
    private ImageView imgNavPerfil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("Perfil", imgFlechaAtrasPass);
    }

    @FXML
    private void onGuardarPass(ActionEvent event) {
        String actual = txtPassActual.getText();
        String nueva = txtPassNueva.getText();
        String confirmar = txtPassConfirmar.getText();

        // 1. Validaciones básicas
        if (actual.isEmpty() || nueva.isEmpty() || confirmar.isEmpty()) {
            mostrarAlerta("Campos vacíos", "Por favor, llene todos los campos.", Alert.AlertType.WARNING);
            return;
        }

        // 2. Validar que la nueva y la confirmación coincidan
        if (!nueva.equals(confirmar)) {
            mostrarAlerta("Error", "La nueva contraseña y la confirmación no coinciden.", Alert.AlertType.ERROR);
            return;
        }

        // 3. Validar que la contraseña actual sea correcta
        Usuario u = util.Sesion.getUsuarioLogueado();
        if (u == null) {
            mostrarAlerta("Error", "No hay sesión activa.", Alert.AlertType.ERROR);
            return;
        }

        if (!u.getContrasena().equals(actual)) {
            mostrarAlerta("Error", "La contraseña actual es incorrecta.", Alert.AlertType.ERROR);
            return;
        }

        // 4. Guardar la nueva contraseña en el archivo
        UsuarioDAO.actualizarContrasena(u.getCorreo(), nueva);

        // 5. Actualizar el objeto en la sesión para que el cambio se refleje
        u.setContrasena(nueva);

        mostrarAlerta("Éxito", "Contraseña actualizada correctamente.", Alert.AlertType.INFORMATION);
        util.Navegador.cambiar("Perfil", btnGuardarPass);
    }

    @FXML
    private void onCancelar(ActionEvent event) {
        util.Navegador.cambiar("Perfil", btnCancelarPass);
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @FXML
    private void onNavInicio(MouseEvent event) {
        util.Navegador.cambiar("Inicio", imgNavInicio);
    }

    @FXML
    private void onNavCategorias(MouseEvent event) {
        util.Navegador.cambiar("Categorias", imgNavCategorias);
    }

    @FXML
    private void onNavDeseos(MouseEvent event) {
        util.Navegador.cambiar("ListaDeseos", imgNavDeseos);
    }

    @FXML
    private void onNavCarrito(MouseEvent event) {
        util.Navegador.cambiar("Carrito", imgNavCarrito);
    }

    @FXML
    private void onNavPerfil(MouseEvent event) {
        util.Navegador.cambiar("Perfil", imgNavPerfil);
    }
}
