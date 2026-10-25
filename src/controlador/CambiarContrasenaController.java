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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author efren
 */
public class CambiarContrasenaController implements Initializable {

    // --- HEADER ---
    @FXML
    private ImageView imgFlechaAtrasPass;
    @FXML
    private Label lblTituloCambiarPass;

    // --- CAMPOS ---
    @FXML
    private PasswordField txtPassActual;
    @FXML
    private PasswordField txtPassNueva;
    @FXML
    private PasswordField txtPassConfirmar;

    // --- BOTONES ---
    @FXML
    private Button btnGuardarPass;
    @FXML
    private Button btnCancelarPass;

    // --- NAV INFERIOR ---
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
        // Lógica futura para cargar datos si es necesario
    }

    // ----- EVENTOS: HEADER -----
    @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("Perfil", imgFlechaAtrasPass);
    }

    // ----- EVENTOS: BOTONES -----
    @FXML
    private void onGuardarPass(ActionEvent event) {
        String actual = txtPassActual.getText();
        String nueva = txtPassNueva.getText();
        String confirmar = txtPassConfirmar.getText();

        // Validaciones
        if (actual.isEmpty() || nueva.isEmpty() || confirmar.isEmpty()) {
            mostrarAlerta("Campos vacíos", "Por favor, llene todos los campos.", Alert.AlertType.WARNING);
            return;
        }

        if (!nueva.equals(confirmar)) {
            mostrarAlerta("Error", "La nueva contraseña y la confirmación no coinciden.", Alert.AlertType.ERROR);
            return;
        }

        // Aquí iría la lógica de persistencia (validar la actual y guardar la nueva)
        System.out.println("--- CONTRASEÑA ACTUALIZADA (Simulación) ---");
        System.out.println("Pass Actual: " + actual);
        System.out.println("Pass Nueva: " + nueva);

        mostrarAlerta("Éxito", "Contraseña actualizada correctamente (Simulación).", Alert.AlertType.INFORMATION);
        
        // Volver al perfil
        util.Navegador.cambiar("Perfil", btnGuardarPass);
    }

    @FXML
    private void onCancelar(ActionEvent event) {
        util.Navegador.cambiar("Perfil", btnCancelarPass);
    }

    // ----- MÉTODO AUXILIAR PARA ALERTAS -----
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    // ----- EVENTOS: NAV INFERIOR -----
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