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

/**
 * FXML Controller class
 *
 * @author efren
 */
public class PanelAdministradorController implements Initializable {

    // --- TEXTOS Y SALUDOS ---
    @FXML
    private Label lblTituloPanelAdmin;
    @FXML
    private Label lblSaludoAdmin;

    // --- RESUMEN ---
    @FXML
    private Label lblValorProductos; // Recuadro 1
    @FXML
    private Label lblValorPedidos;   // Recuadro 2
    @FXML
    private Label lblValorStockBajo; // Recuadro 3
    @FXML
    private Label lblValorProductos2; // El número de la cola (el que acabas de crear)

    // --- BOTONES ---
    @FXML
    private Button btnGestionarProducto;
    @FXML
    private Button btnRegistrarProducto;
    @FXML
    private Button btnGestionarPedidos;
    @FXML
    private Button btnGestionarUsuarios;
    @FXML
    private Button btnCerrarSesionAdmin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Aquí, en el futuro, llamaremos a un método para cargar los datos reales.
        // Por ahora, dejamos los números que pusiste en el FXML o los ponemos en 0.
        lblValorProductos.setText("0");
        lblValorPedidos.setText("0");
        lblValorStockBajo.setText("0");
        lblValorProductos2.setText("0"); 
    }

 

    @FXML
    private void onGestionarProducto(ActionEvent event) {
        util.Navegador.cambiar("GestionarProductos", btnGestionarProducto);
    }

    @FXML
    private void onRegistrarProducto(ActionEvent event) {
        util.Navegador.cambiar("RegistrarProducto", btnRegistrarProducto);
    }

    @FXML
    private void onGestionarPedidos(ActionEvent event) {
        util.Navegador.cambiar("GestionarPedidos", btnGestionarPedidos);
    }

    @FXML
    private void onGestionarUsuarios(ActionEvent event) {
        util.Navegador.cambiar("GestionarUsuarios", btnGestionarUsuarios);
    }

    // ----- EVENTOS: CERRAR SESIÓN -----

    @FXML
    private void onCerrarSesionAdmin(ActionEvent event) {
        // En el futuro: Sesion.cerrar();
        util.Navegador.cambiar("IniciarSesion", btnCerrarSesionAdmin);
    }
}