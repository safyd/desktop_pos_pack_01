package Modelo;

import entidad.Proveedor;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ModeloProveedor {

    private Proveedor proveedor;
    private LongProperty pro_id;
    private StringProperty pro_clave, pro_descipcion, pro_codigo, pro_nombre;

    public ModeloProveedor(Proveedor proveedor, Long pro_id, String pro_clave, String pro_descipcion, String pro_codigo, String pro_nombre) {
        this.proveedor = proveedor;
        this.pro_id = new SimpleLongProperty(pro_id);
        this.pro_clave = new SimpleStringProperty(pro_clave);
        this.pro_descipcion = new SimpleStringProperty(pro_descipcion);
        this.pro_codigo = new SimpleStringProperty(pro_codigo);
        this.pro_nombre = new SimpleStringProperty(pro_nombre);
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Long getPro_id() {
        return pro_id.get();
    }

    public void setPro_id(LongProperty pro_id) {
        this.pro_id = pro_id;
    }

    public String getPro_clave() {
        return pro_clave.get();
    }

    public void setPro_clave(StringProperty pro_clave) {
        this.pro_clave = pro_clave;
    }

    public String getPro_descipcion() {
        return pro_descipcion.get();
    }

    public void setPro_descipcion(StringProperty pro_descipcion) {
        this.pro_descipcion = pro_descipcion;
    }

    public String getPro_codigo() {
        return pro_codigo.get();
    }

    public void setPro_codigo(StringProperty pro_codigo) {
        this.pro_codigo = pro_codigo;
    }

    public String getPro_nombre() {
        return pro_nombre.get();
    }

    public void setPro_nombre(StringProperty pro_nombre) {
        this.pro_nombre = pro_nombre;
    }

    public LongProperty getPro_idProperty() {
        return pro_id;
    }

    public StringProperty getPro_claveProperty() {
        return pro_clave;
    }

    public StringProperty getPro_descipcionProperty() {
        return pro_descipcion;
    }

    public StringProperty getPro_codigoProperty() {
        return pro_codigo;
    }

    public StringProperty getPro_nombreProperty() {
        return pro_nombre;
    }

}
