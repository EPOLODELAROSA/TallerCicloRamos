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
public class EditarProductoController implements Initializable {

    // --- HEADER ---
    @FXML
    private ImageView imgFlechaAtrasEdit;
    @FXML
    private Label lblTituloEditarProd;

    // --- IMAGEN ---
    @FXML
    private ImageView imgPreviewEditarProd;
    @FXML
    private Button btnCambiarImagenEdit;

    // --- CAMPOS ---
    @FXML
    private TextField txtNombreEdit;
    @FXML
    private TextField txtPrecioEdit;
    @FXML
    private TextField txtStockEdit;
    @FXML
    private ComboBox<String> cmbCatEdit;
    @FXML
    private TextArea txtDescEdit;

    // --- BOTONES ---
    @FXML
    private Button btnCancelarEditProd;
    @FXML
    private Button btnGuardarEditProd;

    // Variable para guardar la ruta de la nueva imagen (si se cambia)
    private String rutaImagenNueva = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        cmbCatEdit.getItems().addAll("Motos", "Bicicletas", "Repuestos", "Accesorios");
        cargarDatosSimulados();
    }

    private void cargarDatosSimulados() {
        txtNombreEdit.setText("Kit de Arrastre NS 200");
        txtPrecioEdit.setText("204800");
        txtStockEdit.setText("15");
        cmbCatEdit.setValue("Repuestos");
        txtDescEdit.setText("Con la referencia B35-JL00-09, este kit de arrastre ofrece una solución integral y confiable para la transmisión final de tu Bajaj Pulsar NS 200. Fabricado bajo estándares de calidad BAJAJ.");
        
        // Intentamos cargar una imagen de ejemplo
        try {
            Image img = new Image("img/prod_kit.png"); // Asegúrate de tener esta imagen o cámbiala
            imgPreviewEditarProd.setImage(img);
        } catch (Exception e) {
            System.out.println("Imagen de ejemplo no encontrada.");
        }
    }

    @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("GestionarProductos", imgFlechaAtrasEdit);
    }

    @FXML
    private void onCambiarImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Nueva Imagen");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.jpeg")
        );

        File archivo = fileChooser.showOpenDialog(btnCambiarImagenEdit.getScene().getWindow());

        if (archivo != null) {
            rutaImagenNueva = archivo.toURI().toString();
            Image imagen = new Image(rutaImagenNueva);
            imgPreviewEditarProd.setImage(imagen);
        }
    }

    @FXML
    private void onGuardarCambios(ActionEvent event) {
        String nombre = txtNombreEdit.getText();
        String precio = txtPrecioEdit.getText();
        String stock = txtStockEdit.getText();
        String categoria = cmbCatEdit.getValue();
        String descripcion = txtDescEdit.getText();

        // Validaciones
        if (nombre.isEmpty() || precio.isEmpty() || stock.isEmpty() || categoria == null || descripcion.isEmpty()) {
            mostrarAlerta("Campos vacíos", "Por favor, llene todos los campos.", Alert.AlertType.WARNING);
            return;
        }

        System.out.println("--- ACTUALIZANDO PRODUCTO ---");
        System.out.println("Nombre: " + nombre);
        System.out.println("Precio: " + precio);
        System.out.println("Stock: " + stock);
        System.out.println("Categoría: " + categoria);
        if (!rutaImagenNueva.isEmpty()) {
            System.out.println("Nueva Imagen: " + rutaImagenNueva);
        } else {
            System.out.println("Imagen: (Se mantiene la anterior)");
        }

        mostrarAlerta("Éxito", "Producto actualizado (Simulación).", Alert.AlertType.INFORMATION);

        util.Navegador.cambiar("GestionarProductos", btnGuardarEditProd);
    }

    @FXML
    private void onCancelar(ActionEvent event) {
        util.Navegador.cambiar("GestionarProductos", btnCancelarEditProd);
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}