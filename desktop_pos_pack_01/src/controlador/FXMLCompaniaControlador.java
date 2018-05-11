package controlador;

import entidad.Compania;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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

public class FXMLCompaniaControlador extends Application implements Initializable {
    
    @FXML
    private TextField txtNombre, txtImagen, txtDireccion, txtTelefono, txtNumeroExterior, txtCodigoPostal, txtEstado, txtCiudad, txtNumeroInterior;
    @FXML
    private ImageView imgImagen;
    @FXML
    private Button btnGuardar, btnEditar, btnImagen, btnCancelar;
    Compania compania = null;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(uri.COMPANIA));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLCompaniaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.accionarEvento();
        this.obtenerCompania();
        this.valirdarCamposTipo();
        
    }
    
    protected void accionarEvento() {
        this.btnGuardar.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 1) {
                this.guardarCompania();
                
            } else {
                event.consume();
            }
        });
        this.btnGuardar.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                this.guardarCompania();
                
            } else {
                event.consume();
            }
        });
        this.btnEditar.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 1) {
                this.editarCompania();
            } else {
                event.consume();
            }
            
        });
        this.btnEditar.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                this.editarCompania();
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
        this.btnCancelar.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                this.recargarValores();
            } else {
                event.consume();
            }
        });
        this.btnCancelar.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 1) {
                this.recargarValores();
            } else {
                event.consume();
            }
        });
        
    }
    
    protected void recargarValores() {
        this.txtCiudad.setText("");
        this.txtCodigoPostal.setText("");
        this.txtDireccion.setText("");
        this.txtEstado.setText("");
        this.txtImagen.setText("");
        this.txtNombre.setText("");
        this.txtNumeroExterior.setText("");
        this.txtNumeroInterior.setText("");
        this.txtTelefono.setText("");
        this.obtenerCompania();
    }
    
    protected void editarCompania() {
        this.txtNombre.setEditable(true);
        this.txtImagen.setEditable(true);
        this.txtDireccion.setEditable(true);
        this.txtNumeroExterior.setEditable(true);
        this.txtCodigoPostal.setEditable(true);
        this.txtEstado.setEditable(true);
        this.txtCiudad.setEditable(true);
        this.txtNumeroInterior.setEditable(true);
        this.txtTelefono.setEditable(true);
        this.txtNombre.requestFocus();
    }
    
    protected Boolean validarCamposVacios() {
        return !(this.txtNombre.getText().equals("")
                || this.txtDireccion.getText().equals("")
                || this.txtCodigoPostal.getText().equals("")
                || this.txtNumeroExterior.getText().equals("")
                || this.txtNumeroInterior.getText().equals("")
                || this.txtEstado.getText().equals("")
                || this.txtCiudad.getText().equals(""));
    }
    
    protected void valirdarCamposTipo() {
        utils.validarNumeros(this.txtNumeroExterior);
        utils.validarNumeros(this.txtNumeroInterior);
        utils.validarNumeros(this.txtCodigoPostal);
        
    }
    
    protected void guardarCompania() {
        if (this.obtenerCompania()) {
            if (this.validarCamposVacios()) {
                this.compania = new Compania();
                this.compania.setCom_nombre(this.txtNombre.getText());
                if (!this.txtImagen.getText().equals("")) {
                    if (utils.convertirImagen(this.txtImagen.getText()) != null) {
                        this.compania.setCom_imagen(utils.convertirImagen(this.txtImagen.getText()));
                    }
                } else {
                    this.compania.setCom_imagen(null);
                }
                this.compania.setCom_telefono(this.txtTelefono.getText());
                this.compania.setCom_direccion(this.txtDireccion.getText());
                this.compania.setCom_no_exterior(Integer.parseInt(this.txtNumeroExterior.getText()));
                this.compania.setCom_no_interior(Integer.parseInt(this.txtNumeroInterior.getText()));
                this.compania.setCom_cp(this.txtCodigoPostal.getText());
                this.compania.setCom_estado(this.txtEstado.getText());
                this.compania.setCom_ciudad(this.txtCiudad.getText());
                if (this.compania.insert()) {
                    utils.mensaje("Registro exitoso.", "La compañía ha sido registrada con exito.", Alert.AlertType.CONFIRMATION);
                } else {
                    utils.mensaje("Error de registro.", "LA compañía no se registro correctamente.", Alert.AlertType.ERROR);
                }
            } else {
                utils.mensaje("Faltan campos de llenar.", "Es necesario llenar los campos con asterisco", Alert.AlertType.INFORMATION);
            }
            
        } else {
            if (this.validarCamposVacios()) {
                this.compania.setCom_id(this.compania.getCom_id());
                this.compania.setCom_nombre(this.txtNombre.getText());
                if (!this.txtImagen.getText().equals("")) {
                    if (utils.convertirImagen(this.txtImagen.getText()) != null) {
                        this.compania.setCom_imagen(utils.convertirImagen(this.txtImagen.getText()));
                    }
                } else {
                    this.compania.setCom_imagen(null);
                }
                this.compania.setCom_telefono(this.txtTelefono.getText());
                this.compania.setCom_direccion(this.txtDireccion.getText());
                this.compania.setCom_no_exterior(Integer.parseInt(this.txtNumeroExterior.getText()));
                this.compania.setCom_no_interior(Integer.parseInt(this.txtNumeroInterior.getText()));
                this.compania.setCom_cp(this.txtCodigoPostal.getText());
                this.compania.setCom_estado(this.txtEstado.getText());
                this.compania.setCom_ciudad(this.txtCiudad.getText());
                if (this.compania.update()) {
                    utils.mensaje("Modificación exitosa.", "La compañía se modificó correctamente.", Alert.AlertType.CONFIRMATION);
                } else {
                    utils.mensaje("Error de Modificación.", "La compañía no se modificó correctamente.", Alert.AlertType.ERROR);
                }
            } else {
                utils.mensaje("Faltan campos de llenar.", "Es necesario llenar los campos con asterisco", Alert.AlertType.INFORMATION);
            }
        }
        
    }
    
    protected Boolean obtenerCompania() {
        ArrayList<Compania> listCom = new Compania().obtenerTodos();
        if (listCom.isEmpty()) {
            return true;
        } else {
            listCom.forEach((com) -> {
                this.compania = com;
                this.imgImagen.setImage(utils.obtenerImagen(com.getCom_imagen()));
                this.txtCiudad.setText(com.getCom_ciudad());
                this.txtCodigoPostal.setText(com.getCom_cp());
                this.txtDireccion.setText(com.getCom_direccion());
                this.txtEstado.setText(com.getCom_estado());
                this.txtImagen.setText("");
                this.txtTelefono.setText(com.getCom_telefono());
                this.txtNombre.setText(com.getCom_nombre());
                this.txtNumeroExterior.setText(com.getCom_no_exterior().toString());
                this.txtNumeroInterior.setText(com.getCom_no_interior().toString());
            });
            return false;
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
    
}
