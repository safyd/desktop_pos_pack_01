package entidad;

import Safyd.System.DataBase.ORM;

public class Compania extends ORM<Compania> {

    private Integer com_id;
    private byte[] com_imagen;
    private String com_nombre;
    private String com_direccion;
    private String com_cp;
    private String com_ciudad;
    private String com_estado;
    private String com_telefono;
    private Integer com_no_interior;
    private Integer com_no_exterior;

    public Compania() {
        super.setClass(this);
    }

    public Compania(Integer com_id, byte[] com_imagen, String com_nombre, String com_direccion, String com_cp, String com_ciudad, String com_estado, String com_telefono, Integer com_no_interior, Integer com_no_exterior) {
        super.setClass(this);
        this.com_id = com_id;
        this.com_imagen = com_imagen;
        this.com_nombre = com_nombre;
        this.com_direccion = com_direccion;
        this.com_cp = com_cp;
        this.com_ciudad = com_ciudad;
        this.com_estado = com_estado;
        this.com_telefono = com_telefono;
        this.com_no_interior = com_no_interior;
        this.com_no_exterior = com_no_exterior;
    }

    public Integer getCom_id() {
        return com_id;
    }

    public void setCom_id(Integer com_id) {
        this.com_id = com_id;
    }

    public byte[] getCom_imagen() {
        return com_imagen;
    }

    public void setCom_imagen(byte[] com_imagen) {
        this.com_imagen = com_imagen;
    }

    public String getCom_nombre() {
        return com_nombre;
    }

    public void setCom_nombre(String com_nombre) {
        this.com_nombre = com_nombre;
    }

    public String getCom_direccion() {
        return com_direccion;
    }

    public void setCom_direccion(String com_direccion) {
        this.com_direccion = com_direccion;
    }

    public String getCom_cp() {
        return com_cp;
    }

    public void setCom_cp(String com_cp) {
        this.com_cp = com_cp;
    }

    public String getCom_ciudad() {
        return com_ciudad;
    }

    public void setCom_ciudad(String com_ciudad) {
        this.com_ciudad = com_ciudad;
    }

    public String getCom_estado() {
        return com_estado;
    }

    public void setCom_estado(String com_estado) {
        this.com_estado = com_estado;
    }

    public String getCom_telefono() {
        return com_telefono;
    }

    public void setCom_telefono(String com_telefono) {
        this.com_telefono = com_telefono;
    }

    public Integer getCom_no_interior() {
        return com_no_interior;
    }

    public void setCom_no_interior(Integer com_no_interior) {
        this.com_no_interior = com_no_interior;
    }

    public Integer getCom_no_exterior() {
        return com_no_exterior;
    }

    public void setCom_no_exterior(Integer com_no_exterior) {
        this.com_no_exterior = com_no_exterior;
    }

}
