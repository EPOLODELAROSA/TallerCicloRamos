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
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
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
import modelo.Compra;
import modelo.DetalleCompra;
import modelo.Usuario;

/**
 * FXML Controller class
 *
 * @author efren
 */
public class GestionarPedidosController implements Initializable {

    @FXML
    private ImageView imgFlechaAtrasGestPed;
    @FXML
    private Label lblTituloGestionarPedidos;
    @FXML
    private TextField txtBuscarPedido;
    @FXML
    private Button btnBuscarPedido;
    @FXML
    private ComboBox<String> cmbFiltroEstadoPed;
    @FXML
    private ComboBox<String> cmbOrdenPed;
    @FXML
    private Label lblTextoTotalPedidos;
    @FXML
    private Label lblNumPendientes;
    @FXML
    private Label lblNumEnProceso;
    @FXML
    private Label lblNumEnviados;
    @FXML
    private Label lblNumEntregados;
    @FXML
    private Label lblNumCancelados;
    @FXML
    private Label lblNumRechazados;
    @FXML
    private Button btnAtenderCola;
    @FXML
    private ScrollPane scrollListaPedidos;
    @FXML
    private VBox vboxContenedorPedidos;

    private ArrayList<Compra> todasLasCompras;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        cmbFiltroEstadoPed.getItems().addAll("Todos", "Pendiente", "En proceso", "Enviado", "Entregado", "Cancelado", "Rechazado");
        cmbFiltroEstadoPed.getSelectionModel().selectFirst();

        cmbOrdenPed.getItems().addAll("Más recientes", "Más antiguos");
        cmbOrdenPed.getSelectionModel().selectFirst();

        // Listener para el filtro
        cmbFiltroEstadoPed.valueProperty().addListener((obs, oldVal, newVal) -> aplicarFiltros());

