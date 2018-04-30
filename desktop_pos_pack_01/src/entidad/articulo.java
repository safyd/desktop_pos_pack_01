package entidad;

import Safyd.System.DataBase.ORM;
import java.sql.Date;

public class articulo extends ORM<articulo> {

    private Integer art_id;
    private Integer cat_id;
    private String art_nombre;
    private String art_unidad_medida;
    private byte[] art_imagen;
    private String art_codigo;
    private Double art_precio;
    private String art_descripcion;
    private Double art_nuevo_costo;
    private Double art_ultimo_costo;
    private Double art_ultima_existencia;
    private Double art_nueva_existencia;
    private Integer art_activo;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private String usuarioCreacion;
    private String usuarioModificacion;

    public articulo() {
        super.setClass(this);
    }

    public String getArt_nombre() {
        return art_nombre;
    }

    public void setArt_nombre(String art_nombre) {
        this.art_nombre = art_nombre;
    }

    public Integer getArt_id() {
        return art_id;
    }

    public void setArt_id(Integer art_id) {
        this.art_id = art_id;
    }

    public Integer getCat_id() {
        return cat_id;
    }

    public void setCat_id(Integer cat_id) {
        this.cat_id = cat_id;
    }

    public String getArt_unidad_medida() {
        return art_unidad_medida;
    }

    public void setArt_unidad_medida(String art_unidad_medida) {
        this.art_unidad_medida = art_unidad_medida;
    }

    public byte[] getArt_imagen() {
        return art_imagen;
    }

    public void setArt_imagen(byte[] art_imagen) {
        this.art_imagen = art_imagen;
    }

    public String getArt_codigo() {
        return art_codigo;
    }

    public void setArt_codigo(String art_codigo) {
        this.art_codigo = art_codigo;
    }

    public Double getArt_precio() {
        return art_precio;
    }

    public void setArt_precio(Double art_precio) {
        this.art_precio = art_precio;
    }

    public String getArt_descripcion() {
        return art_descripcion;
    }

    public void setArt_descripcion(String art_descripcion) {
        this.art_descripcion = art_descripcion;
    }

    public Double getArt_nuevo_costo() {
        return art_nuevo_costo;
    }

    public void setArt_nuevo_costo(Double art_nuevo_costo) {
        this.art_nuevo_costo = art_nuevo_costo;
    }

    public Double getArt_ultimo_costo() {
        return art_ultimo_costo;
    }

    public void setArt_ultimo_costo(Double art_ultimo_costo) {
        this.art_ultimo_costo = art_ultimo_costo;
    }

    public Double getArt_ultima_existencia() {
        return art_ultima_existencia;
    }

    public void setArt_ultima_existencia(Double art_ultima_existencia) {
        this.art_ultima_existencia = art_ultima_existencia;
    }

    public Double getArt_nueva_existencia() {
        return art_nueva_existencia;
    }

    public void setArt_nueva_existencia(Double art_nueva_existencia) {
        this.art_nueva_existencia = art_nueva_existencia;
    }

    public Integer getArt_activo() {
        return art_activo;
    }

    public void setArt_activo(Integer art_activo) {
        this.art_activo = art_activo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

}
