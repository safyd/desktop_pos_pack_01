package controlador;

import entidad.usuario;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import utils.uri;
import utils.utils;

public class FXMLUsuarioControlador extends Application implements Initializable {

    @FXML
    private TextField txtImagen, txtNombre, txtApellido, txtUsuario;
    @FXML
    private ImageView imgImagen;
    @FXML
    private ComboBox<String> cbxSexo;
    @FXML
    private ComboBox<?> cbxPerfil;
    @FXML
    private PasswordField txtContrasena, txtConfirmarContrasena;
    @FXML
    private Button btnNuevo, btnImagen, btnGuardar, btnCancelar;
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
        this.cbxSexo.getItems().addAll("Hombre", "Mujer");
        this.eventoBoton();

    }

    protected Boolean validarCampos() {
        return !(this.txtUsuario.getText().equals("") || this.txtNombre.getText().equals(""));
    }

    protected void guardarUsuario() {
        if (this.validarCampos()) {
            usuario usu = new usuario();
            //usu.setPer_id();
            if (utils.convertirImagen(this.txtImagen.getText()) != null) {
                usu.setUsu_imagen(utils.convertirImagen(this.txtImagen.getText()));
            }
            usu.setUsu_usuario(this.txtUsuario.getText());
            usu.setUsu_contraseña(utils.Encriptar(this.txtContrasena.getText()));
            usu.setUsu_nombre(this.txtNombre.getText());
            usu.setUsu_apellido(this.txtApellido.getText());
            usu.setUsu_sexo(this.cbxSexo.getSelectionModel().getSelectedItem());
            if (usu.insert()) {
                utils.mensaje("Registro exitoso.", "El usuario ha sido registrado con exito.", Alert.AlertType.ERROR);
            } else {
                utils.mensaje("Error de registro.", "El usuario no se registro correctamente.", Alert.AlertType.ERROR);
            }
        } else {
            utils.mensaje("Faltan campos de llenar.", "Es necesario llenar los campos con asterisco", Alert.AlertType.ERROR);
        }
    }

    protected void eventoBoton() {
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
        this.btnImagen.setOnMouseClicked((event) -> {
            this.obtenerImagen();
        });
        this.btnImagen.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                this.obtenerImagen();
            } else {
                event.consume();
            }
        });

    }

    protected void obtenerImagen() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File file = fileChooser.showOpenDialog(null);
        try {
            if (file != null) {
                BufferedImage bufferedImage = ImageIO.read(file);
                this.txtImagen.setText(file.toString());
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                this.imgImagen.setImage(image);
            }
        } catch (IOException ex) {
            Logger.getLogger(FXMLUsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
