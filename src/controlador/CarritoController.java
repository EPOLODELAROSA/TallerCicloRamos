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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
        if (anchorCarrito != null) {
            anchorCarrito.getChildren().clear();
        }

        double y = 10.0;

        if (CarritoManager.estaVacio()) {
            Label lblVacio = new Label("Tu carrito está vacío.");
            lblVacio.setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 14px; -fx-text-fill: #888888;");
            if (anchorCarrito != null) {
                anchorCarrito.getChildren().add(lblVacio);
            }
        } else {
            for (ItemCarrito item : CarritoManager.getItems()) {
                // 1. Tarjeta principal
                AnchorPane card = new AnchorPane();
                card.setPrefHeight(115.0);
                card.setPrefWidth(335.0);
                card.getStyleClass().add("cardCarritoDin");
                card.setLayoutY(y);
                card.setLayoutX(10);

                // 2. Imagen
                ImageView img = new ImageView();
                img.setFitHeight(70.0);
                img.setFitWidth(80.0);
                img.setLayoutX(10.0);
                img.setLayoutY(20.0);
                img.setPreserveRatio(true);
                try {
                    if (item.getProducto().getImagen() != null && !item.getProducto().getImagen().isEmpty()) {
                        img.setImage(new Image(item.getProducto().getImagen()));
                    }
                } catch (Exception e) {
                }

                // 3. Nombre
                Label lblNom = new Label(item.getProducto().getNombre());
                lblNom.setLayoutX(100.0);
                lblNom.setLayoutY(10.0);
                lblNom.getStyleClass().add("lblNombreProdDin");

                // 4. Precio
                Label lblPre = new Label("$ " + formatearPrecio(item.getProducto().getPrecio()));
                lblPre.setLayoutX(100.0);
                lblPre.setLayoutY(30.0);
                lblPre.getStyleClass().add("lblPrecioProdDin");

                // 5. Subtotal
                Label lblSubT = new Label("Subtotal:");
                lblSubT.setLayoutX(100.0);
                lblSubT.setLayoutY(55.0);
                lblSubT.getStyleClass().add("lblSubtotalTextoCarrito1");

                Label lblSubV = new Label("$ " + formatearPrecio(item.getSubtotal()));
                lblSubV.setLayoutX(160.0);
                lblSubV.setLayoutY(55.0);
                lblSubV.getStyleClass().add("lblSubtotalValorCarrito1");

                // 6. Botón Menos (-)
                Button btnMenos = new Button("-");
                btnMenos.setLayoutX(100.0);
                btnMenos.setLayoutY(80.0);
                btnMenos.setPrefHeight(28.0);
                btnMenos.setPrefWidth(28.0);
                btnMenos.getStyleClass().add("btnCantDin");
                btnMenos.setOnAction(e -> {
                    if (item.getCantidad() > 1) {
                        item.setCantidad(item.getCantidad() - 1);
                        cargarCarrito();
                    }
                });

                // 7. Cantidad
                Label lblCant = new Label(String.valueOf(item.getCantidad()));
                lblCant.setLayoutX(140.0);
                lblCant.setLayoutY(85.0);
                lblCant.getStyleClass().add("lblCantidadCarrito1");

                // 8. Botón Más (+)
                Button btnMas = new Button("+");
                btnMas.setLayoutX(165.0);
                btnMas.setLayoutY(80.0);
                btnMas.setPrefHeight(28.0);
                btnMas.setPrefWidth(28.0);
                btnMas.getStyleClass().add("btnCantDin");
                btnMas.setOnAction(e -> {
                    item.setCantidad(item.getCantidad() + 1);
                    cargarCarrito();
                });

                // 9. Botón Eliminar
                Button btnElim = new Button("Eliminar");
                btnElim.setLayoutX(245.0);
                btnElim.setLayoutY(80.0);
                btnElim.setPrefHeight(28.0);
                btnElim.setPrefWidth(75.0);
                btnElim.getStyleClass().add("btnElimDin");
                btnElim.setOnAction(e -> {
                    CarritoManager.eliminarItem(item.getProducto().getId());
                    cargarCarrito();
                });

                card.getChildren().addAll(img, lblNom, lblPre, lblSubT, lblSubV, btnMenos, lblCant, btnMas, btnElim);

                if (anchorCarrito != null) {
                    anchorCarrito.getChildren().add(card);
                }

                y += 125.0;
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
        /* Ya estamos aquí */ }

    @FXML
    private void onNavPerfil(MouseEvent event) {
        util.Navegador.cambiar("Perfil", imgNavPerfil);
    }

}
