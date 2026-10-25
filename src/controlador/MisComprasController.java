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
public class MisComprasController implements Initializable {

    @FXML
    private ImageView imgFlechaAtrasMis;
    @FXML
    private Label lblTituloMisCompras;

    @FXML
    private Label lblNumPedido1;
    @FXML
    private Label lblEstado1;
    @FXML
    private Label lblFechaPedido1Titulo;
    @FXML
    private Label lblFechaPedido1Valor;
    @FXML
    private Label lblProductosPedido1Titulo;
    @FXML
    private Label lblProductosPedido1Valor;
    @FXML
    private Label lblTotalPedido1Titulo;
    @FXML
    private Label lblTotalPedido1Valor;
    @FXML
    private Button btnCancelarPedido1;
    @FXML
    private Button btnVerDetalle1;

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
        // Nada por ahora.
        // Cuando exista persistencia, aqui se cargaran los pedidos reales
        // del usuario logueado y se generaran dinamicamente las tarjetas.
        // El boton cancelar solo se mostrara si el estado es "Pendiente".
    }

    // ----- EVENTOS: HEADER -----

    @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("Perfil", imgFlechaAtrasMis);
    }

    // ----- EVENTOS: TARJETA -----

    @FXML
    private void onCancelarPedido1(ActionEvent event) {
        System.out.println("Cancelar pedido #00001");
        // Cuando exista persistencia, aqui se cambia el estado del pedido
        // a "Cancelado" y se refresca la vista.
    }

    @FXML
    private void onVerDetalle1(ActionEvent event) {
        util.Navegador.cambiar("DetallePedido", btnVerDetalle1); 
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