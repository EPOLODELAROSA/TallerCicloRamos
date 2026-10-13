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

/**
 * FXML Controller class
 *
 * @author efren
 */
public class CompraRealizadaController implements Initializable {
    
     @FXML
    private ImageView imgCheck;
    @FXML
    private Label lblTituloCompra;
    @FXML
    private Label lblSubtituloCompra;

    @FXML
    private Label lblNumPedidoTitulo;
    @FXML
    private Label lblNumPedidoValor;
    @FXML
    private Label lblFechaTitulo;
    @FXML
    private Label lblFechaValor;
    @FXML
    private Label lblMetodoTitulo;
    @FXML
    private Label lblMetodoValor;
    @FXML
    private Label lblEstadoTitulo;
    @FXML
    private Label lblEstadoValor;
    @FXML
    private Label lblTotalTituloCompra;
    @FXML
    private Label lblTotalValorCompra;

    @FXML
    private Button btnSeguirComprando;
    @FXML
    private Button btnVerMisCompras;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void onSeguirComprando(ActionEvent event) {
        util.Navegador.cambiar("Catalogo", btnSeguirComprando);
    }

    @FXML
    private void onVerMisCompras(ActionEvent event) {
        System.out.println("Ver mis compras");
        // Cuando exista MisCompras.fxml, aqui navegamos.
        // util.Navegador.cambiar("MisCompras", btnVerMisCompras);
    }
    
}
