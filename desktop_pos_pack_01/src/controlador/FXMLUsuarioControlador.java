package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.uri;
import utils.utils;

public class FXMLUsuarioControlador extends Application implements Initializable {

    @FXML
    private TextField txtImagen, txtNombre, txtPerfil, txtApellido, txtUsuario;
    @FXML
    private ImageView imgImagen;
    @FXML
    private ComboBox<?> cbxSexo;
    @FXML
    private PasswordField txtContrasena, txtConfirmarContrasena;
    @FXML
    private Button btnNuevo, btnImagen, btnGuardar, btnPerfil, btnCancelar;
    @FXML
    private CheckBox ckbVerContrasena, ckbVerConfirmaContrasena;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(uri.USUARIO));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.txtPerfil.setEditable(false);
        this.eventoBoton();
    }

    private Boolean validarCampos() {
        if (this.txtPerfil.getText().equals("")
                || this.txtUsuario.getText().equals("")
                || this.txtNombre.getText().equals("")) {
            return false;
        }
        return true;
    }

    private void guardarUsuario() {
        if (this.validarCampos()) {
        } else {
            utils.mensaje("Faltan campos de llenar.", "Es necesario llenar los campos con asterisco", Alert.AlertType.ERROR);
        }
    }

    private void eventoBoton() {
        this.btnGuardar.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 1) {
                this.guardarUsuario();
            } else {
                event.consume();
            }
        });
        this.btnGuardar.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                this.guardarUsuario();
            } else {
                event.consume();
            }
        });
        this.btnCancelar.setOnMouseClicked((event) -> {
        });

    }

}
