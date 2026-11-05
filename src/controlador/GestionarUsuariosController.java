/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import datos.UsuarioDAO;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import modelo.Usuario;

/**
 * FXML Controller class
 *
 * @author efren
 */
public class GestionarUsuariosController implements Initializable {

    @FXML
    private ImageView imgFlechaAtrasGestUsr;
    @FXML
    private Label lblTituloGestionarUsuarios;
    @FXML
    private TextField txtBuscarUsuario;
    @FXML
    private Button btnBuscarUsuario;
    @FXML
    private ScrollPane scrollListaUsuarios;
    @FXML
    private VBox vboxContenedorUsuarios;

    private ArrayList<Usuario> todosLosUsuarios;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        todosLosUsuarios = UsuarioDAO.listar();
        mostrarUsuarios(todosLosUsuarios);

        // Listener para buscar en tiempo real
        txtBuscarUsuario.textProperty().addListener((obs, oldVal, newVal) -> {
            filtrarUsuarios(newVal);
        });
    }

    private void filtrarUsuarios(String busqueda) {
        if (busqueda == null || busqueda.isEmpty()) {
            mostrarUsuarios(todosLosUsuarios);
            return;
        }

        String busq = busqueda.toLowerCase();
        ArrayList<Usuario> filtrados = new ArrayList<>();

        for (Usuario u : todosLosUsuarios) {
            if (u.getNombre().toLowerCase().contains(busq) || u.getCorreo().toLowerCase().contains(busq)) {
                filtrados.add(u);
            }
        }
        mostrarUsuarios(filtrados);
    }

    private void mostrarUsuarios(ArrayList<Usuario> lista) {
        vboxContenedorUsuarios.getChildren().clear();

        if (lista.isEmpty()) {
            Label lblVacio = new Label("No se encontraron usuarios.");
            lblVacio.setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 14px; -fx-text-fill: #888888;");
            vboxContenedorUsuarios.getChildren().add(lblVacio);
            return;
        }

        for (Usuario u : lista) {
            crearTarjetaUsuario(u);
        }
    }

    private void crearTarjetaUsuario(Usuario usuario) {
        HBox tarjeta = new HBox(10); // Reducimos el espaciado de 15 a 10
        tarjeta.getStyleClass().add("cardUsuarioAdmin");
        tarjeta.setPadding(new Insets(8)); // Menos padding
        tarjeta.setPrefWidth(330);
        tarjeta.setMaxWidth(330);
        tarjeta.setAlignment(Pos.CENTER_LEFT);

        ImageView imgUsuario = new ImageView();
        try {
            imgUsuario.setImage(new Image("img/user-icon.png"));
        } catch (Exception e) {
        }
        imgUsuario.setFitHeight(35); // Un poco más pequeño
        imgUsuario.setFitWidth(35);
        imgUsuario.setPreserveRatio(true);

        VBox infoBox = new VBox(2);
        infoBox.setPrefWidth(180); // <--- ANCHO FIJO para la info (antes era libre)

        Label lblNombre = new Label(usuario.getNombre());
        lblNombre.getStyleClass().add("lblNombreUsrAdmin");
        lblNombre.setMaxWidth(170);

        Label lblCorreo = new Label("Email: " + usuario.getCorreo());
        lblCorreo.getStyleClass().add("lblInfoUsrAdmin");
        lblCorreo.setMaxWidth(170);

        Label lblTelefono = new Label("Teléfono: " + usuario.getTelefono());
        lblTelefono.getStyleClass().add("lblInfoUsrAdmin");

        infoBox.getChildren().addAll(lblNombre, lblCorreo, lblTelefono);

        // ELIMINAMOS EL SPACER (Region) para que el botón tenga espacio
        // Region spacer = new Region();
        // HBox.setHgrow(spacer, Priority.ALWAYS);
        VBox botonesBox = new VBox();
        botonesBox.setAlignment(Pos.CENTER_RIGHT);
        botonesBox.setPrefWidth(85); // <--- ANCHO FIJO para el botón

        Button btnEliminar = new Button("ELIMINAR");
        btnEliminar.getStyleClass().add("btnEliminarUsrAdmin");
        btnEliminar.setPrefWidth(80); // Ancho suficiente para que no se corte
        btnEliminar.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminar Cliente");
            alert.setHeaderText(null);
            alert.setContentText("¿Estás seguro de que deseas eliminar a " + usuario.getNombre() + "?");

            java.util.Optional<ButtonType> resultado = alert.showAndWait();
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                UsuarioDAO.eliminar(usuario.getCorreo());
                todosLosUsuarios = UsuarioDAO.listar();
                mostrarUsuarios(todosLosUsuarios);
                mostrarAlerta("Éxito", "Usuario eliminado correctamente.", Alert.AlertType.INFORMATION);
            }
        });

        botonesBox.getChildren().add(btnEliminar);

        // Ensamblamos SIN el spacer (quitamos la variable de la lista)
        tarjeta.getChildren().addAll(imgUsuario, infoBox, botonesBox);
        vboxContenedorUsuarios.getChildren().add(tarjeta);
    }

    @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("PanelAdministrador", imgFlechaAtrasGestUsr);
    }

    @FXML
    private void onBuscar(ActionEvent event) {
        filtrarUsuarios(txtBuscarUsuario.getText());
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
