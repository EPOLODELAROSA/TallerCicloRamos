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
import modelo.Usuario;

/**
 * FXML Controller class
 *
 * @author efren
 */
public class EditarPerfilController implements Initializable {

    @FXML
    private ImageView imgFlechaAtrasEd;
    @FXML
    private Label lblTituloEditarPerfil;
    @FXML
    private TextField txtNombreEditar;
    @FXML
    private TextField txtCorreoEditar;
    @FXML
    private TextField txtTelefonoEditar;
    @FXML
    private Button btnGuardarCambios;
    @FXML
    private Button btnCancelarEditar;
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

        Usuario u = util.Sesion.getUsuarioLogueado();
        if (u != null) {
            txtNombreEditar.setText(u.getNombre());
            txtCorreoEditar.setText(u.getCorreo());
            txtTelefonoEditar.setText(u.getTelefono());
        }

    }

    @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("Perfil", imgFlechaAtrasEd);
    }

    @FXML
    private void onGuardarCambios(ActionEvent event) {
        String nombre = txtNombreEditar.getText();
        String correo = txtCorreoEditar.getText();
        String telefono = txtTelefonoEditar.getText();

        if (nombre.isEmpty() || correo.isEmpty() || telefono.isEmpty()) {
            mostrarAlerta("Campos vacíos", "Por favor, llene todos los campos.", Alert.AlertType.WARNING);
            return;
        }

        // 1. Actualizamos el objeto de la Sesión
        Usuario u = util.Sesion.getUsuarioLogueado();
        u.setNombre(nombre);
        u.setCorreo(correo);
        u.setTelefono(telefono);

        // 2. Guardamos en el archivo .txt
        UsuarioDAO.actualizar(u);

        mostrarAlerta("Éxito", "Datos actualizados correctamente.", Alert.AlertType.INFORMATION);

        // 3. Volvemos al perfil para ver los cambios reflejados
        util.Navegador.cambiar("Perfil", btnGuardarCambios);
    }

    @FXML
    private void onCancelar(ActionEvent event) {
        util.Navegador.cambiar("Perfil", btnCancelarEditar);
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
