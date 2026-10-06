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
public class CategoriasController implements Initializable {
    
    @FXML
    private ImageView imgFlechaAtrasCat;
    @FXML
    private Label lblTituloCategorias;
    @FXML
    private ImageView imgCarritoCat;

    @FXML
    private ImageView imgMotos;
    @FXML
    private Label lblNombreMotos;
    @FXML
    private Button btnVerMotos;

    @FXML
    private ImageView imgBicicletas;
    @FXML
    private Label lblNombreBicicletas;
    @FXML
    private Button btnVerBicicletas;

    @FXML
    private ImageView imgRepuestos;
    @FXML
    private Label lblNombreRepuestos;
    @FXML
    private Button btnVerRepuestos;

    @FXML
    private ImageView imgAccesorios;
    @FXML
    private Label lblNombreAccesorios;
    @FXML
    private Button btnVerAccesorios;

    @FXML
    private Label lblExplora;
    @FXML
    private Button btnVerTodos;

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
    }   
    
    @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("Inicio", imgFlechaAtrasCat);
    }

    @FXML
    private void onCarritoHeader(MouseEvent event) {
        System.out.println("Clic en carrito del header (Categorias)");
    }


    @FXML
    private void onVerMotos(ActionEvent event) {
        System.out.println("Ver productos: Motos");
    }

    @FXML
    private void onVerBicicletas(ActionEvent event) {
        System.out.println("Ver productos: Bicicletas");
    }

    @FXML
    private void onVerRepuestos(ActionEvent event) {
        System.out.println("Ver productos: Repuestos");
    }

    @FXML
    private void onVerAccesorios(ActionEvent event) {
        System.out.println("Ver productos: Accesorios");
    }


    @FXML
    private void onVerTodos(ActionEvent event) {
        System.out.println("Clic en VER TODOS LOS PRODUCTOS");
    }


    @FXML
    private void onNavInicio(MouseEvent event) {
        util.Navegador.cambiar("Inicio", imgNavInicio);
    }

    @FXML
    private void onNavCategorias(MouseEvent event) {
        System.out.println("Ya estamos en Categorias");
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
