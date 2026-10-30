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
public class EditarProductoController implements Initializable {

    @FXML
    private ImageView imgFlechaAtrasEdit;
    
    @FXML
    private Label lblTituloEditarProd;

    @FXML
    private ImageView imgPreviewEditarProd;
    
    @FXML
    private Button btnCambiarImagenEdit;

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

    @FXML
    private Button btnCancelarEditProd;
    
    @FXML
    private Button btnGuardarEditProd;


    private String rutaImagenNueva = "";
    private int idProductoActual;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbCatEdit.getItems().addAll("Motos", "Bicicletas", "Repuestos", "Accesorios");
        
        idProductoActual = util.Sesion.getIdProductoEditar();
        cargarDatosReales();
    }

    private void cargarDatosReales() {
        // Buscamos el producto por su ID
        for (Producto p : ProductoDAO.listar()) {
            if (p.getId() == idProductoActual) {
                txtNombreEdit.setText(p.getNombre());
                txtPrecioEdit.setText(String.valueOf(p.getPrecio()));
                txtStockEdit.setText(String.valueOf(p.getStock()));
                cmbCatEdit.setValue(p.getCategoria());
                txtDescEdit.setText(p.getDescripcion());
                
                try {
                    if (p.getImagen() != null && !p.getImagen().isEmpty()) {
                        imgPreviewEditarProd.setImage(new Image(p.getImagen()));
                    }
                } catch (Exception e) {}
                break;
            }
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
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg"));
        File archivo = fileChooser.showOpenDialog(btnCambiarImagenEdit.getScene().getWindow());

        if (archivo != null) {
            rutaImagenNueva = archivo.toURI().toString();
            imgPreviewEditarProd.setImage(new Image(rutaImagenNueva));
        }
    }

    @FXML
    private void onGuardarCambios(ActionEvent event) {
        String nombre = txtNombreEdit.getText();
        String precioStr = txtPrecioEdit.getText();
        String stockStr = txtStockEdit.getText();
        String categoria = cmbCatEdit.getValue();
        String descripcion = txtDescEdit.getText();

        if (nombre.isEmpty() || precioStr.isEmpty() || stockStr.isEmpty() || categoria == null || descripcion.isEmpty()) {
            mostrarAlerta("Campos vacíos", "Por favor, llene todos los campos.", Alert.AlertType.WARNING);
            return;
        }

        try {
            double precio = Double.parseDouble(precioStr);
            int stock = Integer.parseInt(stockStr);
            
            // Si no cambió la imagen, mantenemos la que tenía
            String imagenFinal = rutaImagenNueva;
            if (imagenFinal.isEmpty()) {
                for (Producto p : ProductoDAO.listar()) {
                    if (p.getId() == idProductoActual) {
                        imagenFinal = p.getImagen();
                        break;
                    }
                }
            }

            Producto pActualizado = new Producto(nombre, imagenFinal, precio, stock, categoria, descripcion);
            pActualizado.setId(idProductoActual);

            // Eliminamos el viejo y guardamos el nuevo (simulando edición)
            ProductoDAO.eliminar(idProductoActual);
            ProductoDAO.guardarConId(pActualizado);

            mostrarAlerta("Éxito", "Producto actualizado correctamente.", Alert.AlertType.INFORMATION);
            util.Navegador.cambiar("GestionarProductos", btnGuardarEditProd);
            
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Precio y stock deben ser números.", Alert.AlertType.ERROR);
        }
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