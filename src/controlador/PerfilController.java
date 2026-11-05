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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author efren
 */
public class PerfilController implements Initializable {

    @FXML
    private ImageView imgFlechaAtrasPer;
    @FXML
    private Label lblTituloPerfil;
    @FXML
    private ImageView imgCarritoPer;
    @FXML
    private ImageView imgAvatarPerfil;
    @FXML
    private Label lblNombrePerfil;
    @FXML
    private Label lblCorreoPerfil;
    @FXML
    private Button btnEditarPerfil;
    @FXML
    private Label lblMiCuenta;
    @FXML
    private Button btnMisCompras;
    @FXML
    private Button btnListaDeseos;
    @FXML
    private Button btnMiCarrito;
    @FXML
    private Label lblInfoPersonal;
    @FXML
    private Button btnMisDatos;
    @FXML
    private Button btnDireccionEntrega;
    @FXML
    private Button btnCambiarContrasena;
    @FXML
    private Button btnCerrarSesion;
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

        if (util.Sesion.getUsuarioLogueado() != null) {
            String nombre = util.Sesion.getUsuarioLogueado().getNombre();
            String correo = util.Sesion.getUsuarioLogueado().getCorreo();

            lblNombrePerfil.setText(nombre);
            lblCorreoPerfil.setText(correo);

            if (nombre.length() > 20) {
                lblNombrePerfil.setStyle("-fx-font-size: 18px; -fx-font-family: 'Poppins Bold';");
            } else if (nombre.length() > 15) {
                lblNombrePerfil.setStyle("-fx-font-size: 22px; -fx-font-family: 'Poppins Bold';");
            }

        } else {
            lblNombrePerfil.setText("Usuario Invitado");
            lblCorreoPerfil.setText("Inicia sesión para ver tu perfil");
        }
    }

    @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("Inicio", imgFlechaAtrasPer);
    }

    @FXML
    private void onCarritoHeader(MouseEvent event) {
        util.Navegador.cambiar("Carrito", imgCarritoPer);
    }

    @FXML
    private void onEditarPerfil(ActionEvent event) {
        util.Navegador.cambiar("EditarPerfil", btnEditarPerfil);
    }

    @FXML
    private void onMisCompras(ActionEvent event) {
        util.Navegador.cambiar("MisCompras", btnMisCompras);
    }

    @FXML
    private void onListaDeseos(ActionEvent event) {
        util.Navegador.cambiar("ListaDeseos", btnListaDeseos);
    }

    @FXML
    private void onMiCarrito(ActionEvent event) {
        util.Navegador.cambiar("Carrito", btnMiCarrito);
    }

    @FXML
    private void onMisDatos(ActionEvent event) {
        util.Navegador.cambiar("EditarPerfil", btnMisDatos);
    }

    @FXML
    private void onDireccionEntrega(ActionEvent event) {
        util.Navegador.cambiar("DireccionEntrega", btnDireccionEntrega);
    }

    @FXML
    private void onCambiarContrasena(ActionEvent event) {
        util.Navegador.cambiar("CambiarContrasena", btnCambiarContrasena);
    }

    @FXML
    private void onCerrarSesion(ActionEvent event) {
        util.CarritoManager.vaciar();
        util.Sesion.cerrarSesion();
        util.Navegador.cambiar("IniciarSesion", btnCerrarSesion);
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

    }

}
