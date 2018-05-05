package controlador;

import entidad.proveedor;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import utils.uri;
import utils.utils;
import javafx.stage.Stage;

public class FXMLProveedorControlador extends Application implements Initializable {

    @FXML
    private Button btnGuardar, btnCancelar, btnNuevo;

    @FXML
    private TextField txtClave, txtDescripcion, txtUnidadDeCompra, txtCodigo, txtCosto;

    @FXML
    private TreeTableColumn<?, ?> colCosto, colEliminar, colDescripcion, colClave, colEditar, colUnidadDeCompra, colCodigo;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(uri.PROVEEDOR));

        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.accionarEvento();
    }

    protected void accionarEvento() {
        this.btnGuardar.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                this.guardarProveedor();
                this.limpiarCampos();
            } else {
                event.consume();
            }
        });
         this.btnGuardar.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 1) {
                this.guardarProveedor();
                this.limpiarCampos();
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
         
         
    }

    protected Boolean validarCampos() {
        return !(this.txtDescripcion.getText().equals("")
                || this.txtCodigo.getText().equals("")
                || this.txtCosto.getText().equals("")
                || this.txtUnidadDeCompra.getText().equals("")
                || this.txtClave.getText().equals(""));

    }

    protected void limpiarCampos() {
        this.txtCodigo.setText("");
        this.txtDescripcion.setText("");
        this.txtCosto.setText("");
        this.txtUnidadDeCompra.setText("");
        this.txtClave.setText("");

    }

    protected void guardarProveedor() {
        if (this.validarCampos()) {
            proveedor pro = new proveedor();
            pro.setPro_codigo(txtCodigo.getText());
            pro.setPro_descripcion(txtDescripcion.getText());
            pro.setPro_costo(Double.parseDouble(txtCosto.getText()));
            pro.setPro_unidad_compra(txtUnidadDeCompra.getText());
            pro.setPro_clave(txtClave.getText());
            if (pro.insert()) {
                utils.mensaje("Registro exitoso.", "El proveedor ha sido registrado con exito.", Alert.AlertType.CONFIRMATION);
            } else {
                utils.mensaje("Error de registro.", "El proveedor no se registro correctamente.", Alert.AlertType.ERROR);
            }
        } else {
            utils.mensaje("Faltan campos de llenar.", "Es necesario llenar los campos con asterisco", Alert.AlertType.INFORMATION);
        }
    }
}
