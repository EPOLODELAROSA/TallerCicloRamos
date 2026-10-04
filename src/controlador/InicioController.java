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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author efren
 */
public class InicioController implements Initializable {

    @FXML
    private ImageView imgLogoInicio;
    @FXML
    private Label lblSaludo;
    @FXML
    private ImageView imgIconoCarrito;
    @FXML
    private TextField txtBuscar;
    @FXML
    private Button btnBuscar;
    @FXML
    private ImageView imgBanner;
    @FXML
    private Label lblTextoBanner;
    @FXML
    private Button btnVerCatalogo;
    @FXML
    private Label lblCategoriasTitulo;
    @FXML
    private Button btnCatMotos;
    @FXML
    private Button btnCatBicicletas;
    @FXML
    private Button btnCatAccesorios;
    @FXML
    private Button btnCatRepuestos;
    @FXML
    private Label lblProductosTitulo;
    @FXML
    private ImageView imgProducto1;
    @FXML
    private Label lblNombreProducto1;
    @FXML
    private Label lblCategoriaProducto1;
    @FXML
    private Label lblPrecioProducto1;
    @FXML
    private Label lblStockTexto1;
    @FXML
    private Label lblStockValor1;
    @FXML
    private Button btnDeseo1;
    @FXML
    private Button btnCarrito1;
    @FXML
    private Button btnVer1;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Rectangle clipBanner = new Rectangle(imgBanner.getFitWidth(), imgBanner.getFitHeight());
        clipBanner.setArcWidth(30);
        clipBanner.setArcHeight(30);
        imgBanner.setClip(clipBanner);
    }


    @FXML
    private void onCarritoHeader(MouseEvent event) {
        System.out.println("Clic en carrito del header");
    }


    @FXML
    private void onBuscar(ActionEvent event) {
        System.out.println("Buscar: " + txtBuscar.getText());
    }


    @FXML
    private void onVerCatalogo(ActionEvent event) {
        System.out.println("Clic en VER CATALOGO");
    }

    @FXML
    private void onCatMotos(ActionEvent event) {
        System.out.println("Categoria: Motos");
    }

    @FXML
    private void onCatBicicletas(ActionEvent event) {
        System.out.println("Categoria: Bicicletas");
    }

    @FXML
    private void onCatAccesorios(ActionEvent event) {
        System.out.println("Categoria: Accesorios");
    }

    @FXML
    private void onCatRepuestos(ActionEvent event) {
        System.out.println("Categoria: Repuestos");
    }

    @FXML
    private void onDeseo1(ActionEvent event) {
        System.out.println("Clic en Deseo (producto 1)");
    }

    @FXML
    private void onCarrito1(ActionEvent event) {
        System.out.println("Clic en Carrito (producto 1)");
    }

    @FXML
    private void onVer1(ActionEvent event) {
        System.out.println("Clic en Ver (producto 1)");
    }

    @FXML
    private void onNavInicio(MouseEvent event) {
        System.out.println("Nav: Inicio");
    }

    @FXML
    private void onNavCategorias(MouseEvent event) {
        System.out.println("Nav: Categorias");
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