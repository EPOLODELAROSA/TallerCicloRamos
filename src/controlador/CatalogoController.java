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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
    private ScrollPane scrollCatalogo;
  
    @FXML
    private AnchorPane anchorCatalogo;
   
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

    private ArrayList<Producto> listaOriginal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cmbFiltroCategoria.getItems().addAll("Todos", "Motos", "Bicicletas", "Repuestos", "Accesorios");
        cmbFiltroCategoria.getSelectionModel().selectFirst();

        // Cargar productos del archivo
        listaOriginal = ProductoDAO.listar();
        mostrarProductos(listaOriginal);

        // Listener para filtro en tiempo real
        cmbFiltroCategoria.valueProperty().addListener((obs, oldVal, newVal) -> aplicarFiltros());
        txtBuscarCatalogo.textProperty().addListener((obs, oldVal, newVal) -> aplicarFiltros());
    }

    private void aplicarFiltros() {
        String cat = cmbFiltroCategoria.getValue();
        String busq = txtBuscarCatalogo.getText().toLowerCase();

        ArrayList<Producto> filtrada = new ArrayList<>();
        for (Producto p : listaOriginal) {
            boolean coincideCat = cat.equals("Todos") || p.getCategoria().equals(cat);
            boolean coincideBusq = p.getNombre().toLowerCase().contains(busq);
            if (coincideCat && coincideBusq) {
                filtrada.add(p);
            }
        }
        mostrarProductos(filtrada);
    }

    private void mostrarProductos(ArrayList<Producto> lista) {
        anchorCatalogo.getChildren().clear();
        double y = 14.0;

        for (Producto p : lista) {
            // Creamos una tarjeta visual para cada producto
            AnchorPane card = new AnchorPane();
            card.setLayoutX(15.0);
            card.setLayoutY(y);
            card.setPrefHeight(117.0);
            card.setPrefWidth(326.0);
            card.getStyleClass().add("cardProd1");

            // Imagen
            ImageView img = new ImageView();
            img.setFitHeight(71.0);
            img.setFitWidth(95.0);
            img.setLayoutX(14.0);
            img.setLayoutY(26.0);
            img.setPreserveRatio(true);
            try {
                if (p.getImagen() != null && !p.getImagen().isEmpty()) {
                    img.setImage(new Image(p.getImagen()));
                }
            } catch (Exception e) {
            }

            // Nombre
            Label lblNom = new Label(p.getNombre());
            lblNom.setLayoutX(109.0);
            lblNom.setLayoutY(16.0);
            lblNom.getStyleClass().add("lblNombreProd1");

            // Categoría
            Label lblCat = new Label(p.getCategoria());
            lblCat.setLayoutX(109.0);
            lblCat.setLayoutY(34.0);
            lblCat.getStyleClass().add("lblCategoriaProd1");

            // Precio
            Label lblPre = new Label("$ " + String.format("%,.0f", p.getPrecio()));
            lblPre.setLayoutX(109.0);
            lblPre.setLayoutY(52.0);
            lblPre.getStyleClass().add("lblPrecioProd1");

            // Stock
            Label lblStockT = new Label("Stock:");
            lblStockT.setLayoutX(193.0);
            lblStockT.setLayoutY(58.0);
            lblStockT.getStyleClass().add("lblStockTextoProd1");

            Label lblStockV = new Label(p.getStock() + " unidades");
            lblStockV.setLayoutX(230.0);
            lblStockV.setLayoutY(58.0);
            lblStockV.getStyleClass().add("lblStockValorProd1");

            // Botones
            Button btnDeseo = new Button("Deseo");
            btnDeseo.setLayoutX(129.0);
            btnDeseo.setLayoutY(80.0);
            btnDeseo.setPrefHeight(28.0);
            btnDeseo.setPrefWidth(54.0);
            btnDeseo.getStyleClass().add("btnDeseoProd1");
            btnDeseo.setOnAction(e -> {
                mostrarAlerta("Favoritos", "Producto agregado a deseos (Simulación)", Alert.AlertType.INFORMATION);
            });

            Button btnCarrito = new Button("Carrito");
            btnCarrito.setLayoutX(189.0);
            btnCarrito.setLayoutY(79.0);
            btnCarrito.setPrefHeight(28.0);
            btnCarrito.setPrefWidth(54.0);
            btnCarrito.getStyleClass().add("btnCarritoProd1");
            btnCarrito.setOnAction(e -> {
                CarritoManager.agregarProducto(p);
                mostrarAlerta("Éxito", "Producto agregado al carrito.", Alert.AlertType.INFORMATION);
            });

            Button btnVer = new Button("Ver");
            btnVer.setLayoutX(246.0);
            btnVer.setLayoutY(79.0);
            btnVer.setPrefHeight(28.0);
            btnVer.setPrefWidth(54.0);
            btnVer.getStyleClass().add("btnVerProd1");
            btnVer.setOnAction(e -> {
                // Guardamos el ID y navegamos al detalle (lo haremos luego)
                mostrarAlerta("Detalle", "Ver producto: " + p.getNombre(), Alert.AlertType.INFORMATION);
            });

            card.getChildren().addAll(img, lblNom, lblCat, lblPre, lblStockT, lblStockV, btnDeseo, btnCarrito, btnVer);
            anchorCatalogo.getChildren().add(card);

            y += 130.0; // Espaciado entre tarjetas
        }
    }

    @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("Inicio", imgFlechaAtrasCat2);
    }

    @FXML
    private void onCarritoHeader(MouseEvent event) {
        util.Navegador.cambiar("Carrito", imgCarritoCat2);
    }

    @FXML
    private void onBuscar(ActionEvent event) {
        aplicarFiltros();
    }

    @FXML
    private void onFiltroCategoria(ActionEvent event) {
        aplicarFiltros();
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

}
