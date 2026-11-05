/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import datos.CompraDAO;
import datos.ProductoDAO;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import modelo.Compra;
import modelo.Producto;

/**
 * FXML Controller class
 *
 * @author efren
 */
public class PanelAdministradorController implements Initializable {

    @FXML
    private Label lblTituloPanelAdmin;
    @FXML
    private Label lblSaludoAdmin;
    @FXML
    private Label lblValorProductos;
    @FXML
    private Label lblValorPedidos;
    @FXML
    private Label lblValorStockBajo;
    @FXML
    private Label lblValorProductos2;
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
        //TODO

        cargarResumenReal();
    }

    private void cargarResumenReal() {
        // 1. Contar productos y stock bajo
        ArrayList<Producto> listaProductos = ProductoDAO.listar();
        int totalProductos = listaProductos.size();
        int stockBajo = 0;

        for (Producto p : listaProductos) {
            if (p.getStock() <= 5) {
                stockBajo++;
            }
        }
        lblValorProductos.setText(String.valueOf(totalProductos));
        lblValorStockBajo.setText(String.valueOf(stockBajo));

        // 2. Contar pedidos totales y pendientes (cola)
        ArrayList<Compra> listaCompras = CompraDAO.listarCompras();
        int totalPedidos = listaCompras.size();
        int pedidosPendientes = 0;

        for (Compra c : listaCompras) {
            if (c.getEstado().equals("Pendiente")) {
                pedidosPendientes++;
            }
        }

        lblValorPedidos.setText(String.valueOf(totalPedidos));
        lblValorProductos2.setText(String.valueOf(pedidosPendientes));
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

    @FXML
    private void onCerrarSesionAdmin(ActionEvent event) {
        util.Navegador.cambiar("IniciarSesion", btnCerrarSesionAdmin);
    }
}
