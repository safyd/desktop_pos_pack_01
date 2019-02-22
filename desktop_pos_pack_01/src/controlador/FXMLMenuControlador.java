package controlador;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.uri;

public final class FXMLMenuControlador {

    @FXML
    private AnchorPane panelPrincipal;
    @FXML
    private ImageView imgInventario, imgArticulo, imgCompra;
    protected final Stage ecenario;
    FXMLArticuloControlador artControlador = null;
    FXMLCargaControlador carControlador = null;
    FXMLCompraControlador comControlador = null;

    //button.setGraphic ImageView.new(image("src/code/media/logo.png"))
    //img = image("src/code/media/logo.png") button_login.setGraphic ImageView.new(img);
    public FXMLMenuControlador() {
        this.ecenario = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(uri.MENU));
            loader.setController(this);
            this.ecenario.setScene(new Scene(loader.load()));
            this.accionEvento();
        } catch (IOException ex) {
            Logger.getLogger(FXMLMenuControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void accionEvento() {
        this.imgInventario.setOnMouseClicked((event) -> {
        });
        this.imgArticulo.setOnMouseClicked((event) -> {
            if (this.artControlador == null) {
                this.artControlador = new FXMLArticuloControlador(new FXMLCargaControlador());
                this.artControlador.ver(new FXMLCargaControlador());
            } else {
                this.artControlador.ver(new FXMLCargaControlador());
            }
        });
        this.imgCompra.setOnMouseClicked((event) -> {
            if (this.comControlador == null) {
                this.comControlador = new FXMLCompraControlador();
                this.comControlador.ver();
            } else {
                this.comControlador.ver();
            }
        });
    }

    public void ver() {
        this.ecenario.show();
    }

}
