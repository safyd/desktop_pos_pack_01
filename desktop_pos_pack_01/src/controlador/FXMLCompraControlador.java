/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidad.Compra;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.uri;

public class FXMLCompraControlador extends Application implements Initializable {

    @FXML
    private Button btnGuardar;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(uri.COMPRA));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.accionarEvento();
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
