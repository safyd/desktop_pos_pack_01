package Modelo;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ModeloArticulo {

    private LongProperty art_id;
    private StringProperty colProveedor, colDescripcion, colCodigo,colCosto;
    

    public ModeloArticulo(Long art_id, String colProveedor, String colDescripcion, String colCodigo, String colCosto) {
        this.art_id = new SimpleLongProperty(art_id);
        this.colProveedor = new SimpleStringProperty(colProveedor);
        this.colDescripcion = new SimpleStringProperty(colDescripcion);
        this.colCodigo = new SimpleStringProperty(colCodigo);
        this.colCosto = new SimpleStringProperty(colCosto);
    }

    public LongProperty getArt_idProperty() {
        return art_id;
    }

    public StringProperty getColProveedorProperty() {
        return colProveedor;
    }

    public StringProperty getColDescripcionProperty() {
        return colDescripcion;
    }

    public StringProperty getColCodigoProperty() {
        return colCodigo;
    }

    public StringProperty getColCostoProperty() {
        return colCosto;
    }

    public Long getArt_id() {
        return art_id.get();
    }

    public String getColProveedor() {
        return colProveedor.get();
    }

    public String getColDescripcion() {
        return colDescripcion.get();
    }

    public String getColCodigo() {
        return colCodigo.get();
    }

    public String getColCosto() {
        return colCosto.get();
    }

    public void setArt_id(LongProperty art_id) {
        this.art_id = art_id;
    }

    public void setColProveedor(StringProperty colProveedor) {
        this.colProveedor = colProveedor;
    }

    public void setColDescripcion(StringProperty colDescripcion) {
        this.colDescripcion = colDescripcion;
    }

    public void setColCodigo(StringProperty colCodigo) {
        this.colCodigo = colCodigo;
    }

    public void setColCosto(StringProperty colCosto) {
        this.colCosto = colCosto;
    }

}
