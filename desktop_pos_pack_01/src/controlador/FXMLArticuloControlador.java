package controlador;

import controladorPopup.FXMLPopupConsultaProveedor;
import entidad.Proveedor;
import entidad.Articulo;
import entidad.Categoria;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import utils.uri;
import utils.utils;

public final class FXMLArticuloControlador {

    @FXML
    private ImageView imgImagen;

    @FXML
    private ComboBox<Categoria> cbxCategoria;

    @FXML
    private CheckBox ckbEstadoDelArticulo;

    @FXML
    private Button btnEliminar, btnGuardar, btnImagen, btnNuevo, btnProveedor;

    @FXML
    private TextField txtCosto, txtCodigo, txtUnidadDeCompra, txtMargenUtilidad, txtUnidadMedida, txtProveedor, txtDescripcion, txtNombre, txtPrecio, txtImagen;

    private ObservableList<Categoria> listacategoria;
    private Categoria categoria;
    public Proveedor proveedor;
    private Stage ecenario;
    private FXMLCargaControlador fXMLCargaControlador;

    public FXMLArticuloControlador(FXMLCargaControlador fXMLCargaControlador) {
        try {
            this.fXMLCargaControlador = fXMLCargaControlador;
            this.fXMLCargaControlador.ver();
            this.ecenario = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(uri.ARTICULO));
            loader.setController(this);
            this.ecenario.setScene(new Scene(loader.load()));
            this.txtImagen.setEditable(false);
            this.imgImagen.setImage(new Image(FXMLUsuarioControlador.class.getResourceAsStream("/imagen/imagen.jpg")));
            this.cbxCategoria.getSelectionModel().getSelectedItem();
            this.listacategoria = FXCollections.observableArrayList();
            this.eventoBoton();
            this.llenarComboBox();
            this.configurarCampos();
            this.fXMLCargaControlador.cerrar();
        } catch (IOException ex) {
            Logger.getLogger(FXMLArticuloControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ver(FXMLCargaControlador fXMLCargaControlador) {
        this.ecenario.show();
    }

    public void obtenerProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
        this.txtProveedor.setText(proveedor.getPro_nombre());
    }

    protected Boolean validarCampos() {
        return !(this.proveedor == null
                //|| this.categoria == null
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

    protected void configurarCampos() {
        utils.validarNumerosDecimal(this.txtCosto);

//                txtCodigo
//                txtUnidadDeCompra
//                        txtMargenUtilidad
//                        txtUnidadMedida
//                                txtProveedor
//                                txtDescripcion
//                                        txtNombre
//                                        txtPrecio;
    }

    protected void popupProveedor() {
        FXMLPopupConsultaProveedor popupProveedor = new FXMLPopupConsultaProveedor(this);
        popupProveedor.ver();

    }

    protected void guardarArticulo() {
        if (this.validarCampos()) {
            Articulo art = new Articulo();
            art.setCat_id(this.categoria.getCat_id());
            art.setArt_nombre(this.txtNombre.getText());
            art.setArt_unidad_medida(this.txtUnidadMedida.getText());
            //art.setArt_unidad_compra(this.txtUnidadDeCompra.getText());
            art.setArt_imagen(this.txtImagen.getText());
            art.setArt_codigo(this.txtCodigo.getText());
            art.setArt_precio(Double.parseDouble(this.txtPrecio.getText()));
            art.setArt_descripcion(this.txtDescripcion.getText());
            art.setArt_nuevo_costo(Double.parseDouble(this.txtCosto.getText()));
            art.setArt_ultimo_costo(Double.parseDouble(this.txtCosto.getText()));
            art.setArt_nueva_existencia(0.0);
            art.setArt_ultima_existencia(0.0);
            art.setArt_activo(utils.esSeleccionado(this.ckbEstadoDelArticulo));
            //-------------------------------------------------------//
            art.setFechaCreacion(utils.obtenerFechaTimeStamp());
            art.setFechaModificacion(utils.obtenerFechaTimeStamp());
            art.setUsuarioCreacion("Ejemplo");
            art.setUsuarioModificacion("Ejemplo");
            //-------------------------------------------------------//
            art.setArt_proveedor(this.txtProveedor.getText());

            if (art.insert()) {
                utils.mensaje("Registro exitoso.", "El articulo ha sido registrado con exito.", Alert.AlertType.CONFIRMATION);
                this.limpiarCampos();
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
        this.categoria = null;
        this.proveedor = null;
        this.txtImagen.setText("");
        utils.obtenerImagenEstandar(this.imgImagen);

    }

    protected void eventoBoton() {
        this.btnGuardar.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 1) {
                this.guardarArticulo();

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
        this.btnEliminar.setOnMouseClicked((event) -> {

        });

    }

    private void obtenerImagen() {
        utils.obtenerImagenConDireccion(this.txtImagen, this.imgImagen);
    }

    protected void llenarComboBox() {
        this.listacategoria.clear();
        new Categoria().obtenerTodos().forEach((Categoria) -> {
            this.listacategoria.add(Categoria);
        });
        this.cbxCategoria.setItems(this.listacategoria);
        this.cbxCategoria.valueProperty().addListener((ObservableValue<? extends Object> observable, Object oldValue, Object newValue) -> {
            if (this.cbxCategoria.getSelectionModel().getSelectedIndex() == -1) {
            } else {
                this.categoria = this.listacategoria.get(this.cbxCategoria.getSelectionModel().getSelectedIndex());
            }
        });
    }

}
