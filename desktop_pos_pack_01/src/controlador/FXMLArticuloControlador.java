package controlador;

import entidad.articulo;
import entidad.categoria;

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

public class FXMLArticuloControlador extends Application implements Initializable {

    @FXML
    private ImageView imgImagen;

    @FXML
    private ComboBox<categoria> cbxCategoria;

    @FXML
    private CheckBox ckbEstadoDelArticulo;

    @FXML
    private Button btnEliminar, btnGuardar, btnImagen, btnNuevo, btnProveedor;

    @FXML
    private TextField txtCosto, txtCodigo, txtUnidadDeCompra, txtMargenUtilidad, txtUnidadMedida, txtProveedor, txtDescripcion, txtNombre, txtPrecio, txtImagen;

    private ObservableList<categoria> listacategoria;
    private categoria categoria = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(uri.ARTICULO));

        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.txtImagen.setEditable(false);
        this.imgImagen.setImage(new Image(FXMLUsuarioControlador.class.getResourceAsStream("/imagen/imagen.jpg")));
        this.cbxCategoria.getSelectionModel().getSelectedItem();
        this.listacategoria = FXCollections.observableArrayList();
        this.eventoBoton();
        this.llenarComboBox();
        utils.crearArchivo();
     
    }

    private Boolean validarCampos() {
        return !(this.categoria == null
                || this.txtCodigo.getText().equals("")
                || this.txtNombre.getText().equals("")
                || this.txtDescripcion.getText().equals("")
                || this.txtProveedor.getText().equals("")
                || this.txtUnidadDeCompra.getText().equals("")
                || this.txtUnidadMedida.getText().equals("")
                || this.txtCosto.getText().equals("")
                || this.txtMargenUtilidad.getText().equals("")
                || this.txtPrecio.getText().equals(""));

    }

    protected void popupCategoria() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(uri.CATEGORIA));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            utils.mensaje("Error de popup", "Hay un error al cargar el popup\n" + ex.toString(), Alert.AlertType.ERROR);
        }
    }

    protected void popupProveedor() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(uri.POPUPPROVEEDOR));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            utils.mensaje("Error de popup", "Hay un error al cargar el popup\n" + ex.toString(), Alert.AlertType.ERROR);
        }
    }

    private void guardarArticulo() {
        if (this.validarCampos()) {

            articulo art = new articulo();
            art.setArt_codigo(this.txtCodigo.getText());
            art.setCat_id(this.categoria.getCat_id());
            art.setArt_imagen(utils.convertirImagen(this.txtImagen.getText()));
            art.setArt_nombre(this.txtNombre.getText());
            art.setArt_unidad_medida(utils.Encriptar(this.txtUnidadDeCompra.getText()));
            art.setArt_precio(Double.parseDouble(this.txtPrecio.getText()));
            art.setArt_nuevo_costo(Double.parseDouble(this.txtCosto.getText()));
            art.setArt_proveedor(this.txtProveedor.getText());
            art.setArt_nombre(this.txtNombre.getText());

            if (art.insert()) {
                utils.mensaje("Registro exitoso.", "El articulo ha sido registrado con exito.", Alert.AlertType.CONFIRMATION);
            } else {
                utils.mensaje("Error de registro.", "El articulo no se registro correctamente.", Alert.AlertType.ERROR);
            }

        } else {
            utils.mensaje("Faltan campos de llenar.", "Es necesario llenar los campos con asterisco", Alert.AlertType.ERROR);
        }
    }

    protected void limpiarCampos() {
        this.txtCodigo.setText("");
        this.txtNombre.setText("");
        this.txtDescripcion.setText("");
        this.txtProveedor.setText("");
        this.txtUnidadDeCompra.setText("");
        this.txtUnidadMedida.setText("");
        this.txtCosto.setText("");
        this.txtMargenUtilidad.setText("");
        this.txtPrecio.setText("");
        this.cbxCategoria.getSelectionModel().select(-1);
    }

    private void eventoBoton() {
        this.btnGuardar.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 1) {
                this.guardarArticulo();
                this.limpiarCampos();
            } else {
                event.consume();
            }
        });

     
        
          this.btnProveedor.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 1) {
                this.popupProveedor();
            
            } else {
                event.consume();
            }
        });
        
        this.btnGuardar.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                this.guardarArticulo();
                this.limpiarCampos();
            } else {
                event.consume();
            }
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
            BufferedImage bufferedImage = ImageIO.read(file);
            this.txtImagen.setText(file.toString());
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            this.imgImagen.setImage(image);
        } catch (IOException ex) {
            Logger.getLogger(FXMLArticuloControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void llenarComboBox() {
        categoria cat = new categoria();
        if (cat.obtenerTodos().size() <= 0) {
        } else {
            this.listacategoria.clear();
            cat.obtenerTodos().forEach((Categoria) -> {
                this.listacategoria.add(Categoria);
            });
            this.cbxCategoria.setItems(this.listacategoria);
            this.cbxCategoria.valueProperty().addListener((ObservableValue<? extends Object> observable, Object oldValue, Object newValue) -> {
                if (this.cbxCategoria.getSelectionModel().getSelectedIndex() == -1) {
                } else {
                    this.categoria = listacategoria.get(cbxCategoria.getSelectionModel().getSelectedIndex());
                }
            });
        }

    }

}
