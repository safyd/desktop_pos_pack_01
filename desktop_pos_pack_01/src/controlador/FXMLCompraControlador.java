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
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 *
 * @author Usuario
 */
public class FXMLCompraControlador extends Application implements Initializable{

    
    
    
    @FXML
    void GuardarCompra()
    {
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
        Date d1 =new Date();
        compra.setFechaCreacion(d1);
        compra.setFechaModificacion(d1);
        compra.setUsuarioCreacion("");
        compra.setUsuarioModificacion("");
        compra.insert();
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
