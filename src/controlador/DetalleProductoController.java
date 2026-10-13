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
public class DetalleProductoController implements Initializable {
    
    @FXML
    private ImageView imgFlechaAtrasDet;
    @FXML
    private Label lblTituloDetalle;
    @FXML
    private ImageView imgCarritoDet;

    @FXML
    private ImageView imgProductoDet;
    @FXML
    private Label lblCategoriaDet;
    @FXML
    private Label lblNombreDet;
    @FXML
    private Label lblPrecioDet;
    @FXML
    private Label lblStockTextoDet;
    @FXML
    private Label lblStockValorDet;
    @FXML
    private Label lblDescripcionTitulo;
    @FXML
    private Label lblDescripcionDet;
    @FXML
    private Label lblCantidadTitulo;
    @FXML
    private Button btnMenosCant;
    @FXML
    private Label lblCantidadValor;
    @FXML
    private Button btnMasCant;

    @FXML
    private Button btnAgregarDeseos;
    @FXML
    private Button btnAgregarCarrito;

    @FXML
    private ImageView imgNavInicio;
    @FXML
    private Label lblNavInicio;
    @FXML
    private ImageView imgNavCategorias;
    @FXML
    private Label lblNavCategorias;
    @FXML
    private ImageView imgNavDeseos;
    @FXML
    private Label lblNavDeseos;
    @FXML
    private ImageView imgNavCarrito;
    @FXML
    private Label lblNavCarrito;
    @FXML
    private ImageView imgNavPerfil;
    @FXML
    private Label lblNavPerfil;
    
    
    private int cantidad = 1;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         lblCantidadValor.setText(String.valueOf(cantidad));
    }    
    
    @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("Catalogo", imgFlechaAtrasDet);
    }

    @FXML
    private void onCarritoHeader(MouseEvent event) {
        System.out.println("Clic en carrito del header (Detalle Producto)");
    }

    @FXML
    private void onMenosCant(ActionEvent event) {
        // No permitimos bajar de 1.
        if (cantidad > 1) {
            cantidad--;
            lblCantidadValor.setText(String.valueOf(cantidad));
        }
    }

    @FXML
    private void onMasCant(ActionEvent event) {
        cantidad++;
        lblCantidadValor.setText(String.valueOf(cantidad));
    }

    @FXML
    private void onAgregarDeseos(ActionEvent event) {
        System.out.println("Agregar a deseos (cantidad: " + cantidad + ")");
    }

    @FXML
    private void onAgregarCarrito(ActionEvent event) {
        System.out.println("Agregar al carrito (cantidad: " + cantidad + ")");
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
        System.out.println("Nav: Deseos");
    }

    @FXML
    private void onNavCarrito(MouseEvent event) {
        System.out.println("Nav: Carrito");
    }

    @FXML
    private void onNavPerfil(MouseEvent event) {
        System.out.println("Nav: Perfil");
    }
    
}
