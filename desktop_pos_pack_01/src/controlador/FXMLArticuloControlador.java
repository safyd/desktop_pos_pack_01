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
import static javafx.application.Application.launch;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
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
    private Label label;

    @FXML
    private CheckBox cbxEstadoDelArticulo;

    @FXML
    private TreeTableColumn<?, ?> colUnidadDeMedida, colCodigo, colPrecio, colDescripcion, oolProveedor, colCategoria, colNombre, colMargenUtilidad, colUnidadDeCosto,
            colCosto;

    @FXML
    private Button btnEliminar, btnCancelar, btnGuardar, btnImagen, btnNuevo;

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
//        this.cbxCategoria.getSelectionModel(). getSelectedItem();
              this.listacategoria = FXCollections.observableArrayList();
        
        this.eventoBoton();

    }

    private Boolean validarCampos() {
        if (this.categoria == null
                || this.txtCosto.getText().equals("")
                || this.txtUnidadDeCompra.getText().equals("")
                || this.txtMargenUtilidad.getText().equals("")
                || this.txtUnidadMedida.getText().equals("")
                || this.txtProveedor.getText().equals("")
                || this.txtDescripcion.getText().equals("")
                || this.txtNombre.getText().equals("")
                || this.txtPrecio.getText().equals("")
                || this.cbxEstadoDelArticulo.isSelected()) {
           
        
        }
        {
            return false;
        }
    
    }

    protected void PopupCategoria() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(uri.POPUP_CATEGORIA));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Categoria");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            System.out.println("cargar popup");

        }
    }

    private void guardarArticulo() {
        if (this.validarCampos()) {
            articulo art = new articulo();
            //usu.setPer_id();
            //usu.setUsu_imagen();
            art.setArt_nombre(this.txtNombre.getText());
            art.setArt_unidad_medida(utils.Encriptar(this.txtUnidadDeCompra.getText()));
            art.setArt_codigo(this.txtCodigo.getText());
            art.setArt_proveedor(this.txtProveedor.getText());
            art.setArt_nombre(this.txtNombre.getText());
        } else {
            utils.mensaje("Faltan campos de llenar.", "Es necesario llenar los campos con asterisco", Alert.AlertType.ERROR);
        }
    }

    private void eventoBoton() {
        this.btnGuardar.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 1) {
                this.guardarArticulo();
            } else {
                event.consume();
            }
        });

        this.btnGuardar.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                this.guardarArticulo();
            } else {
                event.consume();
            }
        });
        this.btnCancelar.setOnMouseClicked((event) -> {
            this.PopupCategoria();
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
        if (cat.get().size() <= 0) {      
        } else {
            this.listacategoria.clear();
            cat.get().forEach((Categoria) -> {
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

    private void accionarEvento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