        cargarPedidos();
    }

    private void cargarPedidos() {
        todasLasCompras = CompraDAO.listarCompras();
        actualizarContadores();
        aplicarFiltros();
    }

    private void actualizarContadores() {
        int pendientes = 0, enProceso = 0, enviados = 0, entregados = 0, cancelados = 0, rechazados = 0;

        for (Compra c : todasLasCompras) {
            switch (c.getEstado()) {
                case "Pendiente":
                    pendientes++;
                    break;
                case "En proceso":
                    enProceso++;
                    break;
                case "Enviado":
                    enviados++;
                    break;
                case "Entregado":
                    entregados++;
                    break;
                case "Cancelado":
                    cancelados++;
                    break;
                case "Rechazado":
                    rechazados++;
                    break;
            }
        }

        lblTextoTotalPedidos.setText(todasLasCompras.size() + " pedidos registrados");
        lblNumPendientes.setText(String.valueOf(pendientes));
        lblNumEnProceso.setText(String.valueOf(enProceso));
        lblNumEnviados.setText(String.valueOf(enviados));
        lblNumEntregados.setText(String.valueOf(entregados));
        lblNumCancelados.setText(String.valueOf(cancelados));
        lblNumRechazados.setText(String.valueOf(rechazados));
    }

    private void aplicarFiltros() {
        String filtro = cmbFiltroEstadoPed.getValue();
        vboxContenedorPedidos.getChildren().clear();

        for (Compra c : todasLasCompras) {
            if (filtro == null || filtro.equals("Todos") || c.getEstado().equals(filtro)) {
                crearTarjetaPedido(c);
            }
        }
    }

    private void crearTarjetaPedido(Compra c) {
        VBox tarjeta = new VBox(5);
        tarjeta.getStyleClass().add("cardPedidoAdmin");
        tarjeta.setPadding(new Insets(10));
        tarjeta.setPrefWidth(330);
        tarjeta.setMaxWidth(330);

        // Fila Superior
        HBox filaSuperior = new HBox();
        Label lblNum = new Label("Pedido: #" + String.format("%05d", c.getId()));
        lblNum.getStyleClass().add("lblPedidoNumAdmin");

        Region spacerTop = new Region();
        HBox.setHgrow(spacerTop, Priority.ALWAYS);

        Label lblEstado = new Label(c.getEstado());
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

        filaSuperior.getChildren().addAll(lblNum, spacerTop, lblEstado);

        // Info
        Label lblCliente = new Label("Cliente: " + c.getCorreoCliente());
        lblCliente.getStyleClass().add("lblPedidoInfoAdmin");

        Label lblFecha = new Label("Fecha: " + c.getFecha());
        lblFecha.getStyleClass().add("lblPedidoInfoAdmin");

        ArrayList<DetalleCompra> detalles = CompraDAO.listarDetalles(c.getId());
        Label lblProds = new Label("Productos: " + detalles.size() + " ítems");
        lblProds.getStyleClass().add("lblPedidoInfoAdmin");

        Label lblTotal = new Label("Total: $ " + String.format("%,.0f", c.getTotal()));
        lblTotal.getStyleClass().add("lblPedidoTotalAdmin");

        // Botones
        HBox filaBotones = new HBox(10);

        Button btnVer = new Button("VER DETALLE");
        btnVer.getStyleClass().add("btnVerDetalleAdmin");
        btnVer.setOnAction(e -> {
            util.Sesion.setIdPedidoDetalle(c.getId());
            util.Navegador.cambiar("DetallePedidoAdmin", btnVer);
        });

        Button btnActualizar = new Button("ACTUALIZAR ESTADO");
        btnActualizar.getStyleClass().add("btnActualizarEstadoAdmin");
        btnActualizar.setOnAction(e -> {
            mostrarDialogoCambiarEstado(c.getId(), lblEstado);
        });

        filaBotones.getChildren().addAll(btnVer, btnActualizar);

        tarjeta.getChildren().addAll(filaSuperior, lblCliente, lblFecha, lblProds, lblTotal, filaBotones);
        vboxContenedorPedidos.getChildren().add(tarjeta);
    }

    private void mostrarDialogoCambiarEstado(int idCompra, Label lblEstadoVisual) {
        String estadoActual = lblEstadoVisual.getText();

        if (estadoActual.equals("Entregado") || estadoActual.equals("Cancelado") || estadoActual.equals("Rechazado")) {
            mostrarAlerta("Acción no permitida", "No se puede cambiar el estado de un pedido " + estadoActual, Alert.AlertType.WARNING);
            return;
        }

        ChoiceDialog<String> dialog = new ChoiceDialog<>("En proceso", "En proceso", "Enviado", "Entregado");
        dialog.setTitle("Actualizar Estado");
        dialog.setHeaderText("Pedido #" + String.format("%05d", idCompra));
        dialog.setContentText("Selecciona el nuevo estado:");

        java.util.Optional<String> resultado = dialog.showAndWait();

        if (resultado.isPresent()) {
            String nuevoEstado = resultado.get();
            CompraDAO.actualizarEstado(idCompra, nuevoEstado);
            mostrarAlerta("Éxito", "Pedido actualizado a: " + nuevoEstado, Alert.AlertType.INFORMATION);
            cargarPedidos(); // Recargar la lista para actualizar contadores
        }
    }

    @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("PanelAdministrador", imgFlechaAtrasGestPed);
    }

    @FXML
    private void onBuscar(ActionEvent event) {
        String busqueda = txtBuscarPedido.getText().toLowerCase();
        vboxContenedorPedidos.getChildren().clear();

        for (Compra c : todasLasCompras) {
            if (String.valueOf(c.getId()).contains(busqueda) || c.getCorreoCliente().toLowerCase().contains(busqueda)) {
                crearTarjetaPedido(c);
            }
        }
    }

    @FXML
    private void onAtenderCola(ActionEvent event) {
        // Buscamos el pedido pendiente más antiguo (el de menor ID)
        Compra pedidoMasAntiguo = null;
        for (Compra c : todasLasCompras) {
            if (c.getEstado().equals("Pendiente")) {
                if (pedidoMasAntiguo == null || c.getId() < pedidoMasAntiguo.getId()) {
                    pedidoMasAntiguo = c;
                }
            }
        }

        if (pedidoMasAntiguo != null) {
            CompraDAO.actualizarEstado(pedidoMasAntiguo.getId(), "En proceso");
            mostrarAlerta("Cola atendida", "Pedido #" + String.format("%05d", pedidoMasAntiguo.getId()) + " pasado a 'En proceso'.", Alert.AlertType.INFORMATION);
            cargarPedidos();
        } else {
            mostrarAlerta("Sin pendientes", "No hay pedidos pendientes en la cola.", Alert.AlertType.INFORMATION);
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
