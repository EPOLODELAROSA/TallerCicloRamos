/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import datos.FavoritoDAO;
import datos.ProductoDAO;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.Producto;
import util.CarritoManager;

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
    private VBox vboxContenedorDeseos;
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
        cargarFavoritos();
    }

    private void cargarFavoritos() {
        vboxContenedorDeseos.getChildren().clear();
        String correo = util.Sesion.getUsuarioLogueado().getCorreo();
        ArrayList<Integer> idsFavoritos = FavoritoDAO.listarIds(correo);

        if (idsFavoritos.isEmpty()) {
            Label lblVacio = new Label("No tienes productos en tu lista de deseos.");
            lblVacio.setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 14px; -fx-text-fill: #888888;");
            vboxContenedorDeseos.getChildren().add(lblVacio);
            return;
        }

        // Recorremos todos los productos y mostramos los que están en favoritos
        for (Producto p : ProductoDAO.listar()) {
            if (idsFavoritos.contains(p.getId())) {
                crearTarjetaDeseo(p);
            }
        }
    }

    private void crearTarjetaDeseo(Producto p) {
        AnchorPane card = new AnchorPane();
        card.setPrefHeight(120.0);
        card.setPrefWidth(320.0);  
        card.getStyleClass().add("cardProductoAdmin");

        // Imagen
        ImageView img = new ImageView();
        img.setFitHeight(70.0);
        img.setFitWidth(80.0);
        img.setLayoutX(10.0);
        img.setLayoutY(20.0);
        img.setPreserveRatio(true);
        try {
            if (p.getImagen() != null && !p.getImagen().isEmpty()) {
                img.setImage(new Image(p.getImagen()));
            }
        } catch (Exception e) {
        }

        // Nombre
        Label lblNom = new Label(p.getNombre());
        lblNom.setLayoutX(100.0);
        lblNom.setLayoutY(10.0);
        lblNom.getStyleClass().add("lblNombreProdAdmin");
        lblNom.setWrapText(true);
        lblNom.setMaxWidth(200);

        // Categoría
        Label lblCat = new Label(p.getCategoria());
        lblCat.setLayoutX(100.0);
        lblCat.setLayoutY(32.0);
        lblCat.getStyleClass().add("lblCategoriaProdAdmin");

        // Precio
        Label lblPre = new Label("$ " + String.format("%,.0f", p.getPrecio()));
        lblPre.setLayoutX(100.0);
        lblPre.setLayoutY(50.0);
        lblPre.getStyleClass().add("lblPrecioProdAdmin");

        // Stock
        Label lblStockT = new Label("Stock:");
        lblStockT.setLayoutX(185.0); // Movido a la izquierda
        lblStockT.setLayoutY(58.0);
        lblStockT.setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 10px; -fx-text-fill: #2E7D32;");

        Label lblStockV = new Label(p.getStock() + " und");
        lblStockV.setLayoutX(225.0);
        lblStockV.setLayoutY(58.0);
        lblStockV.setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 10px; -fx-text-fill: #888888;");

        // Botones (Más pequeños y alineados)
        Button btnQuitar = new Button("Quitar");
        btnQuitar.setLayoutX(100.0); 
        btnQuitar.setLayoutY(80.0);
        btnQuitar.setPrefSize(60, 28);
        btnQuitar.getStyleClass().add("btnEliminarProdAdmin");
        btnQuitar.setOnAction(e -> {
            FavoritoDAO.eliminar(util.Sesion.getUsuarioLogueado().getCorreo(), p.getId());
            cargarFavoritos();
        });

        Button btnCarrito = new Button("Carrito");
        btnCarrito.setLayoutX(170.0);
        btnCarrito.setLayoutY(80.0);
        btnCarrito.setPrefSize(65, 28);
        btnCarrito.getStyleClass().add("btnCarritoDin");
        btnCarrito.setOnAction(e -> {
            CarritoManager.agregarProducto(p);
            mostrarAlerta("Éxito", "Producto agregado al carrito.", Alert.AlertType.INFORMATION);
        });

        Button btnVer = new Button("Ver");
        btnVer.setLayoutX(245.0);
        btnVer.setLayoutY(80.0);
        btnVer.setPrefSize(60, 28);
        btnVer.getStyleClass().add("btnVerDin");
        btnVer.setOnAction(e -> {
            util.Sesion.setIdProductoVer(p.getId());
            util.Navegador.cambiar("DetalleProducto", btnVer);
        });

        card.getChildren().addAll(img, lblNom, lblCat, lblPre, lblStockT, lblStockV, btnQuitar, btnCarrito, btnVer);
        vboxContenedorDeseos.getChildren().add(card);
        VBox.setMargin(card, new Insets(0, 0, 8, 0)); 
    }

    @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("Inicio", imgFlechaAtrasDes);
    }

    @FXML
    private void onCarritoHeader(MouseEvent event) {
        util.Navegador.cambiar("Carrito", imgCarritoDes);
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
        /* Ya estamos aquí */ }

    @FXML
    private void onNavCarrito(MouseEvent event) {
        util.Navegador.cambiar("Carrito", imgNavCarrito);
    }

    @FXML
    private void onNavPerfil(MouseEvent event) {
        util.Navegador.cambiar("Perfil", imgNavPerfil);
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}
