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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
    private Label lblDatosPersonales;
    @FXML
    private Label lblNombreEditar;
    @FXML
    private TextField txtNombreEditar;
    @FXML
    private Label lblCorreoEditar;
    @FXML
    private TextField txtCorreoEditar;
    @FXML
    private Label lblTelefonoEditar;
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
    }    
    
    // ----- EVENTOS: HEADER -----

    @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("Perfil", imgFlechaAtrasEd);
    }

    // ----- EVENTOS: BOTONES -----

    @FXML
    private void onGuardarCambios(ActionEvent event) {
        String nombre = txtNombreEditar.getText();
        String correo = txtCorreoEditar.getText();
        String telefono = txtTelefonoEditar.getText();

        System.out.println("Guardar cambios:");
        System.out.println("Nombre: " + nombre);
        System.out.println("Correo: " + correo);
        System.out.println("Telefono: " + telefono);

        // Cuando exista persistencia, aqui se guardara el usuario modificado
        // y se volvera al Perfil.
        // util.Navegador.cambiar("Perfil", btnGuardarCambios);
    }

    @FXML
    private void onCancelar(ActionEvent event) {
        util.Navegador.cambiar("Perfil", btnCancelarEditar);
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
