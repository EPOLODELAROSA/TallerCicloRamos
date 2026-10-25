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
public class ListaDeseosController implements Initializable {
    
    @FXML
    private ImageView imgFlechaAtrasDes;
    @FXML
    private Label lblTituloDeseos;
    @FXML
    private ImageView imgCarritoDes;

    @FXML
    private ImageView imgProdDeseo1;
    @FXML
    private Label lblNombreDeseo1;
    @FXML
    private Label lblCategoriaDeseo1;
    @FXML
    private Label lblPrecioDeseo1;
    @FXML
    private Label lblStockTextoDeseo1;
    @FXML
    private Label lblStockValorDeseo1;
    @FXML
    private Button btnQuitarDeseo1;
    @FXML
    private Button btnCarritoDeseo1;
    @FXML
    private Button btnVerDeseo1;

    @FXML
    private ImageView imgProdDeseo2;
    @FXML
    private Label lblNombreDeseo2;
    @FXML
    private Label lblCategoriaDeseo2;
    @FXML
    private Label lblPrecioDeseo2;
    @FXML
    private Label lblStockTextoDeseo2;
    @FXML
    private Label lblStockValorDeseo2;
    @FXML
    private Button btnQuitarDeseo2;
    @FXML
    private Button btnCarritoDeseo2;
    @FXML
    private Button btnVerDeseo2;

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
        // TODO
    }    
    
    @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("Inicio", imgFlechaAtrasDes);
    }

    @FXML
    private void onCarritoHeader(MouseEvent event) {
        System.out.println("Clic en carrito del header (Lista Deseos)");
    }

    @FXML
    private void onQuitarDeseo1(ActionEvent event) {
        System.out.println("Quitar producto 1 de deseos");
    }

    @FXML
    private void onCarritoDeseo1(ActionEvent event) {
        System.out.println("Mover producto 1 al carrito");
    }

    @FXML
    private void onVerDeseo1(ActionEvent event) {
        System.out.println("Ver producto 1");
    }

    @FXML
    private void onQuitarDeseo2(ActionEvent event) {
        System.out.println("Quitar producto 2 de deseos");
    }

    @FXML
    private void onCarritoDeseo2(ActionEvent event) {
        System.out.println("Mover producto 2 al carrito");
    }

    @FXML
    private void onVerDeseo2(ActionEvent event) {
        System.out.println("Ver producto 2");
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
        System.out.println("Ya estamos en Lista de Deseos");
    }

    @FXML
    private void onNavCarrito(MouseEvent event) {
        util.Navegador.cambiar("Carrito", imgNavCarrito);
    }

    @FXML
    private void onNavPerfil(MouseEvent event) {
        System.out.println("Nav: Perfil");
    }
    
}
