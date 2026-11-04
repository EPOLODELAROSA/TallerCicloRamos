/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import modelo.Producto;
import util.CarritoManager;

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
    private VBox vboxProductosDestacados;
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
        Rectangle clipBanner = new Rectangle(imgBanner.getFitWidth(), imgBanner.getFitHeight());
        clipBanner.setArcWidth(30);
        clipBanner.setArcHeight(30);
        imgBanner.setClip(clipBanner);

        // Mostrar el nombre del usuario
        if (util.Sesion.getUsuarioLogueado() != null) {
            lblSaludo.setText("Hola, " + util.Sesion.getUsuarioLogueado().getNombre());
        } else {
            lblSaludo.setText("Hola, Invitado");
        }

        // Cargar los productos destacados (los 2 últimos)
        cargarProductosDestacados();
    
    }

    private void cargarProductosDestacados() {
        vboxProductosDestacados.getChildren().clear();
        ArrayList<Producto> lista = ProductoDAO.listar();

        // Si no hay productos, mostramos un mensaje
        if (lista.isEmpty()) {
            Label lblVacio = new Label("No hay productos disponibles.");
            lblVacio.setStyle("-fx-font-family: 'Poppins'; -fx-text-fill: #888888;");
            vboxProductosDestacados.getChildren().add(lblVacio);
            return;
        }

        // Tomamos los 2 últimos productos de la lista (los más recientes)
        int inicio = Math.max(0, lista.size() - 2);

        for (int i = inicio; i < lista.size(); i++) {
            Producto p = lista.get(i);
            crearTarjetaProductoDestacado(p);
        }
    }

    private void crearTarjetaProductoDestacado(Producto p) {
        // Creamos la tarjeta visual (similar al catálogo pero adaptada)
        AnchorPane card = new AnchorPane();
        card.setPrefHeight(100.0); // <--- Reducimos el alto de 130 a 100
        card.setPrefWidth(320.0);
        card.setPadding(new Insets(5)); // <--- Menos padding
        card.getStyleClass().add("cardProdDinamica");

        // Imagen
        ImageView img = new ImageView();
        img.setFitHeight(65.0); // <--- Imagen más pequeña (era 80)
        img.setFitWidth(75.0);  // <--- Imagen más pequeña (era 90)
        img.setLayoutX(5.0);
        img.setLayoutY(15.0);
        img.setPreserveRatio(true);
        try {
            if (p.getImagen() != null && !p.getImagen().isEmpty()) {
                img.setImage(new Image(p.getImagen()));
            }
        } catch (Exception e) {
        }

        // Nombre
        Label lblNom = new Label(p.getNombre());
        lblNom.setLayoutX(90.0); // <--- Más a la izquierda
        lblNom.setLayoutY(5.0);  // <--- Más arriba
        lblNom.getStyleClass().add("lblNombreProdDin");
        lblNom.setWrapText(true);
        lblNom.setMaxWidth(200);

        // Categoría
        Label lblCat = new Label(p.getCategoria());
        lblCat.setLayoutX(90.0);
        lblCat.setLayoutY(30.0);
        lblCat.getStyleClass().add("lblCategoriaProdDin");

        // Precio
        Label lblPre = new Label("$ " + String.format("%,.0f", p.getPrecio()));
        lblPre.setLayoutX(90.0);
        lblPre.setLayoutY(45.0);
        lblPre.getStyleClass().add("lblPrecioProdDin");

        // Stock
        Label lblStockT = new Label("Stock:");
        lblStockT.setLayoutX(175.0); 
        lblStockT.setLayoutY(45.0);
        lblStockT.setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 10px; -fx-text-fill: #2E7D32;");

        Label lblStockV = new Label(p.getStock() + " Und");
        lblStockV.setLayoutX(215.0);
        lblStockV.setLayoutY(45.0);
        lblStockV.setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 10px; -fx-text-fill: #888888;");

        // Botones (Subimos la posición Y y los hacemos más pequeños)
        Button btnDeseo = new Button("Deseo");
        btnDeseo.setLayoutX(90.0);
        btnDeseo.setLayoutY(65.0);
        btnDeseo.setPrefSize(54, 25);
        btnDeseo.getStyleClass().add("btnDeseoDin");
        btnDeseo.setOnAction(e -> mostrarAlerta("Favoritos", p.getNombre() + " agregado.", Alert.AlertType.INFORMATION));

        Button btnCarrito = new Button("Carrito");
        btnCarrito.setLayoutX(150.0);
        btnCarrito.setLayoutY(65.0); 
        btnCarrito.setPrefSize(54, 25);
        btnCarrito.getStyleClass().add("btnCarritoDin");
        btnCarrito.setOnAction(e -> {
            CarritoManager.agregarProducto(p);
            mostrarAlerta("Éxito", "Agregado al carrito.", Alert.AlertType.INFORMATION);
        });

        Button btnVer = new Button("Ver");
        btnVer.setLayoutX(210.0);
        btnVer.setLayoutY(65.0);
        btnVer.setPrefSize(54, 25);
        btnVer.getStyleClass().add("btnVerDin");
        btnVer.setOnAction(e -> {
            util.Sesion.setIdProductoVer(p.getId());
            util.Navegador.cambiar("DetalleProducto", btnVer);
        });

        card.getChildren().addAll(img, lblNom, lblCat, lblPre, lblStockT, lblStockV, btnDeseo, btnCarrito, btnVer);

        // Ajustamos el margen entre tarjetas
        VBox.setMargin(card, new Insets(0, 0, 5, 0));

        vboxProductosDestacados.getChildren().add(card);
    }

    @FXML
    private void onCarritoHeader(MouseEvent event) {
        util.Navegador.cambiar("Carrito", imgIconoCarrito);
    }

    @FXML
    private void onBuscar(ActionEvent event) {
        if (!txtBuscar.getText().isEmpty()) {
            util.Sesion.setFiltroCategoria("Todos");
            util.Sesion.setTextoBusqueda(txtBuscar.getText());
            util.Navegador.cambiar("Catalogo", txtBuscar);
            txtBuscar.clear();
        }
    }

    @FXML
    private void onVerCatalogo(ActionEvent event) {
        util.Sesion.setFiltroCategoria("Todos");
        util.Navegador.cambiar("Catalogo", btnVerCatalogo);
    }

    // Los botones de categoría ahora guardan el filtro en la Sesión y navegan
    @FXML
    private void onCatMotos(ActionEvent event) {
        util.Sesion.setFiltroCategoria("Motos");
        util.Navegador.cambiar("Catalogo", btnCatMotos);
    }

    @FXML
    private void onCatBicicletas(ActionEvent event) {
        util.Sesion.setFiltroCategoria("Bicicletas");
        util.Navegador.cambiar("Catalogo", btnCatBicicletas);
    }

    @FXML
    private void onCatAccesorios(ActionEvent event) {
        util.Sesion.setFiltroCategoria("Accesorios");
        util.Navegador.cambiar("Catalogo", btnCatAccesorios);
    }

    @FXML
    private void onCatRepuestos(ActionEvent event) {
        util.Sesion.setFiltroCategoria("Repuestos");
        util.Navegador.cambiar("Catalogo", btnCatRepuestos);
    }

    @FXML
    private void onNavInicio(MouseEvent event) {
        /* Ya estamos aquí */ }

    @FXML
    private void onNavCategorias(MouseEvent event) {
        util.Navegador.cambiar("Categorias", imgNavCategorias);
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

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    
        @FXML
    private void onBuscarAction(ActionEvent event) {

        if (!txtBuscar.getText().isEmpty()) {
            util.Sesion.setFiltroCategoria("Todos");
            util.Sesion.setTextoBusqueda(txtBuscar.getText()); 
            util.Navegador.cambiar("Catalogo", txtBuscar);
            txtBuscar.clear();
        }
    }
}
