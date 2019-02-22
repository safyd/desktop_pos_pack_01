package controlador;

import entidad.Categoria;
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
import javafx.stage.Stage;
import utils.uri;
import utils.utils;

public class FXMLCategoriaControlador extends Application implements Initializable {

    @FXML
    private TreeTableColumn<?, ?> colClave, colEditar, colEliminar, colNombre;

    @FXML
    private Button btnCancelar, btnGuardar;

    @FXML
    private TextField txtClave, txtNombre;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(uri.CATEGORIA));

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
                this.guardarCategoria();
                this.limpiarCampos();
            } else {
                event.consume();
            }
        });
        
      
    }
    
    
    

    protected void limpiarCampos() {
        this.txtClave.setText("");
        this.txtNombre.setText("");
    }

    protected Boolean validarCampos() {
        return !(this.txtNombre.getText().equals("")
                || this.txtClave.getText().equals(""));
    }

    protected void guardarCategoria() {

        if (this.validarCampos()) {

            Categoria cat = new Categoria();
            cat.setCat_descripcion(txtNombre.getText());
            cat.setCat_clave(txtClave.getText());

            if (cat.insert()) {
                utils.mensaje("Registro exitoso.", "La categoría ha sido registrado con exito.", Alert.AlertType.CONFIRMATION);
            } else {
                utils.mensaje("Error de registro.", "La categoría no se registro correctamente.", Alert.AlertType.ERROR);
            }

        } else {
                        utils.mensaje("Faltan campos de llenar.", "Es necesario llenar los campos con asterisco", Alert.AlertType.INFORMATION);
        }

    }

}
