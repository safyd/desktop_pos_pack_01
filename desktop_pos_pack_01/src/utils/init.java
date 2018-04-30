package utils;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class init extends Application implements Initializable {

    @FXML
    private Button btnConfiguracion, btnEntrar, btnSoporte, btnTema, btnScreen;

    @FXML
    private AnchorPane panelPrincipal;

    @FXML
    private TextField txtContrasena, txtUsuario;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(uri.ACCESO));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);
    }

    protected void accionEvento() {
        this.btnScreen.setOnMouseClicked((event) -> {

        });
        this.btnEntrar.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                System.out.println(event.getCode());
            } else {
                event.consume();
            }
        });
        this.btnEntrar.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 1) {
                System.out.println(event.getClickCount());
            } else {
                event.consume();
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.accionEvento();
    }
}
