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
import javafx.collections.FXCollections;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import modelo.Producto;

/**
 * FXML Controller class
 *
 * @author efren
 */
public class GestionarProductosController implements Initializable {

    @FXML
    private ImageView imgFlechaAtrasGestProd;
    
    @FXML
    private Label lblTituloGestionarProd;

    @FXML
    private TextField txtBuscarGestProd;
    
    @FXML
    private Button btnBuscarGestProd;
    
    @FXML
    private ComboBox<String> cmbFiltroGestProd;

    @FXML
    private ScrollPane scrollListaProductos;
    
    @FXML
    private VBox vboxContenedorProductos;

    @FXML
    private Button btnRegistrarProdDesdeGest;
    
    private ArrayList<Producto> listaOriginal;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbFiltroGestProd.getItems().addAll("Todos", "Motos", "Bicicletas", "Repuestos", "Accesorios");
        cmbFiltroGestProd.getSelectionModel().selectFirst();
        
        // Cargamos todos los productos del archivo
        listaOriginal = ProductoDAO.listar();
        mostrarProductosEnPantalla(listaOriginal);

        // Listener para el filtro en tiempo real
        cmbFiltroGestProd.valueProperty().addListener((obs, oldVal, newVal) -> {
            aplicarFiltros();
        });
        
        // Listener para el buscador en tiempo real
        txtBuscarGestProd.textProperty().addListener((obs, oldVal, newVal) -> {
            aplicarFiltros();
        });
    }

    private void aplicarFiltros() {
        String categoriaSeleccionada = cmbFiltroGestProd.getValue();
        String textoBusqueda = txtBuscarGestProd.getText().toLowerCase();

        ArrayList<Producto> listaFiltrada = new ArrayList<>();

        for (Producto p : listaOriginal) {
            boolean coincideCategoria = categoriaSeleccionada.equals("Todos") || p.getCategoria().equals(categoriaSeleccionada);
            boolean coincideBusqueda = p.getNombre().toLowerCase().contains(textoBusqueda);

            if (coincideCategoria && coincideBusqueda) {
                listaFiltrada.add(p);
            }
        }
        mostrarProductosEnPantalla(listaFiltrada);
    }

    private void mostrarProductosEnPantalla(ArrayList<Producto> lista) {
        vboxContenedorProductos.getChildren().clear();

        if (lista.isEmpty()) {
            Label lblVacio = new Label("No se encontraron productos.");
            lblVacio.setStyle("-fx-font-family: 'Poppins'; -fx-text-fill: #888888; -fx-font-size: 14px;");
            vboxContenedorProductos.getChildren().add(lblVacio);
            return;
        }

        for (Producto p : lista) {
            String precioFormateado = "$ " + String.format("%,.0f", p.getPrecio());
            crearTarjetaProducto(p.getId(), p.getNombre(), p.getCategoria(), 
                    precioFormateado, p.getStock() + " unidades", p.getImagen());
        }
    }

    private void crearTarjetaProducto(int id, String nombre, String categoria, String precio, String stock, String rutaImagen) {
        HBox tarjeta = new HBox();
        tarjeta.getStyleClass().add("cardProductoAdmin");
        tarjeta.setPadding(new Insets(10));
        tarjeta.setSpacing(10);
        tarjeta.setPrefWidth(330);
        tarjeta.setMaxWidth(330);
        tarjeta.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

        ImageView img = new ImageView();
        img.setFitHeight(60);
        img.setFitWidth(60);
        img.setPreserveRatio(true);
        try {
            if (rutaImagen != null && !rutaImagen.isEmpty()) {
                img.setImage(new Image(rutaImagen));
            }
        } catch (Exception e) {}

        VBox infoBox = new VBox(3);
        infoBox.setPrefWidth(150); 

        Label lblNombre = new Label(nombre);
        lblNombre.getStyleClass().add("lblNombreProdAdmin");
        lblNombre.setWrapText(true); 

        Label lblCat = new Label(categoria);
        lblCat.getStyleClass().add("lblCategoriaProdAdmin");

        Label lblPrecio = new Label(precio);
        lblPrecio.getStyleClass().add("lblPrecioProdAdmin");

        HBox hboxStock = new HBox(5); 
        Label lblStockTitulo = new Label("Stock:");
        lblStockTitulo.setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 11px; -fx-text-fill: #2E7D32;"); 
        Label lblStockValor = new Label(stock);
        lblStockValor.setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 11px; -fx-text-fill: #888888;"); 
        hboxStock.getChildren().addAll(lblStockTitulo, lblStockValor);
        
        infoBox.getChildren().addAll(lblNombre, lblCat, lblPrecio, hboxStock);

        VBox botonesBox = new VBox(5);
        botonesBox.setAlignment(javafx.geometry.Pos.TOP_RIGHT); 
        botonesBox.setPrefWidth(80);

        Button btnEliminar = new Button("ELIMINAR");
        btnEliminar.getStyleClass().add("btnEliminarProdAdmin");
        btnEliminar.setPrefWidth(80); 
        btnEliminar.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminar Producto");
            alert.setHeaderText(null);
            alert.setContentText("¿Seguro que deseas eliminar " + nombre + "?");
            
            java.util.Optional<javafx.scene.control.ButtonType> res = alert.showAndWait();
            if (res.isPresent() && res.get() == javafx.scene.control.ButtonType.OK) {
                ProductoDAO.eliminar(id);
                // Recargamos la lista
                listaOriginal = ProductoDAO.listar();
                aplicarFiltros();
            }
        });

        Button btnEditar = new Button("EDITAR");
        btnEditar.getStyleClass().add("btnEditarProdAdmin");
        btnEditar.setPrefWidth(80); 
        btnEditar.setOnAction(e -> {
            // Guardamos el ID en una variable estática temporal para que EditarProducto sepa cuál cargar
            util.Sesion.setIdProductoEditar(id);
            util.Navegador.cambiar("EditarProducto", btnEditar);
        });

        botonesBox.getChildren().addAll(btnEliminar, btnEditar);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        tarjeta.getChildren().addAll(img, infoBox, spacer, botonesBox);
        vboxContenedorProductos.getChildren().add(tarjeta);
    }

    @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("PanelAdministrador", imgFlechaAtrasGestProd);
    }

    @FXML
    private void onBuscar(ActionEvent event) {
        aplicarFiltros(); // Llamamos al método que filtra
    }

    @FXML
    private void onRegistrarProducto(ActionEvent event) {
        util.Navegador.cambiar("RegistrarProducto", btnRegistrarProdDesdeGest);
    }
}