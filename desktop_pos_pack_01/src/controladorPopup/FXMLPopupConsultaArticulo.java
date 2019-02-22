package controladorPopup;

import Modelo.ModeloArticulo;
import Modelo.ModeloProveedor;
import controlador.FXMLArticuloControlador;
import controlador.FXMLCompraControlador;
import entidad.Articulo;
import entidad.Proveedor;
import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.uri;
import utils.utils;

public final class FXMLPopupConsultaArticulo {

    @FXML
    private AnchorPane anpPopup;

    @FXML
    private Label lblEstado;

    @FXML
    private TableView<ModeloArticulo> tblArticulo;

    @FXML
    private TableColumn<ModeloArticulo, String> colProveedor, colDescripcion, colCodigo, colCosto;

    @FXML
    private Button btnFiltro;

    @FXML
    private Pagination pgnPaginas;

    @FXML
    private TextField txtFiltro;

    @FXML
    private ComboBox<Integer> cbxTamano;

    private final ObservableList<ModeloArticulo> listaArticulos = FXCollections.observableArrayList();
    protected final Stage ecenario;
    private final FXMLCompraControlador fXMLCompraControlador;

    public FXMLPopupConsultaArticulo(FXMLCompraControlador fXMLCompraControlador) {
        this.ecenario = new Stage();
        this.fXMLCompraControlador = fXMLCompraControlador;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(uri.POPUPARTICULO));
            loader.setController(this);
            this.ecenario.initModality(Modality.APPLICATION_MODAL);
            this.ecenario.setScene(new Scene(loader.load()));
            this.cbxTamano.getItems().addAll(10, 50, 100, 150, 200);
            this.llenarTabla();
            this.controladorTabla();
        } catch (IOException ex) {
            Logger.getLogger(FXMLPopupConsultaArticulo.class.getName()).log(Level.SEVERE, null, ex);
            utils.mensaje("Error de popup", "Hay un error al cargar el popup\n" + ex.toString(), Alert.AlertType.ERROR);
        }
    }

    protected void llenarTabla() {
        new Articulo().obtenerTodos().forEach((art) -> {
            this.listaArticulos.add(new ModeloArticulo(art.getArt_id(), art.getArt_proveedor(), art.getArt_descripcion(), art.getArt_codigo(), String.valueOf(art.getArt_nuevo_costo())));
        });

    }

    protected void controladorTabla() {
        this.colDescripcion.setCellValueFactory(cellData -> cellData.getValue().getColCodigoProperty());
        this.colCodigo.setCellValueFactory(cellData -> cellData.getValue().getColCodigoProperty());
        this.colProveedor.setCellValueFactory(cellData -> cellData.getValue().getColProveedorProperty());
        this.colCosto.setCellValueFactory(cellData -> cellData.getValue().getColCostoProperty());
        FilteredList<ModeloArticulo> filtrarInformacion = new FilteredList<>(this.listaArticulos, event -> true);
        this.txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {
            filtrarInformacion.setPredicate(ModeloProveedor -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (ModeloProveedor.getColCodigo().contains(lowerCaseFilter)) {
                    return true;
                } else if (ModeloProveedor.getColDescripcion().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (ModeloProveedor.getColProveedor().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<ModeloArticulo> sortedData = new SortedList<>(filtrarInformacion);
        sortedData.comparatorProperty().bind(this.tblArticulo.comparatorProperty());
        this.tblArticulo.setItems(sortedData);
        this.tblArticulo.setOnMouseClicked((MouseEvent e) -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                if (e.getClickCount() == 2) {
                    // this.fXMLArticuloControlador.obtenerProveedor(this.tblProveedor.getSelectionModel().getSelectedItem().getProveedor());
                    this.cerrar();
                }
            }
        });

    }


    public void ver() {
        this.ecenario.showAndWait();
    }

    public void cerrar() {
        this.ecenario.close();
    }

}
