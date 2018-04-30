package entidad;

import Safyd.System.DataBase.ORM;

public class usuario extends ORM<usuario> {

    private Long usu_id;
    private Integer per_id;
    private byte[] art_imagen;
    private String usu_usuario;
    private String usu_contraseña;
    private String usu_nombre;
    private String usu_apellido;

    public usuario() {
        super.setClass(this);
    }

    public byte[] getArt_imagen() {
        return art_imagen;
    }

    public void setArt_imagen(byte[] art_imagen) {
        this.art_imagen = art_imagen;
    }

    public Long getUsu_id() {
        return usu_id;
    }

    public void setUsu_id(Long usu_id) {
        this.usu_id = usu_id;
    }

    public Integer getPer_id() {
        return per_id;
    }

    public void setPer_id(Integer per_id) {
        this.per_id = per_id;
    }

    public String getUsu_usuario() {
        return usu_usuario;
    }

    public void setUsu_usuario(String usu_usuario) {
        this.usu_usuario = usu_usuario;
    }

    public String getUsu_contraseña() {
        return usu_contraseña;
    }

    public void setUsu_contraseña(String usu_contraseña) {
        this.usu_contraseña = usu_contraseña;
    }

    public String getUsu_nombre() {
        return usu_nombre;
    }

    public void setUsu_nombre(String usu_nombre) {
        this.usu_nombre = usu_nombre;
    }

    public String getUsu_apellido() {
        return usu_apellido;
    }

    public void setUsu_apellido(String usu_apellido) {
        this.usu_apellido = usu_apellido;
    }

}
