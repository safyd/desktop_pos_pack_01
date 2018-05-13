package controladorPopup;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.AnchorPane;

public class FXMLPopupConsultaProveedor implements Initializable {

    @FXML
    private AnchorPane anpPopup;

    @FXML
    private Label lblEstado;

    @FXML
    private Button btnFiltro;

    @FXML
    private TreeTableColumn<?, ?> colDescripcion, colCodigo, colClave, colNombre;

    @FXML
    private Pagination pgnPaginas;

    @FXML
    private TextField txtFiltro;

    @FXML
    private ComboBox<?> cbxTamano;

    @FXML
    private TreeTableView<?> tblProveedor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
