package entidad;

import Safyd.System.DataBase.ORM;

public class perfil extends ORM<perfil> {

    private Integer per_id;
    private String per_clave;
    private String per_descripcion;

    public perfil() {
        super.setClass(this);
    }

    public perfil(
            Integer per_id,
            String per_clave,
            String per_descripcion
    ) {

        super.setClass(this);
        this.per_id = per_id;
        this.per_clave = per_clave;
        this.per_descripcion = per_descripcion;

    }

    public Integer getPer_id() {
        return per_id;
    }

    public void setPer_id(Integer per_id) {
        this.per_id = per_id;
    }

    public String getPer_clave() {
        return per_clave;
    }

    public void setPer_clave(String per_clave) {
        this.per_clave = per_clave;
    }

    public String getPer_descripcion() {
        return per_descripcion;
    }

    public void setPer_descripcion(String per_descripcion) {
        this.per_descripcion = per_descripcion;
    }

}
