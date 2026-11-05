/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import datos.CompraDAO;
import datos.ProductoDAO;
import datos.UsuarioDAO;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import modelo.Compra;
import modelo.DetalleCompra;
import modelo.Producto;
import modelo.Usuario;

/**
 * FXML Controller class
 *
 * @author efren
 */
public class DetallePedidoAdminController implements Initializable {

    @FXML
    private ImageView imgFlechaAtrasDetAdmin;
    @FXML
    private Label lblTituloDetalleAdmin;
    @FXML
    private Label lblPedidoTituloAdmin;
    @FXML
    private Label lblPedidoValorAdmin;
    @FXML
    private Label lblEstadoAdmin;
    @FXML
    private Label lblFechaTituloAdmin;
    @FXML
    private Label lblFechaValorAdmin;
    @FXML
    private Label lblDatosClienteTitulo;
    @FXML
    private Label lblClienteNombreTitulo;
    @FXML
    private Label lblClienteNombreValor;
    @FXML
    private Label lblClienteEmailTitulo;
    @FXML
    private Label lblClienteEmailValor;
    @FXML
    private Label lblClienteTelTitulo;
    @FXML
    private Label lblClienteTelValor;
    @FXML
    private Label lblProductosTituloAdmin;
    @FXML
    private ScrollPane scrollProductosAdmin;
    @FXML
    private VBox vboxListaProductosAdmin;
    @FXML
    private Label lblSubtotalTituloAdmin;
    @FXML
    private Label lblSubtotalValorAdmin;
    @FXML
    private Label lblEnvioTituloAdmin;
    @FXML
    private Label lblEnvioValorAdmin;
    @FXML
    private Label lblTotalTituloAdmin;
    @FXML
    private Label lblTotalValorAdmin;
    @FXML
    private Label lblEntregaTituloAdmin;
    @FXML
    private Label lblDireccionTituloAdmin;
    @FXML
    private Label lblDireccionValorAdmin;
    @FXML
    private Label lblCiudadTituloAdmin;
    @FXML
    private Label lblCiudadValorAdmin;
    @FXML
    private Label lblReferenciaTituloAdmin;
    @FXML
    private Label lblReferenciaValorAdmin;
    @FXML
    private Label lblMetodoTituloAdmin;
    @FXML
    private Label lblMetodoValorAdmin;
    @FXML
    private Button btnActualizarEstado;
    @FXML
    private Button btnRechazarPedido;

    private Compra compraActual;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO

        int idCompra = util.Sesion.getIdPedidoDetalle();

        for (Compra c : CompraDAO.listarCompras()) {
            if (c.getId() == idCompra) {
                compraActual = c;
                break;
            }
        }

        if (compraActual == null) {
            mostrarAlerta("Error", "Pedido no encontrado.", Alert.AlertType.ERROR);
            return;
        }

