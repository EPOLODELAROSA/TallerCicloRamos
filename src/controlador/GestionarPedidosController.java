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
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.control.ChoiceDialog;

/**
 * FXML Controller class
 *
 * @author efren
 */
public class GestionarPedidosController implements Initializable {

    // --- HEADER ---
    @FXML
    private ImageView imgFlechaAtrasGestPed;
    @FXML
    private Label lblTituloGestionarPedidos;

    // --- FILTROS ---
    @FXML
    private TextField txtBuscarPedido;
    @FXML
    private Button btnBuscarPedido;
    @FXML
    private ComboBox<String> cmbFiltroEstadoPed;
    @FXML
    private ComboBox<String> cmbOrdenPed;

    // --- RESÚMENES ---
    @FXML
    private Label lblTextoTotalPedidos;
    @FXML
    private Label lblTextoPendientes;
    @FXML
    private Label lblNumPendientes;
    @FXML
    private Label lblTextoEnProceso;
    @FXML
    private Label lblNumEnProceso;
    @FXML
    private Label lblTextoEnviados;
    @FXML
    private Label lblNumEnviados;
    @FXML
    private Label lblTextoEntregados;
    @FXML
    private Label lblNumEntregados;
    @FXML
    private Label lblTextoCancelados;
    @FXML
    private Label lblNumCancelados;
    @FXML
    private Label lblTextoRechazados;
    @FXML
    private Label lblNumRechazados;

    // --- BOTÓN COLA ---
    @FXML
    private Button btnAtenderCola;

    // --- LISTA ---
    @FXML
    private ScrollPane scrollListaPedidos;
    @FXML
    private VBox vboxContenedorPedidos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Llenar los ComboBox
        cmbFiltroEstadoPed.getItems().addAll("Todos", "Pendiente", "En proceso", "Enviado", "Entregado", "Cancelado", "Rechazado");
        cmbFiltroEstadoPed.getSelectionModel().selectFirst();
        
        cmbOrdenPed.getItems().addAll("Más recientes", "Más antiguos");
        cmbOrdenPed.getSelectionModel().selectFirst();

