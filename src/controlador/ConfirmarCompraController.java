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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
    private Label lblDatosEntrega;
    @FXML
    private Label lblNombreConf;
    @FXML
    private TextField txtNombreConf;
    @FXML
    private Label lblTelefonoConf;
    @FXML
    private TextField txtTelefonoConf;
    @FXML
    private Label lblDireccionConf;
    @FXML
    private TextField txtDireccionConf;
    @FXML
    private Label lblCiudadConf;
    @FXML
    private TextField txtCiudadConf;
    @FXML
    private Label lblReferenciaConf;
    @FXML
    private TextField txtReferenciaConf;

    @FXML
    private Label lblMetodoPago;
    @FXML
    private RadioButton rbContraEntrega;
    @FXML
    private RadioButton rbTransferencia;

    @FXML
    private Label lblResumenPedido;
    @FXML
    private Label lblDetallePedido;

    @FXML
    private Label lblSubtotalTituloConf;
    @FXML
    private Label lblSubtotalValorConf;
    @FXML
    private Label lblEnvioTituloConf;
    @FXML
    private Label lblEnvioValorConf;
    @FXML
    private Label lblTotalTituloConf;
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
        
        javafx.scene.control.ToggleGroup grupo = new javafx.scene.control.ToggleGroup();
    rbContraEntrega.setToggleGroup(grupo);
    rbTransferencia.setToggleGroup(grupo);
    }    
    
     @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("Carrito", imgFlechaAtrasConf);
    }

    // ----- EVENTOS: BOTON CONFIRMAR -----

    @FXML
    private void onConfirmarCompra(ActionEvent event) {
        String nombre = txtNombreConf.getText();
        String telefono = txtTelefonoConf.getText();
        String direccion = txtDireccionConf.getText();
        String ciudad = txtCiudadConf.getText();
        String referencia = txtReferenciaConf.getText();

        String metodoPago = rbContraEntrega.isSelected()
                ? "Pago contra entrega"
                : "Transferencia bancaria";

        System.out.println("=== CONFIRMAR COMPRA ===");
        System.out.println("Nombre: " + nombre);
        System.out.println("Telefono: " + telefono);
        System.out.println("Direccion: " + direccion);
        System.out.println("Ciudad: " + ciudad);
        System.out.println("Referencia: " + referencia);
        System.out.println("Metodo de pago: " + metodoPago);

        // Cuando exista persistencia y CompraRealizada.fxml, aqui:
        // 1. Se valida que los campos no esten vacios.
        // 2. Se guarda la compra.
        // 3. Se navega a CompraRealizada.
    }
    
}
