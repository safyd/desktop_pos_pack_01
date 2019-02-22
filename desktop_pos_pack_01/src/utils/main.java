/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import controlador.FXMLAccesoControlador;
import controlador.FXMLMenuControlador;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author layo
 */
public class main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //FXMLAccesoControlador acceso = new FXMLAccesoControlador();
        //acceso.ver();
        FXMLMenuControlador menu = new FXMLMenuControlador();
        menu.ver();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
