package entidad;

import Safyd.System.DataBase.ORM;

public class usuario extends ORM<usuario> {

    private Long usu_id;
    private Integer per_id;
    private byte[] usu_imagen;
    private String usu_usuario;
    private String usu_contrasena;
    private String usu_nombre;
    private String usu_apellido;
    private String usu_sexo;

    public byte[] getUsu_imagen() {
        return usu_imagen;
    }

    public void setUsu_imagen(byte[] usu_imagen) {
        this.usu_imagen = usu_imagen;
    }

    public usuario() {
        super.setClass(this);
    }

    public String getUsu_sexo() {
        return usu_sexo;
    }

    public void setUsu_sexo(String usu_sexo) {
        this.usu_sexo = usu_sexo;
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

    public String getUsu_contrasena() {
        return usu_contrasena;
    }

    public void setUsu_contrasena(String usu_contrasena) {
        this.usu_contrasena = usu_contrasena;
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