        // Cargar datos simulados
        cargarDatosSimulados();
    }

    // ----- LÓGICA DE DATOS (SIMULADA POR AHORA) -----
    private void cargarDatosSimulados() {
        vboxContenedorPedidos.getChildren().clear();

        // Actualizamos los resúmenes (números)
        lblTextoTotalPedidos.setText("2 pedidos registrados");
        lblNumPendientes.setText("2");
        lblNumEnProceso.setText("0");
        lblNumEnviados.setText("0");
        lblNumEntregados.setText("0");
        lblNumCancelados.setText("0");
        lblNumRechazados.setText("0");

        // Creamos 2 tarjetas de ejemplo
        crearTarjetaPedido("#00001", "Juan Pérez", "26/08/2026", "1 Productos", "$ 212.000", "Pendiente");
        crearTarjetaPedido("#00002", "María López", "27/08/2026", "3 Productos", "$ 450.000", "Pendiente");
    }

    // ----- MÉTODO AUXILIAR: CREAR TARJETA DE PEDIDO -----
    private void crearTarjetaPedido(String numPedido, String cliente, String fecha, String productos, String total, String estado) {
        // 1. Contenedor principal
        VBox tarjeta = new VBox(5);
        tarjeta.getStyleClass().add("cardPedidoAdmin");
        tarjeta.setPadding(new Insets(10));
        tarjeta.setPrefWidth(330);
        tarjeta.setMaxWidth(330);

        // 2. Fila superior: Número de pedido y Estado
        HBox filaSuperior = new HBox();
        Label lblNum = new Label("Pedido: " + numPedido);
        lblNum.getStyleClass().add("lblPedidoNumAdmin");
        
        Region spacerTop = new Region();
        HBox.setHgrow(spacerTop, Priority.ALWAYS);

        Label lblEstado = new Label(estado);
        // Asignamos la clase de estilo según el estado (usando las que ya tienes en tu CSS)
        if (estado.equals("Pendiente")) {
            lblEstado.getStyleClass().add("estadoPendiente");
        } else if (estado.equals("En proceso")) {
            lblEstado.getStyleClass().add("estadoProceso");
        } else if (estado.equals("Enviado")) {
            lblEstado.getStyleClass().add("estadoEnviado");
        } else if (estado.equals("Entregado")) {
            lblEstado.getStyleClass().add("estadoEntregado");
        } else if (estado.equals("Cancelado")) {
            lblEstado.getStyleClass().add("estadoCancelado");
        } else {
            lblEstado.getStyleClass().add("estadoRechazado");
        }

        filaSuperior.getChildren().addAll(lblNum, spacerTop, lblEstado);

        // 3. Información del cliente y fecha
        Label lblInfo1 = new Label("Cliente: " + cliente);
        lblInfo1.getStyleClass().add("lblPedidoInfoAdmin");
        Label lblInfo2 = new Label("Fecha: " + fecha);
        lblInfo2.getStyleClass().add("lblPedidoInfoAdmin");
        Label lblInfo3 = new Label("Productos: " + productos);
        lblInfo3.getStyleClass().add("lblPedidoInfoAdmin");

        // 4. Total
        Label lblTotal = new Label("Total: " + total);
        lblTotal.getStyleClass().add("lblPedidoTotalAdmin");

        // 5. Botones (Ver Detalle, Actualizar Estado)
        HBox filaBotones = new HBox(10);
        
        Button btnVer = new Button("VER DETALLE");
        btnVer.getStyleClass().add("btnVerDetalleAdmin");
        btnVer.setOnAction(e -> {
            util.Navegador.cambiar("DetallePedidoAdmin", btnVer);
        });

        Button btnActualizar = new Button("ACTUALIZAR ESTADO");
        btnActualizar.getStyleClass().add("btnActualizarEstadoAdmin");
        btnActualizar.setOnAction(e -> {
            // Llamamos a un nuevo método que se encargará del diálogo
            mostrarDialogoCambiarEstado(lblEstado, numPedido);
        });

        filaBotones.getChildren().addAll(btnVer, btnActualizar);

        // 6. Ensamblar
        tarjeta.getChildren().addAll(filaSuperior, lblInfo1, lblInfo2, lblInfo3, lblTotal, filaBotones);
        
        // 7. Agregar al VBox principal
        vboxContenedorPedidos.getChildren().add(tarjeta);
    }

    // ----- EVENTOS: HEADER -----
    @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("PanelAdministrador", imgFlechaAtrasGestPed);
    }

    // ----- EVENTOS: FILTROS Y COLA -----
    @FXML
    private void onBuscar(ActionEvent event) {
        System.out.println("Buscando: " + txtBuscarPedido.getText());
    }

    @FXML
    private void onAtenderCola(ActionEvent event) {
        // Simulación de atender el pedido más antiguo
        mostrarAlerta("Cola de Pedidos", "El pedido pendiente más antiguo (#00001) ha pasado a 'En proceso'. (Simulación)", Alert.AlertType.INFORMATION);
        
        // En la vida real, aquí actualizaríamos el estado en el modelo y volveríamos a cargar la lista.
    }

    // ----- MÉTODOS AUXILIARES -----
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    
        // ----- MÉTODO PARA ACTUALIZAR ESTADO DESDE LA LISTA -----
    private void mostrarDialogoCambiarEstado(Label lblEstadoVisual, String numPedido) {
        String estadoActual = lblEstadoVisual.getText();

        // Validación: No actualizar si ya está entregado/cancelado/rechazado
        if (estadoActual.equals("Entregado") || estadoActual.equals("Cancelado") || estadoActual.equals("Rechazado")) {
            mostrarAlerta("Acción no permitida", "No se puede cambiar el estado de un pedido " + estadoActual, Alert.AlertType.WARNING);
            return;
        }

        ChoiceDialog<String> dialog = new ChoiceDialog<>("En proceso", "En proceso", "Enviado", "Entregado");
        dialog.setTitle("Actualizar Estado");
        dialog.setHeaderText("Pedido " + numPedido);
        dialog.setContentText("Selecciona el nuevo estado:");

        java.util.Optional<String> resultado = dialog.showAndWait();
        
        if (resultado.isPresent()) {
            String nuevoEstado = resultado.get();
            
            // Actualizamos el Label visualmente
            lblEstadoVisual.setText(nuevoEstado);
            lblEstadoVisual.getStyleClass().clear();
            
            // Le aplicamos el estilo correcto
            if (nuevoEstado.equals("En proceso")) {
                lblEstadoVisual.getStyleClass().add("estadoProceso");
            } else if (nuevoEstado.equals("Enviado")) {
                lblEstadoVisual.getStyleClass().add("estadoEnviado");
            } else if (nuevoEstado.equals("Entregado")) {
                lblEstadoVisual.getStyleClass().add("estadoEntregado");
            }

            mostrarAlerta("Éxito", "Pedido " + numPedido + " actualizado a: " + nuevoEstado + " (Simulación)", Alert.AlertType.INFORMATION);
            
            // Aquí llamaríamos a cargarDatosSimulados() o al método de recarga real 
            // para que los contadores de arriba se actualicen.
        }
    }
}