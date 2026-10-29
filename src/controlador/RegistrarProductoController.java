/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author efren
 */
public class RegistrarProductoController implements Initializable {
    
    @FXML
    private ImageView imgFlechaAtrasReg;
    @FXML
    private Label lblTituloRegistrarProd;

    // --- IMAGEN ---
    @FXML
    private ImageView imgPreviewProducto;
    @FXML
    private Label lblSinImagen;
    @FXML
    private Button btnSeleccionarImagen;

    // --- CAMPOS ---
    @FXML
    private TextField txtNombreProd;
    @FXML
    private TextField txtPrecioProd;
    @FXML
    private TextField txtStockProd;
    @FXML
    private ComboBox<String> cmbCategoriaProd;
    @FXML
    private TextArea txtDescripcionProd;

    // --- BOTONES ---
    @FXML
    private Button btnRegistrarProductoAdmin; // Anotado el nombre real
    @FXML
    private Button btnCancelarRegProd;

    // Variable para guardar la ruta de la imagen seleccionada
    private String rutaImagen = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Llenamos el ComboBox con las 4 categorías
        cmbCategoriaProd.getItems().addAll("Motos", "Bicicletas", "Repuestos", "Accesorios");
    }

    // ----- EVENTOS: HEADER -----
    @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("PanelAdministrador", imgFlechaAtrasReg);
    }

    // ----- EVENTOS: IMAGEN -----
    @FXML
    private void onSeleccionarImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imagen del Producto");
        
        // Filtro para que solo muestre imágenes
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.jpeg")
        );

        // Abrir la ventana para seleccionar el archivo
        File archivo = fileChooser.showOpenDialog(btnSeleccionarImagen.getScene().getWindow());

        if (archivo != null) {
            rutaImagen = archivo.toURI().toString(); // Guardamos la ruta para usarla luego
            Image imagen = new Image(rutaImagen);    // Creamos la imagen
            imgPreviewProducto.setImage(imagen);     // Mostramos la imagen en el recuadro
            lblSinImagen.setVisible(false);          // Ocultamos el texto "Sin imagen"
        }
    }

    // ----- EVENTOS: BOTONES -----
    @FXML
    private void onRegistrarProducto(ActionEvent event) {
        String nombre = txtNombreProd.getText();
        String precio = txtPrecioProd.getText();
        String stock = txtStockProd.getText();
        String categoria = cmbCategoriaProd.getValue();
        String descripcion = txtDescripcionProd.getText();

        // Validaciones básicas
        if (nombre.isEmpty() || precio.isEmpty() || stock.isEmpty() || categoria == null || descripcion.isEmpty()) {
            mostrarAlerta("Campos vacíos", "Por favor, llene todos los campos.", Alert.AlertType.WARNING);
            return;
        }

        // Aquí irá la lógica para guardar en el archivo .txt
        System.out.println("--- GUARDANDO PRODUCTO ---");
        System.out.println("Nombre: " + nombre);
        System.out.println("Precio: " + precio);
        System.out.println("Stock: " + stock);
        System.out.println("Categoría: " + categoria);
        System.out.println("Ruta Imagen: " + rutaImagen);
        
        mostrarAlerta("Éxito", "Producto registrado (Simulación).", Alert.AlertType.INFORMATION);
        
        // Limpiar los campos después de guardar (opcional)
        limpiarCampos();
    }

    @FXML
    private void onCancelar(ActionEvent event) {
        util.Navegador.cambiar("PanelAdministrador", btnCancelarRegProd);
    }

    // ----- MÉTODOS AUXILIARES -----
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void limpiarCampos() {
        txtNombreProd.clear();
        txtPrecioProd.clear();
        txtStockProd.clear();
        cmbCategoriaProd.getSelectionModel().clearSelection();
        txtDescripcionProd.clear();
        imgPreviewProducto.setImage(null);
        lblSinImagen.setVisible(true);
        rutaImagen = "";
    }
}