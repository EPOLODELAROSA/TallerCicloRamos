/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import datos.ProductoDAO;
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
import modelo.Producto;

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

    @FXML
    private ImageView imgPreviewProducto;
    
    @FXML
    private Label lblSinImagen;
    
    @FXML
    private Button btnSeleccionarImagen;

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

    @FXML
    private Button btnRegistrarProductoAdmin; 

    @FXML
    private Button btnCancelarRegProd;

    // Variable para guardar la ruta de la imagen seleccionada
    private String rutaImagen = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbCategoriaProd.getItems().addAll("Motos", "Bicicletas", "Repuestos", "Accesorios");
    }

    @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("PanelAdministrador", imgFlechaAtrasReg);
    }

    @FXML
    private void onSeleccionarImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imagen del Producto");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.jpeg")
        );

        File archivo = fileChooser.showOpenDialog(btnSeleccionarImagen.getScene().getWindow());

        if (archivo != null) {
            rutaImagen = archivo.toURI().toString();
            Image imagen = new Image(rutaImagen);
            imgPreviewProducto.setImage(imagen);
            lblSinImagen.setVisible(false);
        }
    }

    @FXML
    private void onRegistrarProducto(ActionEvent event) {
        String nombre = txtNombreProd.getText();
        String precioStr = txtPrecioProd.getText();
        String stockStr = txtStockProd.getText();
        String categoria = cmbCategoriaProd.getValue();
        String descripcion = txtDescripcionProd.getText();

        if (nombre.isEmpty() || precioStr.isEmpty() || stockStr.isEmpty() || categoria == null || descripcion.isEmpty()) {
            mostrarAlerta("Campos vacíos", "Por favor, llene todos los campos.", Alert.AlertType.WARNING);
            return;
        }

        try {
            double precio = Double.parseDouble(precioStr);
            int stock = Integer.parseInt(stockStr);

            Producto nuevoProducto = new Producto(nombre, rutaImagen, precio, stock, categoria, descripcion);
            ProductoDAO.guardar(nuevoProducto);

            mostrarAlerta("Éxito", "Producto registrado correctamente.", Alert.AlertType.INFORMATION);
            limpiarCampos();
            
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de formato", "El precio y el stock deben ser números válidos.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onCancelar(ActionEvent event) {
        util.Navegador.cambiar("PanelAdministrador", btnCancelarRegProd);
    }

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