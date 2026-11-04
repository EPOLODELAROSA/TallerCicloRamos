/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import datos.CompraDAO;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.Compra;
import modelo.DetalleCompra;

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
    private VBox vboxContenedorPedidos;
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

        cargarPedidos();
    }

    private void cargarPedidos() {
        vboxContenedorPedidos.getChildren().clear();

        String correoCliente = util.Sesion.getUsuarioLogueado().getCorreo();
        ArrayList<Compra> todasLasCompras = CompraDAO.listarCompras();

        for (Compra c : todasLasCompras) {
            if (c.getCorreoCliente().equals(correoCliente)) {
                // Creamos la tarjeta para cada pedido del cliente
                crearTarjetaPedido(c);
            }
        }

        if (vboxContenedorPedidos.getChildren().isEmpty()) {
            Label lblVacio = new Label("No tienes compras registradas.");
            lblVacio.setStyle("-fx-font-family: 'Poppins'; -fx-text-fill: #888888; -fx-font-size: 14px;");
            vboxContenedorPedidos.getChildren().add(lblVacio);
        }
    }

    private void crearTarjetaPedido(Compra c) {
        AnchorPane card = new AnchorPane();
        card.setPrefHeight(140.0);
        card.setPrefWidth(330.0);
        card.getStyleClass().add("cardPedidoDin"); // <--- CAMBIO

        Label lblNum = new Label("Pedido: #" + String.format("%05d", c.getId()));
        lblNum.setLayoutX(15.0);
        lblNum.setLayoutY(10.0);
        lblNum.getStyleClass().add("lblNumPedidoDin"); // <--- CAMBIO
        card.getChildren().add(lblNum);

        Label lblEstado = new Label(c.getEstado());
        lblEstado.setLayoutX(240.0);
        lblEstado.setLayoutY(10.0);
        // (Aquí se mantienen las clases .estadoPendiente, .estadoProceso, etc.)
        if (c.getEstado().equals("Pendiente")) {
            lblEstado.getStyleClass().add("estadoPendiente");
        } else if (c.getEstado().equals("En proceso")) {
            lblEstado.getStyleClass().add("estadoProceso");
        } else if (c.getEstado().equals("Enviado")) {
            lblEstado.getStyleClass().add("estadoEnviado");
        } else if (c.getEstado().equals("Entregado")) {
            lblEstado.getStyleClass().add("estadoEntregado");
        } else if (c.getEstado().equals("Cancelado")) {
            lblEstado.getStyleClass().add("estadoCancelado");
        } else {
            lblEstado.getStyleClass().add("estadoRechazado");
        }
        card.getChildren().add(lblEstado);

        Label lblFecha = new Label("Fecha: " + c.getFecha());
        lblFecha.setLayoutX(15.0);
        lblFecha.setLayoutY(40.0);
        lblFecha.getStyleClass().add("lblInfoPedidoDin"); // <--- CAMBIO
        card.getChildren().add(lblFecha);

        ArrayList<DetalleCompra> detalles = CompraDAO.listarDetalles(c.getId());
        Label lblProds = new Label("Productos: " + detalles.size() + " ítems");
        lblProds.setLayoutX(15.0);
        lblProds.setLayoutY(60.0);
        lblProds.getStyleClass().add("lblInfoPedidoDin"); // <--- CAMBIO
        card.getChildren().add(lblProds);

        Label lblTotal = new Label("Total: $ " + String.format("%,.0f", c.getTotal()));
        lblTotal.setLayoutX(15.0);
        lblTotal.setLayoutY(80.0);
        lblTotal.getStyleClass().add("lblTotalPedidoDin"); // <--- CAMBIO
        card.getChildren().add(lblTotal);

        if (c.getEstado().equals("Pendiente")) {
            Button btnCancelar = new Button("CANCELAR PEDIDO");
            btnCancelar.setLayoutX(15.0);
            btnCancelar.setLayoutY(105.0);
            btnCancelar.setPrefSize(120, 25);
            btnCancelar.getStyleClass().add("btnCancelarPedidoDin"); // <--- CAMBIO
            btnCancelar.setOnAction(e -> {
                System.out.println("Cancelar pedido #" + c.getId());
            });
            card.getChildren().add(btnCancelar);
        }

        Button btnDetalle = new Button("VER DETALLE");
        btnDetalle.setLayoutX(200.0);
        btnDetalle.setLayoutY(105.0);
        btnDetalle.setPrefSize(115, 25);
        btnDetalle.getStyleClass().add("btnVerDetalleDin"); // <--- CAMBIO
        btnDetalle.setOnAction(e -> {
            util.Sesion.setIdPedidoDetalle(c.getId());
            util.Navegador.cambiar("DetallePedido", btnDetalle);
        });
        card.getChildren().add(btnDetalle);

        vboxContenedorPedidos.getChildren().add(card);
        VBox.setMargin(card, new Insets(0, 0, 10, 0));
    }

    @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("Perfil", imgFlechaAtrasMis);
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
