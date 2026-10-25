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
public class DetallePedidoController implements Initializable {

    // --- HEADER ---
    @FXML
    private ImageView imgFlechaAtrasDet;
    @FXML
    private Label lblTituloDetallePedido;

    // --- INFO PEDIDO ---
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

    // --- PRODUCTOS ---
    @FXML
    private Label lblProductosTituloDet;
    @FXML
    private Label lblProducto1Det;
    @FXML
    private Label lblPrecioProd1Det;

    // --- TOTALES ---
    @FXML
    private Label lblSubtotalTituloDet;
    @FXML
    private Label lblSubtotalValorDet;
    @FXML
    private Label lblEnvioTituloDet;
    @FXML
    private Label lblEnvioValorDet;
    @FXML
    private Label lblTotalTituloDet;
    @FXML
    private Label lblTotalValorDet;

    // --- DATOS ENTREGA ---
    @FXML
    private Label lblDatosEntregaDet;
    @FXML
    private Label lblNombreTituloDet;
    @FXML
    private Label lblNombreValorDet;
    @FXML
    private Label lblTelefonoTituloDet;
    @FXML
    private Label lblTelefonoValorDet;
    @FXML
    private Label lblDireccionTituloDet;
    @FXML
    private Label lblDireccionValorDet;
    @FXML
    private Label lblCiudadTituloDet;
    @FXML
    private Label lblCiudadValorDet;
    @FXML
    private Label lblReferenciaTituloDet;
    @FXML
    private Label lblReferenciaValorDet;

    // --- BOTONES ---
    @FXML
    private Button btnCancelarPedido;

    // --- NAV INFERIOR ---
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
        // Aquí irá la lógica para cargar los datos reales del pedido
        // cuando tengamos la persistencia y el modelo listos.
    }

    // ----- EVENTOS: HEADER -----
    @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("MisCompras", imgFlechaAtrasDet);
    }

    // ----- EVENTOS: BOTONES -----
    @FXML
    private void onCancelarPedido(ActionEvent event) {
        System.out.println("Cancelar pedido solicitado...");
        // Cuando tengamos la lógica, aquí se validará que esté 'Pendiente'
        // y se devolverá el stock.
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