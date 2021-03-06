package controladorPopup;

import Modelo.ModeloProveedor;
import controlador.FXMLArticuloControlador;
import entidad.Proveedor;
import java.io.IOException;
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

public final class FXMLPopupConsultaProveedor {

    @FXML
    private AnchorPane anpPopup;

    @FXML
    private Label lblEstado;

    @FXML
    private Button btnFiltro;

    @FXML
    private TableColumn<ModeloProveedor, String> colDescripcion, colCodigo, colClave, colNombre;

    @FXML
    private Pagination pgnPaginas;

    @FXML
    private TextField txtFiltro;

    @FXML
    private ComboBox<Integer> cbxTamano;

    @FXML
    public TableView<ModeloProveedor> tblProveedor;

    private final ObservableList<ModeloProveedor> listaProveedor = FXCollections.observableArrayList();
    protected final Stage ecenario;
    private final FXMLArticuloControlador fXMLArticuloControlador;

    public FXMLPopupConsultaProveedor(FXMLArticuloControlador fXMLArticuloControlador) {
        this.ecenario = new Stage();
        this.fXMLArticuloControlador = fXMLArticuloControlador;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(uri.POPUPPROVEEDOR));
            loader.setController(this);
            this.ecenario.initModality(Modality.APPLICATION_MODAL);
            this.ecenario.setScene(new Scene(loader.load()));
            this.cbxTamano.getItems().addAll(10, 50, 100, 150, 200);
            this.llenarTabla();
            this.controladorTabla();
        } catch (IOException ex) {
            Logger.getLogger(FXMLPopupConsultaProveedor.class.getName()).log(Level.SEVERE, null, ex);
            utils.mensaje("Error de popup", "Hay un error al cargar el popup\n" + ex.toString(), Alert.AlertType.ERROR);
        }
    }

    protected void llenarTabla() {
        new Proveedor().obtenerTodos().forEach((pro) -> {
            this.listaProveedor.add(new ModeloProveedor(pro, pro.getPro_id(), pro.getPro_clave(), pro.getPro_descripcion(), pro.getPro_codigo(), pro.getPro_nombre()));
        });

    }

    protected void controladorTabla() {
        this.colDescripcion.setCellValueFactory(cellData -> cellData.getValue().getPro_descipcionProperty());
        this.colCodigo.setCellValueFactory(cellData -> cellData.getValue().getPro_codigoProperty());
        this.colClave.setCellValueFactory(cellData -> cellData.getValue().getPro_claveProperty());
        this.colNombre.setCellValueFactory(cellData -> cellData.getValue().getPro_nombreProperty());
        FilteredList<ModeloProveedor> filtrarInformacion = new FilteredList<>(listaProveedor, event -> true);
        this.txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {
            filtrarInformacion.setPredicate(ModeloProveedor -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (ModeloProveedor.getPro_clave().contains(lowerCaseFilter)) {
                    return true;
                } else if (ModeloProveedor.getPro_codigo().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (ModeloProveedor.getPro_descipcion().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (ModeloProveedor.getPro_nombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<ModeloProveedor> sortedData = new SortedList<>(filtrarInformacion);
        sortedData.comparatorProperty().bind(this.tblProveedor.comparatorProperty());
        this.tblProveedor.setItems(sortedData);
        this.tblProveedor.setOnMouseClicked((MouseEvent e) -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                if (e.getClickCount() == 2) {
                    this.fXMLArticuloControlador.obtenerProveedor(this.tblProveedor.getSelectionModel().getSelectedItem().getProveedor());
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
