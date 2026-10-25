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
    }    
    
    @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("Inicio", imgFlechaAtrasPer);
    }

    @FXML
    private void onCarritoHeader(MouseEvent event) {
        util.Navegador.cambiar("Carrito", imgCarritoPer);
    }

    // ----- EVENTOS: EDITAR PERFIL -----

    @FXML
    private void onEditarPerfil(ActionEvent event) {
        util.Navegador.cambiar("EditarPerfil", btnEditarPerfil);
        // Cuando exista EditarPerfil.fxml:
        // util.Navegador.cambiar("EditarPerfil", btnEditarPerfil);
    }

    // ----- EVENTOS: MI CUENTA -----

    @FXML
    private void onMisCompras(ActionEvent event) {
        util.Navegador.cambiar("MisCompras", btnMisCompras);
        // Cuando exista MisCompras.fxml:
        // util.Navegador.cambiar("MisCompras", btnMisCompras);
    }

    @FXML
    private void onListaDeseos(ActionEvent event) {
        util.Navegador.cambiar("ListaDeseos", btnListaDeseos);
    }

    @FXML
    private void onMiCarrito(ActionEvent event) {
        util.Navegador.cambiar("Carrito", btnMiCarrito);
    }

    // ----- EVENTOS: INFO PERSONAL -----

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

    // ----- EVENTOS: CERRAR SESION -----

   
    @FXML
    private void onCerrarSesion(ActionEvent event) {
        // En el futuro, aquí limpiaremos la sesión del usuario (Sesion.cerrar()).
        // Por ahora, volvemos al inicio de sesión.
        util.Navegador.cambiar("IniciarSesion", btnCerrarSesion);
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
        System.out.println("Ya estamos en Perfil");
    }
    
}
