/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import datos.CompraDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import modelo.Compra;

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
        // 1. Obtenemos el ID de la compra que guardamos en la Sesión
        int idCompra = util.Sesion.getIdCompraRealizada();

        // 2. Buscamos la compra en el archivo .txt
        for (Compra c : CompraDAO.listarCompras()) {
            if (c.getId() == idCompra) {
                // 3. Llenamos los Labels con los datos reales
                lblNumPedidoValor.setText(String.format("#%05d", c.getId())); // Formato #00001
                lblFechaValor.setText(c.getFecha());
                lblMetodoValor.setText(c.getMetodoPago());
                lblEstadoValor.setText(c.getEstado());
                lblTotalValorCompra.setText("$ " + String.format("%,.0f", c.getTotal()));
                break;
            }
        }
    }

    @FXML
    private void onSeguirComprando(ActionEvent event) {
        util.Sesion.setFiltroCategoria("Todos");
        util.Navegador.cambiar("Catalogo", btnSeguirComprando);
    }

    @FXML
    private void onVerMisCompras(ActionEvent event) {
        util.Navegador.cambiar("MisCompras", btnVerMisCompras);
    }

}
