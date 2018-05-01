package entidad;

import Safyd.System.DataBase.ORM;

public class categoria extends ORM<categoria> {

    private Integer cat_id;
    private String cat_clave;
    private String cat_descripcion;

    public categoria() {
        super.setClass(this);
    }

    public categoria(
            Integer cat_id,
            String cat_clave,
            String cat_descripcion
    ) {
        super.setClass(this);
        this.cat_id = cat_id;
        this.cat_clave = cat_clave;
        this.cat_descripcion = cat_descripcion;
    }

    public Integer getCat_id() {
        return cat_id;
    }

    public void setCat_id(Integer cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_clave() {
        return cat_clave;
    }

    public void setCat_clave(String cat_clave) {
        this.cat_clave = cat_clave;
    }

    public String getCat_descripcion() {
        return cat_descripcion;
    }

    public void setCat_descripcion(String cat_descripcion) {
        this.cat_descripcion = cat_descripcion;
    }

      @Override
    public String toString() {
        return this.cat_clave;
    }

}
