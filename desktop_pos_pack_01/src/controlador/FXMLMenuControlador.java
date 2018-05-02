package controlador;

import com.jfoenix.controls.JFXDrawer;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import utils.uri;

public class FXMLMenuControlador extends Application implements Initializable {

    @FXML
    private JFXDrawer drwMenu;
    @FXML
    private AnchorPane panelPrincipal;
    @FXML
    private TitledPane tltPanelUsuario, tltPanelInventario, tltPanelCompra, tltPanelVenta;
    @FXML
    private ImageView btnMenu;
    @FXML
    private Button btnUsuario;
    @FXML
    private Circle cclIcono;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.controlTituloPanel();
        InputStream a = FXMLMenuControlador.class.getResourceAsStream("/imagen/imagen.jpg");
        //byte[] A = utils.convertirImagen(a);
        //ByteArrayInputStream bis = new ByteArrayInputStream(A);
        //BufferedImage bImage2 = ImageIO.read(bis);
        //Image card = SwingFXUtils.toFXImage(bImage2, null);
        //this.cclIcono.setFill(new ImagePattern(card));
        //BufferedImage img = ImageIO.read(new ByteArrayInputStream(bytes));

    }

    private void controlTituloPanel() {
        this.tltPanelUsuario.expandedProperty().addListener((observable) -> {
            new ChangeListener<TitledPane>() {
                @Override
                public void changed(ObservableValue<? extends TitledPane> observable, TitledPane oldValue, TitledPane newValue) {
                    if (newValue != null) {
                        tltPanelInventario.setExpanded(false);
                    }
                }
            };
        });

        this.tltPanelInventario.setExpanded(true);
        this.tltPanelCompra.setExpanded(false);
        this.tltPanelVenta.setExpanded(false);

    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(uri.MENU));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
