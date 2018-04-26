package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

public class utils {

    public static void mensaje(String mensajeHed, String mensajeDia, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(mensajeHed);
        alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(mensajeDia)));
        alert.showAndWait();
    }
}
