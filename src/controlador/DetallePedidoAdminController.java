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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.control.ChoiceDialog;

/**
 * FXML Controller class
 *
 * @author efren
 */
public class DetallePedidoAdminController implements Initializable {

    // --- HEADER ---
    @FXML
    private ImageView imgFlechaAtrasDetAdmin;
    @FXML
    private Label lblTituloDetalleAdmin;

    // --- INFO PEDIDO ---
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

    // --- DATOS CLIENTE ---
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

    // --- PRODUCTOS (DINÁMICO) ---
    @FXML
    private Label lblProductosTituloAdmin;
    @FXML
    private ScrollPane scrollProductosAdmin;
    @FXML
    private VBox vboxListaProductosAdmin;

    // --- TOTALES ---
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

    // --- ENTREGA Y PAGO ---
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

    // --- BOTONES ---
    @FXML
    private Button btnActualizarEstado;
    @FXML
    private Button btnRechazarPedido;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Simulamos la carga de datos de un pedido
        cargarDatosSimulados();
    }

    private void cargarDatosSimulados() {
        // 1. Datos básicos del pedido
        lblPedidoValorAdmin.setText("#00001");
        lblEstadoAdmin.setText("Pendiente");
        lblEstadoAdmin.getStyleClass().clear(); // Limpiamos por si tenía otra clase
        lblEstadoAdmin.getStyleClass().add("estadoPendiente");
        lblFechaValorAdmin.setText("28/09/2026");

        // 2. Datos del cliente
        lblClienteNombreValor.setText("Juan Pérez");
        lblClienteEmailValor.setText("juanperez08@gmail.com");
        lblClienteTelValor.setText("300 691 5640");

        // 3. Productos (LIMPIAMOS EL VBOX Y AGREGAMOS VARIOS)
        vboxListaProductosAdmin.getChildren().clear();
        
        // Agregamos 3 productos de ejemplo para probar el ScrollPane
        agregarProductoAlDetalle("1 x Pastillas de Freno Pulsar 200 Ns", "Precio: $23.000 Subtotal: $ 23.000");
        agregarProductoAlDetalle("2 x Kit de Arrastre NS 200", "Precio: $204.800 Subtotal: $ 409.600");
        agregarProductoAlDetalle("1 x Aceite Celerity 20W50", "Precio: $32.000 Subtotal: $ 32.000");

        // 4. Totales
        lblSubtotalValorAdmin.setText("$ 464.600");
        lblEnvioValorAdmin.setText("$ 8.000");
        lblTotalValorAdmin.setText("$ 472.600");

        // 5. Entrega y Pago
        lblDireccionValorAdmin.setText("Manzana 170 lote 12");
        lblCiudadValorAdmin.setText("Montería");
        lblReferenciaValorAdmin.setText("Casa azul con rejas negras");
        lblMetodoValorAdmin.setText("Pago contra entrega");
    }

    // Método auxiliar para inyectar los productos en el VBox
    private void agregarProductoAlDetalle(String textoProducto, String textoPrecio) {
        Label lblProd = new Label(textoProducto);
        lblProd.getStyleClass().add("lblProductoDetalle");
        
        Label lblPrecio = new Label(textoPrecio);
        lblPrecio.getStyleClass().add("lblPrecioProductoDetalle");
        
        // Los agregamos al VBox. Puedes agregar un pequeño espaciado (setSpacing) al VBox en SceneBuilder
        vboxListaProductosAdmin.getChildren().addAll(lblProd, lblPrecio);
        
        // Opcional: Un pequeño separador visual entre productos
        // vboxListaProductosAdmin.getChildren().add(new Separator());
    }

    // ----- EVENTOS: HEADER -----
    @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("GestionarPedidos", imgFlechaAtrasDetAdmin);
    }

    // ----- EVENTOS: BOTONES -----
    @FXML
    private void onActualizarEstado(ActionEvent event) {
        String estadoActual = lblEstadoAdmin.getText();

        // Regla de negocio: No se puede actualizar un pedido entregado, cancelado o rechazado.
        if (estadoActual.equals("Entregado") || estadoActual.equals("Cancelado") || estadoActual.equals("Rechazado")) {
            mostrarAlerta("Acción no permitida", "No se puede cambiar el estado de un pedido " + estadoActual, Alert.AlertType.WARNING);
            return;
        }

        // Creamos un diálogo para elegir el nuevo estado
        ChoiceDialog<String> dialog = new ChoiceDialog<>("En proceso", "En proceso", "Enviado", "Entregado");
        dialog.setTitle("Actualizar Estado");
        dialog.setHeaderText("Pedido " + lblPedidoValorAdmin.getText());
        dialog.setContentText("Selecciona el nuevo estado:");

        // Mostramos el diálogo y esperamos la respuesta
        java.util.Optional<String> resultado = dialog.showAndWait();
        
        if (resultado.isPresent()) {
            String nuevoEstado = resultado.get();
            
            // Aquí, cuando tengamos persistencia, guardaremos el cambio en el .txt
            System.out.println("--- ACTUALIZANDO ESTADO ---");
            System.out.println("Pedido: " + lblPedidoValorAdmin.getText());
            System.out.println("Nuevo estado: " + nuevoEstado);

            // Actualizamos visualmente el Label del estado
            lblEstadoAdmin.setText(nuevoEstado);
            lblEstadoAdmin.getStyleClass().clear();
            
            // Le ponemos el color correspondiente (usando tus clases de CSS)
            if (nuevoEstado.equals("En proceso")) {
                lblEstadoAdmin.getStyleClass().add("estadoProceso");
            } else if (nuevoEstado.equals("Enviado")) {
                lblEstadoAdmin.getStyleClass().add("estadoEnviado");
            } else if (nuevoEstado.equals("Entregado")) {
                lblEstadoAdmin.getStyleClass().add("estadoEntregado");
            }

            mostrarAlerta("Éxito", "Estado actualizado a: " + nuevoEstado + " (Simulación)", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void onRechazarPedido(ActionEvent event) {
        String estadoActual = lblEstadoAdmin.getText();

        // Regla de negocio: Solo se puede rechazar si está Pendiente o En proceso
        if (!estadoActual.equals("Pendiente") && !estadoActual.equals("En proceso")) {
            mostrarAlerta("Acción no permitida", "Solo se pueden rechazar pedidos Pendientes o En Proceso.", Alert.AlertType.WARNING);
            return;
        }

        // Diálogo para elegir el motivo del rechazo
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Producto agotado", 
                "Producto agotado", "Error de inventario", "Problema con el pedido", "Otro");
        dialog.setTitle("Rechazar Pedido");
        dialog.setHeaderText("Rechazando pedido " + lblPedidoValorAdmin.getText());
        dialog.setContentText("Selecciona el motivo del rechazo:");

        java.util.Optional<String> resultado = dialog.showAndWait();
        
        if (resultado.isPresent()) {
            String motivo = resultado.get();
            
            // Simulamos el guardado del motivo y el cambio de estado
            System.out.println("--- RECHAZANDO PEDIDO ---");
            System.out.println("Pedido: " + lblPedidoValorAdmin.getText());
            System.out.println("Motivo: " + motivo);

            // Actualizamos visualmente
            lblEstadoAdmin.setText("Rechazado");
            lblEstadoAdmin.getStyleClass().clear();
            lblEstadoAdmin.getStyleClass().add("estadoRechazado");

            mostrarAlerta("Pedido Rechazado", "El pedido ha sido rechazado por: " + motivo + " (Simulación)", Alert.AlertType.INFORMATION);
        }
    }

    // ----- MÉTODOS AUXILIARES -----
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}