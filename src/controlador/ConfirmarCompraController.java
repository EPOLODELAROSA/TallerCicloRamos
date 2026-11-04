/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import datos.CompraDAO;
import datos.ProductoDAO;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import modelo.Compra;
import modelo.DetalleCompra;
import modelo.ItemCarrito;
import util.CarritoManager;

/**
 * FXML Controller class
 *
 * @author efren
 */
public class ConfirmarCompraController implements Initializable {

    @FXML
    private ImageView imgFlechaAtrasConf;
    @FXML
    private Label lblTituloConfirmar;
    @FXML
    private TextField txtNombreConf;
    @FXML
    private TextField txtTelefonoConf;
    @FXML
    private TextField txtDireccionConf;
    @FXML
    private TextField txtCiudadConf;
    @FXML
    private TextField txtReferenciaConf;
    @FXML
    private RadioButton rbContraEntrega;
    @FXML
    private RadioButton rbTransferencia;
    @FXML
    private Label lblDetallePedido;
    @FXML
    private Label lblSubtotalValorConf;
    @FXML
    private Label lblEnvioValorConf;
    @FXML
    private Label lblTotalValorConf;
    @FXML
    private Button btnConfirmarCompra;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ToggleGroup grupo = new ToggleGroup();
        rbContraEntrega.setToggleGroup(grupo);
        rbTransferencia.setToggleGroup(grupo);
        rbContraEntrega.setSelected(true); // Opción por defecto

        // 1. Cargar datos del usuario logueado
        if (util.Sesion.getUsuarioLogueado() != null) {
            txtNombreConf.setText(util.Sesion.getUsuarioLogueado().getNombre());
            txtTelefonoConf.setText(util.Sesion.getUsuarioLogueado().getTelefono());
            txtDireccionConf.setText(util.Sesion.getUsuarioLogueado().getDireccion());
            txtCiudadConf.setText(util.Sesion.getUsuarioLogueado().getCiudad());
            txtReferenciaConf.setText(util.Sesion.getUsuarioLogueado().getReferencia());
        }

        // 2. Calcular los totales reales del carrito
        double subtotal = CarritoManager.getSubtotal();
        double envio = CarritoManager.COSTO_ENVIO;
        double total = CarritoManager.getTotal();

        lblSubtotalValorConf.setText("$ " + String.format("%,.0f", subtotal));
        lblEnvioValorConf.setText("$ " + String.format("%,.0f", envio));
        lblTotalValorConf.setText("$ " + String.format("%,.0f", total));

        // 3. Mostrar resumen de productos en el Label
        StringBuilder resumen = new StringBuilder();
        for (ItemCarrito item : CarritoManager.getItems()) {
            resumen.append(item.getCantidad()).append(" x ").append(item.getProducto().getNombre())
                    .append("  ->  $ ").append(String.format("%,.0f", item.getSubtotal())).append("\n");
        }
        lblDetallePedido.setText(resumen.toString());
    }

    @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("Carrito", imgFlechaAtrasConf);
    }

    @FXML
    private void onConfirmarCompra(ActionEvent event) {
        // Validaciones
        if (txtNombreConf.getText().isEmpty() || txtTelefonoConf.getText().isEmpty()
                || txtDireccionConf.getText().isEmpty() || txtCiudadConf.getText().isEmpty()) {
            mostrarAlerta("Campos vacíos", "Por favor, llene todos los datos de entrega.", Alert.AlertType.WARNING);
            return;
        }

        if (CarritoManager.estaVacio()) {
            mostrarAlerta("Carrito vacío", "No hay productos para comprar.", Alert.AlertType.WARNING);
            return;
        }

        // 1. Crear los detalles de la compra
        ArrayList<DetalleCompra> detalles = new ArrayList<>();
        for (ItemCarrito item : CarritoManager.getItems()) {
            DetalleCompra d = new DetalleCompra(0, item.getProducto().getId(),
                    item.getCantidad(), item.getProducto().getPrecio(), item.getSubtotal());
            detalles.add(d);
        }

        // 2. Crear la compra
        String fecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        String metodoPago = rbContraEntrega.isSelected() ? "Pago contra entrega" : "Transferencia bancaria";

        Compra compra = new Compra(
                util.Sesion.getUsuarioLogueado().getCorreo(), // Correo del cliente
                fecha,
                "Pendiente",
                metodoPago,
                txtDireccionConf.getText(),
                txtCiudadConf.getText(),
                txtReferenciaConf.getText(),
                CarritoManager.getTotal()
        );

        // 3. Guardar en archivo (Compra + Detalles)
        int idCompra = CompraDAO.guardarCompra(compra, detalles);

        // 4. Actualizar stock de cada producto
        for (ItemCarrito item : CarritoManager.getItems()) {
            ProductoDAO.actualizarStock(item.getProducto().getId(), item.getCantidad());
        }

        // 5. Vaciar carrito y redirigir a la pantalla de éxito
        CarritoManager.vaciar();

        // TODO: Guardar el ID de compra en la Sesión para mostrarlo en CompraRealizada
        util.Sesion.setIdCompraRealizada(idCompra);

        util.Navegador.cambiar("CompraRealizada", btnConfirmarCompra);
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}
