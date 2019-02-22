package Modelo;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ModeloDetalleCompra {

    private LongProperty art_id, com_id;
    private DoubleProperty colPrecioUnitario, colPrecioTotal, colCantidad, colDescuento, colSubTotal, colTotal;
    private StringProperty colCodigo, colDescripcion;

    public ModeloDetalleCompra(Long art_id, Long com_id, Double colPrecioUnitario, Double colPrecioTotal, Double colCantidad, Double colDescuento, Double colSubTotal, Double colTotal, String colCodigo, String colDescripcion) {
        this.art_id = new SimpleLongProperty(art_id);
        this.com_id = new SimpleLongProperty(com_id);
        this.colPrecioUnitario = new SimpleDoubleProperty(colPrecioUnitario);
        this.colPrecioTotal = new SimpleDoubleProperty(colPrecioTotal);
        this.colCantidad = new SimpleDoubleProperty(colCantidad);
        this.colDescuento = new SimpleDoubleProperty(colDescuento);
        this.colSubTotal = new SimpleDoubleProperty(colSubTotal);
        this.colTotal = new SimpleDoubleProperty(colTotal);
        this.colCodigo = new SimpleStringProperty(colCodigo);
        this.colDescripcion = new SimpleStringProperty(colDescripcion);
    }

    public LongProperty getArt_idProperty() {
        return art_id;
    }

    public LongProperty getCom_idProperty() {
        return com_id;
    }

    public DoubleProperty getColPrecioUnitarioProperty() {
        return colPrecioUnitario;
    }

    public DoubleProperty getColPrecioTotalProperty() {
        return colPrecioTotal;
    }

    public DoubleProperty getColCantidadProperty() {
        return colCantidad;
    }

    public DoubleProperty getColDescuentoProperty() {
        return colDescuento;
    }

    public DoubleProperty getColSubTotalProperty() {
        return colSubTotal;
    }

    public DoubleProperty getColTotalProperty() {
        return colTotal;
    }

    public StringProperty getColCodigoProperty() {
        return colCodigo;
    }

    public StringProperty getColDescripcionProperty() {
        return colDescripcion;
    }

    public Long getArt_id() {
        return art_id.get();
    }

    public Long getCom_id() {
        return com_id.get();
    }

    public Double getColPrecioUnitario() {
        return colPrecioUnitario.get();
    }

    public Double getColPrecioTotal() {
        return colPrecioTotal.get();
    }

    public Double getColCantidad() {
        return colCantidad.get();
    }

    public Double getColDescuento() {
        return colDescuento.get();
    }

    public Double getColSubTotal() {
        return colSubTotal.get();
    }

    public Double getColTotal() {
        return colTotal.get();
    }

    public String getColCodigo() {
        return colCodigo.get();
    }

    public String getColDescripcion() {
        return colDescripcion.get();
    }

    public void setArt_id(LongProperty art_id) {
        this.art_id = art_id;
    }

    public void setCom_id(LongProperty com_id) {
        this.com_id = com_id;
    }

    public void setColPrecioUnitario(DoubleProperty colPrecioUnitario) {
        this.colPrecioUnitario = colPrecioUnitario;
    }

    public void setColPrecioTotal(DoubleProperty colPrecioTotal) {
        this.colPrecioTotal = colPrecioTotal;
    }

    public void setColCantidad(DoubleProperty colCantidad) {
        this.colCantidad = colCantidad;
    }

    public void setColDescuento(DoubleProperty colDescuento) {
        this.colDescuento = colDescuento;
    }

    public void setColSubTotal(DoubleProperty colSubTotal) {
        this.colSubTotal = colSubTotal;
    }

    public void setColTotal(DoubleProperty colTotal) {
        this.colTotal = colTotal;
    }

    public void setColCodigo(StringProperty colCodigo) {
        this.colCodigo = colCodigo;
    }

    public void setColDescripcion(StringProperty colDescripcion) {
        this.colDescripcion = colDescripcion;
    }
    

}
