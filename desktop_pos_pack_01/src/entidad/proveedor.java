package entidad;

import Safyd.System.DataBase.ORM;

public class proveedor extends ORM<proveedor> {

    private Long pro_id;
    private String pro_descripcion;
    private String pro_codigo;
    private Double pro_costo;
    private String pro_unidad_compra;
    private String pro_clave;

    public proveedor() {
        super.setClass(this);
    }

    public proveedor(
            Long pro_id,
            String pro_descripcion,
            String pro_codigo,
            Double pro_costo,
            String pro_unidad_compra,
            String pro_clave
    ) {
        super.setClass(this);

        this.pro_id = pro_id;
        this.pro_descripcion = pro_descripcion;
        this.pro_codigo = pro_codigo;
        this.pro_costo = pro_costo;
        this.pro_unidad_compra = pro_unidad_compra;
        this.pro_clave = pro_clave;

    }

    public Long getPro_id() {
        return pro_id;
    }

    public void setPro_id(Long pro_id) {
        this.pro_id = pro_id;
    }

    public String getPro_descripcion() {
        return pro_descripcion;
    }

    public void setPro_descripcion(String pro_descripcion) {
        this.pro_descripcion = pro_descripcion;
    }

    public String getPro_codigo() {
        return pro_codigo;
    }

    public void setPro_codigo(String pro_codigo) {
        this.pro_codigo = pro_codigo;
    }

    public Double getPro_costo() {
        return pro_costo;
    }

    public void setPro_costo(Double pro_costo) {
        this.pro_costo = pro_costo;
    }

    public String getPro_unidad_compra() {
        return pro_unidad_compra;
    }

    public void setPro_unidad_compra(String pro_unidad_compra) {
        this.pro_unidad_compra = pro_unidad_compra;
    }

    public String getPro_clave() {
        return pro_clave;
    }

    public void setPro_clave(String pro_clave) {
        this.pro_clave = pro_clave;
    }

}
