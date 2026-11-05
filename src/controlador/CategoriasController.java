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
        util.Navegador.cambiar("Inicio", imgFlechaAtrasCat);
    }

    @FXML
    private void onCarritoHeader(MouseEvent event) {
        util.Navegador.cambiar("Carrito", imgCarritoCat);
    }

    @FXML
    private void onVerMotos(ActionEvent event) {
        util.Sesion.setFiltroCategoria("Motos");
        util.Navegador.cambiar("Catalogo", btnVerMotos);
    }

    @FXML
    private void onVerBicicletas(ActionEvent event) {
        util.Sesion.setFiltroCategoria("Bicicletas");
        util.Navegador.cambiar("Catalogo", btnVerBicicletas);
    }

    @FXML
    private void onVerRepuestos(ActionEvent event) {
        util.Sesion.setFiltroCategoria("Repuestos");
        util.Navegador.cambiar("Catalogo", btnVerRepuestos);
    }

    @FXML
    private void onVerAccesorios(ActionEvent event) {
        util.Sesion.setFiltroCategoria("Accesorios");
        util.Navegador.cambiar("Catalogo", btnVerAccesorios);
    }

    @FXML
    private void onVerTodos(ActionEvent event) {
        util.Sesion.setFiltroCategoria("Todos");
        util.Navegador.cambiar("Catalogo", btnVerTodos);
    }

    @FXML
    private void onNavInicio(MouseEvent event) {
        util.Navegador.cambiar("Inicio", imgNavInicio);
    }

    @FXML
    private void onNavCategorias(MouseEvent event) {
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
