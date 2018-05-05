package continuas;

import entidad.Compania;
import entidad.Usuario;

public class instancia {

    protected static Compania compania = null;
    protected static Usuario usuario = null;

    public static Compania obtenerCompaniaSingleton() {
        if (instancia.compania == null) {
            instancia.compania = new Compania();
        }
        return instancia.compania;
    }

    public static Usuario obtenerUsuarioSingleton() {
        if (instancia.usuario == null) {
            instancia.usuario = new Usuario();
        }
        return instancia.usuario;
    }

}