        cargarDatosReales();
    }

    private void cargarDatosReales() {
        // 1. Datos generales
        lblPedidoValorAdmin.setText("#" + String.format("%05d", compraActual.getId()));
        lblEstadoAdmin.setText(compraActual.getEstado());
        lblFechaValorAdmin.setText(compraActual.getFecha());

        // Actualizar clase CSS del estado
        lblEstadoAdmin.getStyleClass().clear();
        if (compraActual.getEstado().equals("Pendiente")) {
            lblEstadoAdmin.getStyleClass().add("estadoPendiente");
        } else if (compraActual.getEstado().equals("En proceso")) {
            lblEstadoAdmin.getStyleClass().add("estadoProceso");
        } else if (compraActual.getEstado().equals("Enviado")) {
            lblEstadoAdmin.getStyleClass().add("estadoEnviado");
        } else if (compraActual.getEstado().equals("Entregado")) {
            lblEstadoAdmin.getStyleClass().add("estadoEntregado");
        } else if (compraActual.getEstado().equals("Cancelado")) {
            lblEstadoAdmin.getStyleClass().add("estadoCancelado");
        } else {
            lblEstadoAdmin.getStyleClass().add("estadoRechazado");
        }

        // 2. Datos del cliente
        Usuario u = UsuarioDAO.buscarPorCorreo(compraActual.getCorreoCliente());
        if (u != null) {
            lblClienteNombreValor.setText(u.getNombre());
            lblClienteEmailValor.setText(u.getCorreo());
            lblClienteTelValor.setText(u.getTelefono());
        } else {
            lblClienteNombreValor.setText("Cliente no registrado");
            lblClienteEmailValor.setText(compraActual.getCorreoCliente());
            lblClienteTelValor.setText("-");
        }

        // 3. Productos dinámicos
        vboxListaProductosAdmin.getChildren().clear();
        ArrayList<DetalleCompra> detalles = CompraDAO.listarDetalles(compraActual.getId());
        double subtotalReal = 0;

        for (DetalleCompra d : detalles) {
            // Nombre del producto
            String nombreProd = "Producto ID: " + d.getIdProducto();
            for (Producto p : ProductoDAO.listar()) {
                if (p.getId() == d.getIdProducto()) {
                    nombreProd = p.getNombre();
                    break;
                }
            }

            Label lblProd = new Label(d.getCantidad() + " x " + nombreProd);
            lblProd.getStyleClass().add("lblProductoDetalle");

            Label lblPrecio = new Label("Precio: $" + String.format("%,.0f", d.getPrecioUnitario())
                    + "  Subtotal: $" + String.format("%,.0f", d.getSubtotal()));
            lblPrecio.getStyleClass().add("lblPrecioProductoDetalle");

            vboxListaProductosAdmin.getChildren().addAll(lblProd, lblPrecio);
            subtotalReal += d.getSubtotal();
        }

        // 4. Totales
        lblSubtotalValorAdmin.setText("$ " + String.format("%,.0f", subtotalReal));
        lblEnvioValorAdmin.setText("$ " + String.format("%,.0f", 8000.0));
        lblTotalValorAdmin.setText("$ " + String.format("%,.0f", compraActual.getTotal()));

        // 5. Entrega
        lblDireccionValorAdmin.setText(compraActual.getDireccion());
        lblCiudadValorAdmin.setText(compraActual.getCiudad());
        lblReferenciaValorAdmin.setText(compraActual.getReferencia());
        lblMetodoValorAdmin.setText(compraActual.getMetodoPago());
    }

    @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("GestionarPedidos", imgFlechaAtrasDetAdmin);
    }

    @FXML
    private void onActualizarEstado(ActionEvent event) {
        String estadoActual = compraActual.getEstado();

        if (estadoActual.equals("Entregado") || estadoActual.equals("Cancelado") || estadoActual.equals("Rechazado")) {
            mostrarAlerta("Acción no permitida", "No se puede cambiar el estado de un pedido " + estadoActual, Alert.AlertType.WARNING);
            return;
        }

        ChoiceDialog<String> dialog = new ChoiceDialog<>("En proceso", "En proceso", "Enviado", "Entregado");
        dialog.setTitle("Actualizar Estado");
        dialog.setHeaderText("Pedido #" + String.format("%05d", compraActual.getId()));
        dialog.setContentText("Selecciona el nuevo estado:");

        java.util.Optional<String> resultado = dialog.showAndWait();

        if (resultado.isPresent()) {
            String nuevoEstado = resultado.get();
            CompraDAO.actualizarEstado(compraActual.getId(), nuevoEstado);
            mostrarAlerta("Éxito", "Estado actualizado a: " + nuevoEstado, Alert.AlertType.INFORMATION);
            util.Navegador.cambiar("DetallePedidoAdmin", btnActualizarEstado);
        }
    }

    @FXML
    private void onRechazarPedido(ActionEvent event) {
        String estadoActual = compraActual.getEstado();

        if (!estadoActual.equals("Pendiente") && !estadoActual.equals("En proceso")) {
            mostrarAlerta("Acción no permitida", "Solo se pueden rechazar pedidos Pendientes o En Proceso.", Alert.AlertType.WARNING);
            return;
        }

        ChoiceDialog<String> dialog = new ChoiceDialog<>("Producto agotado",
                "Producto agotado", "Error de inventario", "Problema con el pedido", "Otro");
        dialog.setTitle("Rechazar Pedido");
        dialog.setHeaderText("Rechazando pedido #" + String.format("%05d", compraActual.getId()));
        dialog.setContentText("Selecciona el motivo del rechazo:");

        java.util.Optional<String> resultado = dialog.showAndWait();

        if (resultado.isPresent()) {
            String motivo = resultado.get();

            // 1. Cambiamos el estado a Rechazado
            CompraDAO.actualizarEstado(compraActual.getId(), "Rechazado");

            // 2. Devolvemos el stock de cada producto
            for (DetalleCompra d : CompraDAO.listarDetalles(compraActual.getId())) {
                ProductoDAO.devolverStock(d.getIdProducto(), d.getCantidad());
            }

            mostrarAlerta("Pedido Rechazado", "El pedido ha sido rechazado por: " + motivo + ".\nEl stock ha sido devuelto.", Alert.AlertType.INFORMATION);
            util.Navegador.cambiar("GestionarPedidos", btnRechazarPedido);
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
