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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import modelo.Usuario;

/**
 * FXML Controller class
 *
 * @author efren
 */
public class DireccionEntregaController implements Initializable {

    @FXML
    private ImageView imgFlechaAtrasDir;
    @FXML
    private Label lblTituloDireccion;
    @FXML
    private TextField txtDireccionDir;
    @FXML
    private TextField txtCiudadDir;
    @FXML
    private TextField txtReferenciaDir;
    @FXML
    private Button btnGuardarDireccion;
    @FXML
    private Button btnCancelarDir;
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
    @FXML 
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Usuario u = util.Sesion.getUsuarioLogueado();
        if (u != null) {
            txtDireccionDir.setText(u.getDireccion());
            txtCiudadDir.setText(u.getCiudad());
            txtReferenciaDir.setText(u.getReferencia());
        }
    }

    @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("Perfil", imgFlechaAtrasDir);
    }

    @FXML
    private void onGuardarDireccion(ActionEvent event) {
        String direccion = txtDireccionDir.getText();
        String ciudad = txtCiudadDir.getText();
        String referencia = txtReferenciaDir.getText();

        if (direccion.isEmpty() || ciudad.isEmpty()) {
            mostrarAlerta("Campos vacíos", "Por favor, ingresa al menos la dirección y la ciudad.", Alert.AlertType.WARNING);
            return;
        }

        // 1. Actualizamos el objeto en la Sesión
        Usuario u = util.Sesion.getUsuarioLogueado();
        u.setDireccion(direccion);
        u.setCiudad(ciudad);
        u.setReferencia(referencia);

        // 2. Guardamos en el archivo .txt
        UsuarioDAO.actualizar(u);

        mostrarAlerta("Éxito", "Dirección guardada correctamente.", Alert.AlertType.INFORMATION);
        util.Navegador.cambiar("Perfil", rootPane); 
    }

    @FXML
    private void onCancelar(ActionEvent event) {
        util.Navegador.cambiar("Perfil", btnCancelarDir);
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
