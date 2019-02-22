/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Modelo.ModeloDetalleCompra;
import controladorPopup.FXMLPopupConsultaArticulo;
import entidad.Compra;
import entidad.Proveedor;
import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import utils.uri;

public final class FXMLCompraControlador {

    @FXML
    private TreeTableColumn<ModeloDetalleCompra, Double> colPrecioUnitario, colPrecioTotal, colCantidad, colDescuento, colSubTotal, colTotal;
    @FXML
    private TreeTableColumn<ModeloDetalleCompra, String> colCodigo, colDescripcion;
    @FXML
    private TreeTableView<ModeloDetalleCompra> tblCompra;
    @FXML
    private TextField txtNoCompra, txtProveedor;

    @FXML
    private DatePicker dtpFecha;

    @FXML
    private Button btnNuevoProveedor, btnGuardar, btnBuscarArticulo, btnCancelar;

    @FXML
    private TreeTableColumn<?, ?> colModificar;

    @FXML
    private TreeTableColumn<?, ?> colRemover;

    protected final Stage ecenario;
    protected FXMLPopupConsultaArticulo fXMLPopupConsultaArticulo = null;

    public FXMLCompraControlador() {
        this.ecenario = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(uri.COMPRA));
            loader.setController(this);
            this.ecenario.setScene(new Scene(loader.load()));
            this.accionarEvento();
            this.completarAutomatico();
        } catch (IOException ex) {
            Logger.getLogger(FXMLCompraControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void accionarEvento() {
        this.btnGuardar.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 1) {
                this.guardarCompra();
            } else {
                event.consume();
            }
        });
        this.btnGuardar.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                this.guardarCompra();
            } else {
                event.consume();
            }
        });
        this.btnBuscarArticulo.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 1) {
                if (this.fXMLPopupConsultaArticulo == null) {
                    this.fXMLPopupConsultaArticulo = new FXMLPopupConsultaArticulo(this);
                    this.fXMLPopupConsultaArticulo.ver();
                } else {
                    this.fXMLPopupConsultaArticulo.ver();
                }

            } else {
                event.consume();
            }
        });

    }

    public void ver() {
        this.ecenario.show();
    }

    public void completarAutomatico() {
        //Crear nueva entidad
        Proveedor pro = new Proveedor();
        //Agregar entidad a las posibles coincidencias
        Set<Proveedor> autoCompletions = new HashSet<>(pro.obtenerTodos());
        SuggestionProvider<Proveedor> provider = SuggestionProvider.create(autoCompletions);
        AutoCompletionTextFieldBinding<Proveedor> autoCompletionTextFieldBinding = new AutoCompletionTextFieldBinding<>(this.txtProveedor, provider);
        Set<Proveedor> filteredAutoCompletions = new HashSet<>(pro.obtenerTodos());
        provider.clearSuggestions();
       provider.addPossibleSuggestions(filteredAutoCompletions);
    }

    private void guardarDetalleCompra() {
    }

    private void guardarCompra() {
        Compra compra = new Compra();
        compra.setCom_id(0);
        compra.setUsu_id(0);
        compra.setCaj_id(0);
        compra.setPro_id(0);
        long folio = 0;
        compra.setCom_folio(folio);
        compra.setCom_cantidad(0.0);
        compra.setCom_costo(0.0);
        compra.setCom_descuento(0.0);
        compra.setCom_impuesto(0.0);
        Date d1 = new Date();
        compra.setFechaCreacion(d1);
        compra.setFechaModificacion(d1);
        compra.setUsuarioCreacion("");
        compra.setUsuarioModificacion("");
        compra.insert();
    }

}
