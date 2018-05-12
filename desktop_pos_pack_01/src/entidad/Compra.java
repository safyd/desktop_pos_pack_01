/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import Safyd.System.DataBase.ORM;
import java.util.Date;

public class Compra extends ORM<Compra> {

    Integer com_id;
    Integer usu_id;
    Integer caj_id;
    Integer pro_id;
    Long com_folio;
    Double com_cantidad;
    Double com_subtotal;
    Double com_total;
    Double com_costo;
    Double com_descuento;
    Double com_impuesto;
    Date fechaCreacion;
    Date fechaModificacion;
    String usuarioCreacion;
    String usuarioModificacion;

    public Compra() {
        super.setClass(this);
    }

    public Compra(Integer com_id,
            Integer usu_id,
            Integer caj_id,
            Integer pro_id,
            Long com_folio,
            Double com_cantidad,
            Double com_subtotal,
            Double com_total,
            Double com_costo,
            Double com_descuento,
            Double com_impuesto,
            Date fechaCreacion,
            Date fechaModificacion,
            String usuarioCreacion,
            String usuarioModificacion) {
        super.setClass(this);
        this.com_id = com_id;
        this.usu_id = usu_id;
        this.caj_id = caj_id;
        this.pro_id = pro_id;
        this.com_folio = com_folio;
        this.com_cantidad = com_cantidad;
        this.com_subtotal = com_subtotal;
        this.com_total = com_total;
        this.com_costo = com_costo;
        this.com_descuento = com_descuento;
        this.com_impuesto = com_impuesto;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuarioCreacion = usuarioCreacion;
        this.usuarioModificacion = usuarioModificacion;
    }

    public Integer getCom_id() {
        return com_id;
    }

    public void setCom_id(Integer com_id) {
        this.com_id = com_id;
    }

    public Integer getUsu_id() {
        return usu_id;
    }

    public void setUsu_id(Integer usu_id) {
        this.usu_id = usu_id;
    }

    public Integer getCaj_id() {
        return caj_id;
    }

    public void setCaj_id(Integer caj_id) {
        this.caj_id = caj_id;
    }

    public Integer getPro_id() {
        return pro_id;
    }

    public void setPro_id(Integer pro_id) {
        this.pro_id = pro_id;
    }

    public Long getCom_folio() {
        return com_folio;
    }

    public void setCom_folio(Long com_folio) {
        this.com_folio = com_folio;
    }

    public Double getCom_cantidad() {
        return com_cantidad;
    }

    public void setCom_cantidad(Double com_cantidad) {
        this.com_cantidad = com_cantidad;
    }

    public Double getCom_subtotal() {
        return com_subtotal;
    }

    public void setCom_subtotal(Double com_subtotal) {
        this.com_subtotal = com_subtotal;
    }

    public Double getCom_total() {
        return com_total;
    }

    public void setCom_total(Double com_total) {
        this.com_total = com_total;
    }

    public Double getCom_costo() {
        return com_costo;
    }

    public void setCom_costo(Double com_costo) {
        this.com_costo = com_costo;
    }

    public Double getCom_descuento() {
        return com_descuento;
    }

    public void setCom_descuento(Double com_descuento) {
        this.com_descuento = com_descuento;
    }

    public Double getCom_impuesto() {
        return com_impuesto;
    }

    public void setCom_impuesto(Double com_impuesto) {
        this.com_impuesto = com_impuesto;
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
