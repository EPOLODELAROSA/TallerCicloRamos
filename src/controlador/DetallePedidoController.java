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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import modelo.Compra;
import modelo.DetalleCompra;
import modelo.Producto;
import modelo.Usuario;

/**
 * FXML Controller class
 *
 * @author efren
 */
public class DetallePedidoController implements Initializable {

    @FXML
    private ImageView imgFlechaAtrasDet;
    @FXML
    private Label lblTituloDetallePedido;
    @FXML
    private Label lblPedidoTituloDet;
    @FXML
    private Label lblPedidoValorDet;
    @FXML
    private Label lblEstadoDet;
    @FXML
    private Label lblFechaTituloDet;
    @FXML
    private Label lblFechaValorDet;
    @FXML
    private Label lblMetodoTituloDet;
    @FXML
    private Label lblMetodoValorDet;
    @FXML
    private Label lblProductosTituloDet;
    @FXML
    private ScrollPane scrollProductosDet;
    @FXML
    private AnchorPane anchorProductos;
    @FXML
    private Label lblSubtotalValorDet;
    @FXML
    private Label lblEnvioValorDet;
    @FXML
    private Label lblTotalValorDet;
    @FXML
    private Label lblDatosEntregaDet;
    @FXML
    private Label lblNombreValorDet;
    @FXML
    private Label lblTelefonoValorDet;
    @FXML
    private Label lblDireccionValorDet;
    @FXML
    private Label lblCiudadValorDet;
    @FXML
    private Label lblReferenciaValorDet;
    @FXML
    private Button btnCancelarPedido;
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

    private Compra compraActual;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        int idCompra = util.Sesion.getIdPedidoDetalle();

        // 1. Buscamos la compra
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

        // 2. Llenamos los datos del pedido
        lblPedidoValorDet.setText("#" + String.format("%05d", compraActual.getId()));
        lblFechaValorDet.setText(compraActual.getFecha());
        lblMetodoValorDet.setText(compraActual.getMetodoPago());
        lblEstadoDet.setText(compraActual.getEstado());

        // Estilos de estado
        lblEstadoDet.getStyleClass().clear();
        if (compraActual.getEstado().equals("Pendiente")) {
            lblEstadoDet.getStyleClass().add("estadoPendiente");
        } else if (compraActual.getEstado().equals("En proceso")) {
            lblEstadoDet.getStyleClass().add("estadoProceso");
        } else if (compraActual.getEstado().equals("Enviado")) {
            lblEstadoDet.getStyleClass().add("estadoEnviado");
        } else if (compraActual.getEstado().equals("Entregado")) {
            lblEstadoDet.getStyleClass().add("estadoEntregado");
        } else if (compraActual.getEstado().equals("Cancelado")) {
            lblEstadoDet.getStyleClass().add("estadoCancelado");
        } else {
            lblEstadoDet.getStyleClass().add("estadoRechazado");
        }

        // 3. Llenamos los totales
        lblSubtotalValorDet.setText("$ " + String.format("%,.0f", compraActual.getTotal() - 8000)); // Restamos envío (puedes guardarlo en el objeto Compra si quieres)
        lblEnvioValorDet.setText("$ 8.000");
        lblTotalValorDet.setText("$ " + String.format("%,.0f", compraActual.getTotal()));

        // 4. Cargamos los productos en el AnchorPane dinámicamente
        anchorProductos.getChildren().clear();
        ArrayList<DetalleCompra> detalles = CompraDAO.listarDetalles(compraActual.getId());
        double y = 10.0;

        for (DetalleCompra d : detalles) {
            // Buscamos el nombre del producto
            String nombreProd = "Producto ID: " + d.getIdProducto();
            for (Producto p : ProductoDAO.listar()) {
                if (p.getId() == d.getIdProducto()) {
                    nombreProd = p.getNombre();
                    break;
                }
            }

            Label lblNom = new Label(d.getCantidad() + " x " + nombreProd);
            lblNom.setLayoutX(10.0);
            lblNom.setLayoutY(y);
            lblNom.getStyleClass().add("lblProducto1Det");

            Label lblPrecio = new Label("Precio: $" + String.format("%,.0f", d.getPrecioUnitario())
                    + "  Subtotal: $" + String.format("%,.0f", d.getSubtotal()));
            lblPrecio.setLayoutX(20.0);
            lblPrecio.setLayoutY(y + 20.0);
            lblPrecio.getStyleClass().add("lblPrecioProd1Det");

            anchorProductos.getChildren().addAll(lblNom, lblPrecio);
            y += 50.0;
        }

        // 5. Datos de entrega (buscamos al usuario)
        Usuario u = UsuarioDAO.buscarPorCorreo(compraActual.getCorreoCliente());
        if (u != null) {
            lblNombreValorDet.setText(u.getNombre());
            lblTelefonoValorDet.setText(u.getTelefono());
        }
        lblDireccionValorDet.setText(compraActual.getDireccion());
        lblCiudadValorDet.setText(compraActual.getCiudad());
        lblReferenciaValorDet.setText(compraActual.getReferencia());

        // 6. Mostrar u ocultar botón de cancelar
        if (!compraActual.getEstado().equals("Pendiente")) {
            btnCancelarPedido.setVisible(false);
            btnCancelarPedido.setManaged(false);
        }
    }

    @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("MisCompras", imgFlechaAtrasDet);
    }

    @FXML
    private void onCancelarPedido(ActionEvent event) {
        if (compraActual != null && compraActual.getEstado().equals("Pendiente")) {
            // Aquí iría la lógica para cambiar el estado a "Cancelado" y devolver stock
            mostrarAlerta("Éxito", "Pedido cancelado (Simulación).", Alert.AlertType.INFORMATION);
            // TODO: Actualizar en el archivo y recargar
        }
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

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
