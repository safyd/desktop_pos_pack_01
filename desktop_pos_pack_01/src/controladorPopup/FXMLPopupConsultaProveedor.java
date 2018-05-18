package controladorPopup;

import Modelo.ModeloProveedor;
import entidad.proveedor;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.uri;

public class FXMLPopupConsultaProveedor extends Application implements Initializable {

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
    private TableView<ModeloProveedor> tblProveedor;

    private ObservableList<ModeloProveedor> listaProveedor;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.cbxTamano.getItems().addAll(10, 50, 100, 150, 200);
        this.listaProveedor = FXCollections.observableArrayList();
        this.llenarTabla();
        this.controladorTabla();

    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(uri.POPUPPROVEEDOR));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    protected void llenarTabla() {
        proveedor pro = new proveedor();
        pro.obtenerTodos().forEach((t) -> {
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
                    System.out.println("true");
                    return true;
                } else if (ModeloProveedor.getPro_codigo().toLowerCase().contains(lowerCaseFilter)) {
                    System.out.println("true");
                    return true;
                } else if (ModeloProveedor.getPro_descipcion().toLowerCase().contains(lowerCaseFilter)) {
                    System.out.println("true");
                    return true;
                } else if (ModeloProveedor.getPro_nombre().toLowerCase().contains(lowerCaseFilter)) {
                    System.out.println("true");
                    return true;
                }
                System.out.println("false");
                return false;
            });
        });
    }

}
