/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
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
public class CarritoController implements Initializable {
    
    @FXML
    private ImageView imgFlechaAtrasCar;
    @FXML
    private Label lblTituloCarrito;

    @FXML
    private ImageView imgProdCarrito1;
    @FXML
    private Label lblNombreCarrito1;
    @FXML
    private Label lblPrecioCarrito1;
    @FXML
    private Label lblSubtotalTextoCarrito1;
    @FXML
    private Label lblSubtotalValorCarrito1;
    @FXML
    private Button btnMenosCarrito1;
    @FXML
    private Label lblCantidadCarrito1;
    @FXML
    private Button btnMasCarrito1;
    @FXML
    private Button btnEliminarCarrito1;

    @FXML
    private Label lblResumenTitulo;
    @FXML
    private Label lblSubtotalTitulo;
    @FXML
    private Label lblSubtotalValor;
    @FXML
    private Label lblEnvioTitulo;
    @FXML
    private Label lblEnvioValor;
    @FXML
    private Label lblTotalTitulo;
    @FXML
    private Label lblTotalValor;

    @FXML
    private Button btnVaciarCarrito;
    @FXML
    private Button btnContinuarCompra;

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
    
    
   
    private final double PRECIO_UNITARIO = 204000.0;


    private final double ENVIO = 8000.0;

   
    private int cantidad = 1;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lblCantidadCarrito1.setText(String.valueOf(cantidad));
        actualizarTotales();
    }   
    
    private void actualizarTotales() {
        double subtotalProducto = PRECIO_UNITARIO * cantidad;

        lblSubtotalValorCarrito1.setText(formatearPrecio(subtotalProducto));

        lblSubtotalValor.setText(formatearPrecio(subtotalProducto));
        lblEnvioValor.setText(formatearPrecio(ENVIO));
        lblTotalValor.setText(formatearPrecio(subtotalProducto + ENVIO));
    }

    /**
     * Formatea un numero como precio en pesos colombianos.
     * Ejemplo: 204000 -> "$ 204.000"
     */
    private String formatearPrecio(double valor) {
        NumberFormat formato = NumberFormat.getInstance(new Locale("es", "CO"));
        return "$ " + formato.format(valor);
    }


    @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("Inicio", imgFlechaAtrasCar);
    }


    @FXML
    private void onMenosCarrito1(ActionEvent event) {
        if (cantidad > 1) {
            cantidad--;
            lblCantidadCarrito1.setText(String.valueOf(cantidad));
            actualizarTotales();
        }
    }

    @FXML
    private void onMasCarrito1(ActionEvent event) {
        cantidad++;
        lblCantidadCarrito1.setText(String.valueOf(cantidad));
        actualizarTotales();
    }

    @FXML
    private void onEliminarCarrito1(ActionEvent event) {
        System.out.println("Eliminar producto del carrito");
        // Cuando exista persistencia, aqui eliminamos el producto y actualizamos.
    }


    @FXML
    private void onVaciarCarrito(ActionEvent event) {
        System.out.println("Vaciar carrito");
        // Cuando exista persistencia, aqui se vacia el carrito.
    }

    @FXML
    private void onContinuarCompra(ActionEvent event) {
        System.out.println("Continuar compra");
        // Cuando exista ConfirmarCompra.fxml, aqui navegamos.
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
        System.out.println("Ya estamos en Carrito");
    }

    @FXML
    private void onNavPerfil(MouseEvent event) {
        System.out.println("Nav: Perfil");
    }
    
}
