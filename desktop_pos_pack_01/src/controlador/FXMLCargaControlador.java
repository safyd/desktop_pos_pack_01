/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import utils.uri;

/**
 *
 * @author layo
 */
public class FXMLCargaControlador {

    @FXML
    private ProgressBar prsBarCarga;
    private final Stage ecenario;

    public FXMLCargaControlador() {
        this.ecenario = new Stage();
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(uri.CARGA));
            loader.setController(this);
            this.ecenario.setScene(new Scene(loader.load()));
            this.prsBarCarga.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
        } catch (IOException ex) {
            Logger.getLogger(FXMLCargaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ver() {
        this.ecenario.show();
    }

    public void cerrar() {
        this.ecenario.close();
    }

}
