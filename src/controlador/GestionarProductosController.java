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

/**
 * FXML Controller class
 *
 * @author efren
 */
public class GestionarProductosController implements Initializable {

    // --- HEADER ---
    @FXML
    private ImageView imgFlechaAtrasGestProd;
    @FXML
    private Label lblTituloGestionarProd;

    // --- FILTROS ---
    @FXML
    private TextField txtBuscarGestProd;
    @FXML
    private Button btnBuscarGestProd;
    @FXML
    private ComboBox<String> cmbFiltroGestProd;

    // --- LISTA ---
    @FXML
    private ScrollPane scrollListaProductos;
    @FXML
    private VBox vboxContenedorProductos;

    // --- BOTÓN INFERIOR ---
    @FXML
    private Button btnRegistrarProdDesdeGest;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 1. Llenar el ComboBox de filtros
        cmbFiltroGestProd.getItems().addAll("Todos", "Motos", "Bicicletas", "Repuestos", "Accesorios");
        cmbFiltroGestProd.getSelectionModel().selectFirst(); // Selecciona "Todos" por defecto

        // 2. Cargar los productos (Por ahora vacío, luego leerá del .txt)
        cargarProductos();
    }

    // ----- MÉTODO PARA CARGAR PRODUCTOS -----
    private void cargarProductos() {
        // Limpiamos el contenedor
        vboxContenedorProductos.getChildren().clear();

        /* 
         * AQUÍ IRÁ LA LÓGICA REAL:
         * Cuando tengamos el ProductoDAO, haremos un ArrayList<Producto> lista = dao.listar();
         * y recorreremos la lista para crear una tarjeta por cada producto.
         */
        
        // --- SIMULACIÓN DE TARJETA (Para que veas cómo se verá) ---
        // Esta tarjeta de ejemplo se borrará cuando conectemos la persistencia real.
        crearTarjetaProducto("Pastillas de Freno Pulsar 200 NS", "Repuestos", "$ 22.100", "18 unidades", "img/prod_pastillas.png");
        
        // Si no hay productos, podríamos mostrar un Label que diga "No hay productos"
        if (vboxContenedorProductos.getChildren().isEmpty()) {
            Label lblVacio = new Label("No hay productos registrados.");
            lblVacio.setStyle("-fx-font-family: 'Poppins'; -fx-text-fill: #888888; -fx-font-size: 14px;");
            vboxContenedorProductos.getChildren().add(lblVacio);
        }
    }

    // ----- MÉTODO AUXILIAR: CREAR UNA TARJETA VISUAL -----
    private void crearTarjetaProducto(String nombre, String categoria, String precio, String stock, String rutaImagen) {
        // 1. Contenedor principal de la tarjeta (HBox)
        HBox tarjeta = new HBox();
        tarjeta.getStyleClass().add("cardProductoAdmin");
        tarjeta.setPadding(new Insets(10));
        tarjeta.setSpacing(10);
        tarjeta.setPrefWidth(330);
        tarjeta.setMaxWidth(330);
        tarjeta.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

        // 2. Imagen del producto
        ImageView img = new ImageView();
        img.setFitHeight(60);
        img.setFitWidth(60);
        img.setPreserveRatio(true);
        
        try {
            // Intentamos cargar la imagen. Si falla, no pasa nada, se queda vacía.
            if (rutaImagen != null && !rutaImagen.isEmpty()) {
                Image imagen = new Image(rutaImagen);
                img.setImage(imagen);
            }
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen: " + rutaImagen);
        }

        // 3. VBox para los textos (Nombre, Categoría, Precio, Stock)
        VBox infoBox = new VBox(3);
        infoBox.setPrefWidth(150); // Ancho fijo para que los textos no empujen los botones

        Label lblNombre = new Label(nombre);
        lblNombre.getStyleClass().add("lblNombreProdAdmin");
        lblNombre.setWrapText(true); // Para que el nombre baje de línea si es muy largo

        Label lblCat = new Label(categoria);
        lblCat.getStyleClass().add("lblCategoriaProdAdmin");

        Label lblPrecio = new Label(precio);
        lblPrecio.getStyleClass().add("lblPrecioProdAdmin");

        HBox hboxStock = new HBox(5); // 5 es el espacio entre las palabras
        
        Label lblStockTitulo = new Label("Stock:");
        lblStockTitulo.setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 11px; -fx-text-fill: #2E7D32;"); // Verde
        
        Label lblStockValor = new Label(stock);
        lblStockValor.setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 11px; -fx-text-fill: #888888;"); // Gris
        
        hboxStock.getChildren().addAll(lblStockTitulo, lblStockValor);
        
        // Lo agregamos al infoBox en lugar del lblStock anterior
        infoBox.getChildren().addAll(lblNombre, lblCat, lblPrecio, hboxStock);

        // 4. VBox para los botones (Eliminar, Editar)
        VBox botonesBox = new VBox(5);
        // Alineamos los botones arriba y a la derecha
        botonesBox.setAlignment(javafx.geometry.Pos.TOP_RIGHT); 
        botonesBox.setPrefWidth(80);

        Button btnEliminar = new Button("ELIMINAR");
        btnEliminar.getStyleClass().add("btnEliminarProdAdmin");
        btnEliminar.setPrefWidth(80); // Ancho fijo para que no se corte el texto
        btnEliminar.setOnAction(e -> {
            mostrarAlerta("Eliminar", "Eliminando producto: " + nombre, Alert.AlertType.CONFIRMATION);
        });

        Button btnEditar = new Button("EDITAR");
        btnEditar.getStyleClass().add("btnEditarProdAdmin");
        btnEditar.setPrefWidth(80); // Ancho fijo
        btnEditar.setOnAction(e -> {
            util.Navegador.cambiar("EditarProducto", btnEditar);
        });

        botonesBox.getChildren().addAll(btnEliminar, btnEditar);

        // 5. Un espaciador (Region) para empujar los botones a la derecha del todo
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // 6. Ensamblar la tarjeta
        tarjeta.getChildren().addAll(img, infoBox, spacer, botonesBox);

        // 7. Agregar la tarjeta al VBox principal
        vboxContenedorProductos.getChildren().add(tarjeta);
    }

    // ----- EVENTOS: HEADER -----
    @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("PanelAdministrador", imgFlechaAtrasGestProd);
    }

    // ----- EVENTOS: FILTROS Y BOTONES -----
    @FXML
    private void onBuscar(ActionEvent event) {
        String busqueda = txtBuscarGestProd.getText();
        String categoria = cmbFiltroGestProd.getValue();
        System.out.println("Buscando: " + busqueda + " | Categoría: " + categoria);
        // Aquí irá la lógica para filtrar la lista real
    }

    @FXML
    private void onRegistrarProducto(ActionEvent event) {
        util.Navegador.cambiar("RegistrarProducto", btnRegistrarProdDesdeGest);
    }

    // ----- MÉTODOS AUXILIARES -----
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}