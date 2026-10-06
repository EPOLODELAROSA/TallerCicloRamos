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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author efren
 */
public class CatalogoController implements Initializable {
    
    @FXML
    private ImageView imgFlechaAtrasCat2;
    @FXML
    private Label lblTituloCatalogo;
    @FXML
    private ImageView imgCarritoCat2;
    @FXML
    private TextField txtBuscarCatalogo;
    @FXML
    private Button btnBuscarCatalogo;
    @FXML
    private ComboBox<String> cmbFiltroCategoria;

    @FXML
    private ImageView imgProd1;
    @FXML
    private Label lblNombreProd1;
    @FXML
    private Label lblCategoriaProd1;
    @FXML
    private Label lblPrecioProd1;
    @FXML
    private Label lblStockTextoProd1;
    @FXML
    private Label lblStockValorProd1;
    @FXML
    private Button btnDeseoProd1;
    @FXML
    private Button btnCarritoProd1;
    @FXML
    private Button btnVerProd1;

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
        cmbFiltroCategoria.getItems().addAll(
                "Motos",
                "Bicicletas",
                "Repuestos",
                "Accesorios"
        );
        cmbFiltroCategoria.getSelectionModel().clearSelection();
    }  
    
    @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("Inicio", imgFlechaAtrasCat2);
    }

    @FXML
    private void onCarritoHeader(MouseEvent event) {
        System.out.println("Clic en carrito del header (Catalogo)");
    }


    @FXML
    private void onBuscar(ActionEvent event) {
        System.out.println("Buscar: " + txtBuscarCatalogo.getText());
    }


    @FXML
    private void onFiltroCategoria(ActionEvent event) {
        String seleccion = cmbFiltroCategoria.getValue();
        System.out.println("Filtro seleccionado: " + seleccion);
    }

    @FXML
    private void onDeseoProd1(ActionEvent event) {
        System.out.println("Deseo: producto 1");
    }

    @FXML
    private void onCarritoProd1(ActionEvent event) {
        System.out.println("Carrito: producto 1");
    }

    @FXML
    private void onVerProd1(ActionEvent event) {
        System.out.println("Ver: producto 1");
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
