package controlador;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.uri;

public final class FXMLAccesoControlador {

    @FXML
    protected Button btnConfiguracion, btnEntrar, btnSoporte, btnTema, btnSalir, btnScreen;

    @FXML
    protected AnchorPane panelPrincipal;

    @FXML
    protected TextField txtContrasena, txtUsuario;

    protected Stage ecenario;

    public FXMLAccesoControlador() {
        try {
            this.ecenario = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(uri.ACCESO));
            loader.setController(this);
            this.ecenario.setScene(new Scene(loader.load()));
            this.ecenario.setFullScreen(true);
            this.accionEvento();
            this.panelPrincipal.setStyle("-fx-background-image: url(\"/imagen/fondo01.jpg\");" + "-fx-background-size: cover;");
        } catch (IOException ex) {
            Logger.getLogger(FXMLAccesoControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void accionEvento() {
        this.btnScreen.setOnMouseClicked((event) -> {
            if (this.ecenario.isFullScreen()) {
                this.ecenario.setFullScreen(false);
            } else {
                this.ecenario.setFullScreen(true);
            }

        });
        this.btnSalir.setOnMouseClicked((event) -> {
            this.ecenario.close();
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

    public void ver() {
        this.ecenario.showAndWait();
    }

}
