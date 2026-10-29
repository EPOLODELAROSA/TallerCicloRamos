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

/**
 * FXML Controller class
 *
 * @author efren
 */
public class GestionarUsuariosController implements Initializable {

    // --- HEADER ---
    @FXML
    private ImageView imgFlechaAtrasGestUsr;
    @FXML
    private Label lblTituloGestionarUsuarios;

    // --- FILTROS ---
    @FXML
    private TextField txtBuscarUsuario;
    @FXML
    private Button btnBuscarUsuario;

    // --- LISTA ---
    @FXML
    private ScrollPane scrollListaUsuarios;
    @FXML
    private VBox vboxContenedorUsuarios;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        cargarUsuariosSimulados();
    }

    private void cargarUsuariosSimulados() {
        vboxContenedorUsuarios.getChildren().clear();

        // Simulamos los usuarios que vienen de la base de datos
        crearTarjetaUsuario("Juan Pérez", "juanperez08@gmail.com", "300 691 5640");
        crearTarjetaUsuario("María López", "maria.lopez@correo.com", "315 123 4567");
        crearTarjetaUsuario("Carlos Ruiz", "carlosruiz99@hotmail.com", "301 987 6543");
    }

    // ----- MÉTODO AUXILIAR: CREAR TARJETA DE USUARIO -----
    private void crearTarjetaUsuario(String nombre, String correo, String telefono) {
        // 1. Contenedor principal de la tarjeta
        HBox tarjeta = new HBox(15);
        tarjeta.getStyleClass().add("cardUsuarioAdmin");
        tarjeta.setPadding(new Insets(10));
        tarjeta.setPrefWidth(330);
        tarjeta.setMaxWidth(330);
        tarjeta.setAlignment(Pos.CENTER_LEFT);

        // 2. Imagen del usuario (Avatar)
        ImageView imgUsuario = new ImageView();
        try {
            // Cargamos el icono de usuario de la carpeta img
            Image img = new Image("img/user-icon.png"); 
            imgUsuario.setImage(img);
        } catch (Exception e) {
            System.out.println("No se encontró el icono de usuario.");
        }
        imgUsuario.setFitHeight(40);
        imgUsuario.setFitWidth(40);
        imgUsuario.setPreserveRatio(true);

        // 3. Información del usuario (Nombre, correo, teléfono)
        VBox infoBox = new VBox(3);
        Label lblNombre = new Label(nombre);
        lblNombre.getStyleClass().add("lblNombreUsrAdmin");
        
        Label lblCorreo = new Label("Email: " + correo);
        lblCorreo.getStyleClass().add("lblInfoUsrAdmin");
        
        Label lblTelefono = new Label("Teléfono: " + telefono);
        lblTelefono.getStyleClass().add("lblInfoUsrAdmin");

        infoBox.getChildren().addAll(lblNombre, lblCorreo, lblTelefono);

        // 4. Espaciador para empujar el botón a la derecha
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // 5. Botón de eliminar
        VBox botonesBox = new VBox();
        botonesBox.setAlignment(Pos.CENTER_RIGHT);
        
        Button btnEliminar = new Button("ELIMINAR CLIENTE");
        btnEliminar.getStyleClass().add("btnEliminarUsrAdmin");
        btnEliminar.setOnAction(e -> {
            // Pedir confirmación antes de eliminar
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminar Cliente");
            alert.setHeaderText(null);
            alert.setContentText("¿Estás seguro de que deseas eliminar a " + nombre + "?");
            
            java.util.Optional<ButtonType> resultado = alert.showAndWait();
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                System.out.println("Eliminando usuario: " + nombre);
                // Aquí iría la lógica real para eliminar del archivo .txt
                vboxContenedorUsuarios.getChildren().remove(tarjeta); // Quitarlo visualmente
                mostrarAlerta("Éxito", "Usuario eliminado correctamente (Simulación)", Alert.AlertType.INFORMATION);
            }
        });

        botonesBox.getChildren().add(btnEliminar);

        // 6. Ensamblar la tarjeta
        tarjeta.getChildren().addAll(imgUsuario, infoBox, spacer, botonesBox);

        // 7. Agregar al VBox principal
        vboxContenedorUsuarios.getChildren().add(tarjeta);
    }

    // ----- EVENTOS: HEADER -----
    @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("PanelAdministrador", imgFlechaAtrasGestUsr);
    }

    // ----- EVENTOS: FILTROS -----
    @FXML
    private void onBuscar(ActionEvent event) {
        String busqueda = txtBuscarUsuario.getText();
        System.out.println("Buscando usuario: " + busqueda);
        // Aquí iría la lógica para filtrar la lista
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