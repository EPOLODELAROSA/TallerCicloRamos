/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import modelo.ItemCarrito;
import util.CarritoManager;

/**
 * FXML Controller class
 *
 * @author efren
 */
public class CarritoController implements Initializable {

    @FXML
    private ImageView imgFlechaAtrasCar;

    @FXML
    private Label lblTituloCarrito;

    @FXML
    private VBox vboxCarrito;

    @FXML
    private AnchorPane anchorCarrito;

    @FXML
    private Label lblResumenTitulo;

    @FXML
    private Label lblSubtotalTitulo;

    @FXML
    private Label lblSubtotalValor;

    @FXML
    private Label lblEnvioTitulo;

    @FXML
    private Label lblEnvioValor;

    @FXML
    private Label lblTotalTitulo;

    @FXML
    private Label lblTotalValor;

    @FXML
    private Button btnVaciarCarrito;

    @FXML
    private Button btnContinuarCompra;

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
        cargarCarrito();
    }

    private void cargarCarrito() {
        // Limpiamos el contenedor (asumiendo que usas un AnchorPane)
        // Si usas un VBox, cambia 'anchorCarrito.getChildren()' por 'vboxCarrito.getChildren()'
        if (anchorCarrito != null) {
            anchorCarrito.getChildren().clear();
        } else if (vboxCarrito != null) {
            vboxCarrito.getChildren().clear();
        }

        double y = 10.0;

        if (CarritoManager.estaVacio()) {
            Label lblVacio = new Label("Tu carrito está vacío.");
            lblVacio.setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 14px; -fx-text-fill: #888888;");
            if (anchorCarrito != null) {
                anchorCarrito.getChildren().add(lblVacio);
            } else {
                vboxCarrito.getChildren().add(lblVacio);
            }
        } else {
            for (ItemCarrito item : CarritoManager.getItems()) {
                // Creamos la tarjeta visual para cada producto
                AnchorPane card = new AnchorPane();
                card.setPrefHeight(120.0);
                card.setPrefWidth(346.0);
                card.getStyleClass().add("cardCarrito1");
                card.setLayoutY(y);
                card.setLayoutX(20);

                // Imagen
                ImageView img = new ImageView();
                img.setFitHeight(120.0);
                img.setFitWidth(89.0);
                img.setLayoutX(6.0);
                img.setLayoutY(1.0);
                img.setPreserveRatio(true);
                try {
                    if (item.getProducto().getImagen() != null) {
                        img.setImage(new Image(item.getProducto().getImagen()));
                    }
                } catch (Exception e) {
                }

                // Nombre
                Label lblNom = new Label(item.getProducto().getNombre());
                lblNom.setLayoutX(101.0);
                lblNom.setLayoutY(5.0);
                lblNom.getStyleClass().add("lblNombreCarrito1");

                // Precio
                Label lblPre = new Label("$ " + formatearPrecio(item.getProducto().getPrecio()));
                lblPre.setLayoutX(101.0);
                lblPre.setLayoutY(24.0);
                lblPre.getStyleClass().add("lblPrecioCarrito1");

                // Subtotal
                Label lblSubT = new Label("Subtotal:");
                lblSubT.setLayoutX(101.0);
                lblSubT.setLayoutY(48.0);
                lblSubT.getStyleClass().add("lblSubtotalTextoCarrito1");

                Label lblSubV = new Label("$ " + formatearPrecio(item.getSubtotal()));
                lblSubV.setLayoutX(175.0);
                lblSubV.setLayoutY(48.0);
                lblSubV.getStyleClass().add("lblSubtotalValorCarrito1");

                // Botones
                Button btnMenos = new Button("-");
                btnMenos.setLayoutX(95.0);
                btnMenos.setLayoutY(76.0);
                btnMenos.setPrefHeight(39.0);
                btnMenos.setPrefWidth(39.0);
                btnMenos.getStyleClass().add("btnMenosCarrito1");
                btnMenos.setOnAction(e -> {
                    if (item.getCantidad() > 1) {
                        item.setCantidad(item.getCantidad() - 1);
                        cargarCarrito();
                    }
                });

                Label lblCant = new Label(String.valueOf(item.getCantidad()));
                lblCant.setLayoutX(143.0);
                lblCant.setLayoutY(84.0);
                lblCant.getStyleClass().add("lblCantidadCarrito1");

                Button btnMas = new Button("+");
                btnMas.setLayoutX(175.0);
                btnMas.setLayoutY(76.0);
                btnMas.setPrefHeight(39.0);
                btnMas.setPrefWidth(35.0);
                btnMas.getStyleClass().add("btnMasCarrito1");
                btnMas.setOnAction(e -> {
                    item.setCantidad(item.getCantidad() + 1);
                    cargarCarrito();
                });

                Button btnElim = new Button("Eliminar");
                btnElim.setLayoutX(267.0);
                btnElim.setLayoutY(76.0);
                btnElim.setPrefHeight(39.0);
                btnElim.setPrefWidth(65.0);
                btnElim.getStyleClass().add("btnEliminarCarrito1");
                btnElim.setOnAction(e -> {
                    CarritoManager.eliminarItem(item.getProducto().getId());
                    cargarCarrito();
                });

                card.getChildren().addAll(img, lblNom, lblPre, lblSubT, lblSubV, btnMenos, lblCant, btnMas, btnElim);

                if (anchorCarrito != null) {
                    anchorCarrito.getChildren().add(card);
                } else {
                    vboxCarrito.getChildren().add(card);
                }

                y += 130.0;
            }
        }

        // Actualizar totales
        lblSubtotalValor.setText("$ " + formatearPrecio(CarritoManager.getSubtotal()));
        lblEnvioValor.setText("$ " + formatearPrecio(CarritoManager.COSTO_ENVIO));
        lblTotalValor.setText("$ " + formatearPrecio(CarritoManager.getTotal()));
    }

    private String formatearPrecio(double valor) {
        NumberFormat formato = NumberFormat.getInstance(new Locale("es", "CO"));
        return formato.format(valor);
    }

    @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("Inicio", imgFlechaAtrasCar);
    }

    @FXML
    private void onVaciarCarrito(ActionEvent event) {
        CarritoManager.vaciar();
        cargarCarrito();
    }

    @FXML
    private void onContinuarCompra(ActionEvent event) {
        if (CarritoManager.estaVacio()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Carrito vacío");
            alert.setHeaderText(null);
            alert.setContentText("Agrega productos antes de continuar.");
            alert.showAndWait();
            return;
        }
        util.Navegador.cambiar("ConfirmarCompra", btnContinuarCompra);
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
    }

    @FXML
    private void onNavPerfil(MouseEvent event) {
        util.Navegador.cambiar("Perfil", imgNavPerfil);
    }

}
