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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import modelo.Producto;
import util.CarritoManager;
import datos.FavoritoDAO;

/**
 * FXML Controller class
 *
 * @author efren
 */
public class DetalleProductoController implements Initializable {

    @FXML
    private ImageView imgFlechaAtrasDet;
    @FXML
    private Label lblTituloDetalle;
    @FXML
    private ImageView imgCarritoDet;
    @FXML
    private ImageView imgProductoDet;
    @FXML
    private Label lblCategoriaDet;
    @FXML
    private Label lblNombreDet;
    @FXML
    private Label lblPrecioDet;
    @FXML
    private Label lblStockTextoDet;
    @FXML
    private Label lblStockValorDet;
    @FXML
    private Label lblDescripcionTitulo;
    @FXML
    private Label lblDescripcionDet;
    @FXML
    private Label lblCantidadTitulo;
    @FXML
    private Button btnMenosCant;
    @FXML
    private Label lblCantidadValor;
    @FXML
    private Button btnMasCant;
    @FXML
    private Button btnAgregarDeseos;
    @FXML
    private Button btnAgregarCarrito;
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

    private int cantidad = 1;
    private Producto productoActual;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lblCantidadValor.setText(String.valueOf(cantidad));

        // 1. Obtenemos el ID que guardamos en la Sesión desde el Catálogo
        int idProducto = util.Sesion.getIdProductoVer();

        // 2. Buscamos el producto en el archivo .txt
        for (Producto p : ProductoDAO.listar()) {
            if (p.getId() == idProducto) {
                productoActual = p;
                break;
            }
        }

        // 3. Si lo encontramos, llenamos la pantalla
        if (productoActual != null) {
            lblNombreDet.setText(productoActual.getNombre());
            lblCategoriaDet.setText(productoActual.getCategoria());
            lblPrecioDet.setText("$ " + String.format("%,.0f", productoActual.getPrecio()));
            lblStockValorDet.setText(productoActual.getStock() + " unidades");
            lblDescripcionDet.setText(productoActual.getDescripcion());
            lblDescripcionDet.setWrapText(true); // Para que el texto largo baje de línea

            try {
                if (productoActual.getImagen() != null && !productoActual.getImagen().isEmpty()) {
                    imgProductoDet.setImage(new Image(productoActual.getImagen()));
                }
            } catch (Exception e) {
                System.out.println("No se pudo cargar la imagen.");
            }
        } else {
            mostrarAlerta("Error", "Producto no encontrado.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("Catalogo", imgFlechaAtrasDet);
    }

    @FXML
    private void onCarritoHeader(MouseEvent event) {
        util.Navegador.cambiar("Carrito", imgCarritoDet);
    }

    @FXML
    private void onMenosCant(ActionEvent event) {
        if (cantidad > 1) {
            cantidad--;
            lblCantidadValor.setText(String.valueOf(cantidad));
        }
    }

    @FXML
    private void onMasCant(ActionEvent event) {
        if (productoActual != null && cantidad < productoActual.getStock()) {
            cantidad++;
            lblCantidadValor.setText(String.valueOf(cantidad));
        } else {
            mostrarAlerta("Stock límite", "No puedes agregar más unidades de las disponibles.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void onAgregarDeseos(ActionEvent event) {
        if (productoActual != null) {
            datos.FavoritoDAO.agregar(util.Sesion.getUsuarioLogueado().getCorreo(), productoActual.getId());
            mostrarAlerta("Favoritos", productoActual.getNombre() + " agregado a tu lista de deseos.", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void onAgregarCarrito(ActionEvent event) {
        if (productoActual != null) {

            for (int i = 0; i < cantidad; i++) {
                CarritoManager.agregarProducto(productoActual);
            }
            mostrarAlerta("Éxito", cantidad + " unidad(es) de " + productoActual.getNombre() + " agregada(s) al carrito.", Alert.AlertType.INFORMATION);
        }
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
