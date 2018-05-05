package controlador;

import entidad.Perfil;
import entidad.Usuario;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
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
    private ComboBox<Perfil> cbxPerfil;
    @FXML
    private PasswordField txtContrasena, txtConfirmarContrasena;
    @FXML
    private Button btnNuevo, btnImagen, btnGuardar, btnCancelar;
    @FXML
    private CheckBox ckbVerContrasena, ckbVerConfirmaContrasena;
    @FXML
    private Label lblMensajeContrasena;

    private ObservableList<Perfil> listaPerfil;
    private Perfil perfil = null;

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
        this.txtImagen.setEditable(false);
        this.listaPerfil = FXCollections.observableArrayList();
        this.cbxSexo.getItems().addAll("Hombre", "Mujer");
        this.accionarEvento();
        this.llenarComboBox();

    }

    protected Boolean validarCampos() {
        return !(this.perfil == null || this.txtUsuario.getText().equals("") || this.txtNombre.getText().equals(""));
    }

    protected void guardarUsuario() {
        if (this.validarCampos()) {
            if (this.confirmarContrasena(txtContrasena, txtConfirmarContrasena)) {
                Usuario usu = new Usuario();
                usu.setPer_id(this.perfil.getPer_id());
                if (!this.txtImagen.getText().equals("")) {
                    if (utils.convertirImagen(this.txtImagen.getText()) != null) {
                        usu.setUsu_imagen(utils.convertirImagen(this.txtImagen.getText()));
                    }
                } else {
                    usu.setUsu_imagen(null);
                }

                usu.setUsu_usuario(this.txtUsuario.getText());
                usu.setUsu_contrasena(utils.Encriptar(this.txtContrasena.getText()));
                usu.setUsu_nombre(this.txtNombre.getText());
                usu.setUsu_apellido(this.txtApellido.getText());
                usu.setUsu_sexo(this.cbxSexo.getSelectionModel().getSelectedItem());
                if (usu.insert()) {
                    utils.mensaje("Registro exitoso.", "El usuario ha sido registrado con exito.", Alert.AlertType.CONFIRMATION);
                    this.limpiarCampos();
                } else {
                    utils.mensaje("Error de registro.", "El usuario no se registro correctamente.", Alert.AlertType.ERROR);
                }
            }
        } else {
            utils.mensaje("Faltan campos de llenar.", "Es necesario llenar los campos con asterisco", Alert.AlertType.INFORMATION);
        }

    }

    protected void accionarEvento() {
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
            if (event.getClickCount() == 1) {
                this.limpiarCampos();
            } else {
                event.consume();
            }

        });
        this.btnCancelar.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                this.limpiarCampos();
            } else {
                event.consume();
            }
        });
        this.btnNuevo.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 1) {
                this.limpiarCampos();
            } else {
                event.consume();
            }
        });
        this.btnNuevo.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                this.limpiarCampos();
            } else {
                event.consume();
            }
        });
        this.btnImagen.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 1) {
                this.obtenerImagen();
            } else {
                event.consume();
            }

        });
        this.btnImagen.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                this.obtenerImagen();
            } else {
                event.consume();
            }
        });
        //CHECKBOX SELECCIONAR VER
        this.ckbVerContrasena.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (this.ckbVerContrasena.isSelected()) {
                this.ckbVerConfirmaContrasena.setSelected(false);
                this.lblMensajeContrasena.setText(this.txtContrasena.getText());
                this.lblMensajeContrasena.setTextFill(Color.rgb(0, 0, 0));
            } else {
                this.lblMensajeContrasena.setText("");
            }
        });
        this.ckbVerConfirmaContrasena.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (this.ckbVerConfirmaContrasena.isSelected()) {
                this.ckbVerContrasena.setSelected(false);
                this.lblMensajeContrasena.setText(this.txtConfirmarContrasena.getText());
                this.lblMensajeContrasena.setTextFill(Color.rgb(0, 0, 0));
            } else {
                this.lblMensajeContrasena.setText("");
            }
        });
    }

    private Boolean confirmarContrasena(PasswordField password, PasswordField rePassword) {
        if (!rePassword.getText().equals(password.getText())) {
            this.lblMensajeContrasena.setText("La contraseña no coincide.");
            this.lblMensajeContrasena.setTextFill(Color.rgb(255, 0, 0));

            return false;

        } else {
            this.lblMensajeContrasena.setText("Hecho.");
            this.lblMensajeContrasena.setTextFill(Color.rgb(76, 187, 23));
            return true;

        }

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
            utils.mensaje("Ha ocurrido un error", ex.toString(), Alert.AlertType.ERROR);
        }
    }

    protected void llenarComboBox() {
        Perfil per = new Perfil();
        if (per.obtenerTodos().size() <= 0) {
            utils.mensaje("No hay perfiles", "Es necesario agregar como minímo un perfil\nEn caso de no tenerlo le sera imposible agregar un usuario.", Alert.AlertType.ERROR);
        } else {
            this.listaPerfil.clear();
            per.obtenerTodos().forEach((perfiles) -> {
                this.listaPerfil.add(perfiles);
            });
            this.cbxPerfil.setItems(this.listaPerfil);
            this.cbxPerfil.valueProperty().addListener((ObservableValue<? extends Object> observable, Object oldValue, Object newValue) -> {
                if (this.cbxPerfil.getSelectionModel().getSelectedIndex() == -1) {
                } else {
                    this.perfil = listaPerfil.get(cbxPerfil.getSelectionModel().getSelectedIndex());
                }
            });
        }

    }

    protected void limpiarCampos() {
        this.cbxPerfil.getSelectionModel().select(-1);
        this.txtUsuario.setText("");
        this.txtNombre.setText("");
        this.txtApellido.setText("");
        this.cbxSexo.getSelectionModel().select(-1);
        this.txtContrasena.setText("");
        this.txtConfirmarContrasena.setText("");
        this.ckbVerConfirmaContrasena.setSelected(false);
        this.ckbVerContrasena.setSelected(false);
        this.txtImagen.setText("");
        this.imgImagen.setImage(new Image(FXMLUsuarioControlador.class.getResourceAsStream("/imagen/imagen.jpg")));
        this.cbxPerfil.requestFocus();
        //this.cbxPerfil.show();
    }

}
