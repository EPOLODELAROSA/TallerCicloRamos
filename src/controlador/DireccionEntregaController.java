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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author efren
 */
public class DireccionEntregaController implements Initializable {

    // --- HEADER ---
    @FXML
    private ImageView imgFlechaAtrasDir;
    @FXML
    private Label lblTituloDireccion;
    @FXML
    private Label lblDatosEntregaDir;

    // --- CAMPOS DE TEXTO ---
    @FXML
    private Label lblDireccionTituloDir;
    @FXML
    private TextField txtDireccionDir;
    @FXML
    private Label lblCiudadTituloDir;
    @FXML
    private TextField txtCiudadDir;
    @FXML
    private Label lblReferenciaTituloDir;
    @FXML
    private TextField txtReferenciaDir;

    // --- BOTONES ---
    @FXML
    private Button btnGuardarDireccion;
    @FXML
    private Button btnCancelarDir;

    // --- NAV INFERIOR ---
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
        // Aquí irá la lógica para cargar la dirección actual del usuario
        // cuando tengamos la persistencia lista.
    }

    // ----- EVENTOS: HEADER -----
    @FXML
    private void onFlechaAtras(MouseEvent event) {
        util.Navegador.cambiar("Perfil", imgFlechaAtrasDir);
    }

    // ----- EVENTOS: BOTONES -----
    @FXML
    private void onGuardarDireccion(ActionEvent event) {
        String direccion = txtDireccionDir.getText();
        String ciudad = txtCiudadDir.getText();
        String referencia = txtReferenciaDir.getText();

        // Imprimimos en consola para probar que captura los datos
        System.out.println("--- GUARDANDO DIRECCIÓN ---");
        System.out.println("Dirección: " + direccion);
        System.out.println("Ciudad: " + ciudad);
        System.out.println("Referencia: " + referencia);
        
        // Cuando tengamos el modelo y la persistencia, aquí actualizaremos
        // los datos del usuario logueado y guardaremos en el archivo .txt.
        
        // Por ahora, volvemos al perfil para que el flujo no se rompa
        util.Navegador.cambiar("Perfil", btnGuardarDireccion);
    }

    @FXML
    private void onCancelar(ActionEvent event) {
        util.Navegador.cambiar("Perfil", btnCancelarDir);
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
}