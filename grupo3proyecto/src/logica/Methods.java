package logica;

import datos.conexionbd.Datos;
import datos.conexionbd.Usuario;
import datos.config.Config;
import datos.strings.Language;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Grupo 3
 */
public class Methods {
    /*
     * Clase que conecta datos con interfaz. Se ha programado con un patron singleton de diseño.
     * 
     * 
     */

    private static Methods instance;
    private Config cfg;
    private Language lang;
    private final Datos bd = Datos.getInstance();
    private boolean remember;//indica si se guarda el usuario por defecto
    private Usuario user = new Usuario();

    private Methods() {
        getConfig();//configura los valores iniciales
    }

    public static Methods getInstance() {
        instance = new Methods();
        return instance;
    }

    public String write(String text) {
        /*
         * Metodo que conecta la interfaz con los archivos de idioma
         */
        return lang.write(text);
    }

    public boolean getRemember() {
        return remember;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user, boolean makeDefault) {
        /*
        * Permite seleccionar un usuario y ponerlo como por defecto o no
        */
        this.user = user;
        if (makeDefault) {
            cfg.setDefaultUser(user.getId());
        }
    }

    private void getConfig() {
        /*
        * Obtener configuración del archivo config.cfg, se ejecutara al principio
        */
        cfg = new Config();
        lang = Language.getInstance(cfg.getLanguage());
        if (cfg.getDefaultUser() != 0) {
            remember = true;
            this.user = getUsuario(cfg.getDefaultUser());
        } else {
            remember = false;
        }
    }

    /*
     * Consultas
     */
    public Usuario getUsuario(int id) {
        /*
         * Devuelve los datos del usuario indicado
         */
        ResultSet rs = bd.consulta("select * from escaladores where p_escaladores=" + id);
        try {
            while (rs.next()) {
                return new Usuario(rs.getInt("p_escaladores"), rs.getString("nombre"), rs.getInt("edad"), rs.getFloat("altura"), rs.getFloat("peso"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public Usuario getUserFromUsername(String name) {
        /*
        * Devuelve los datos de un usuario a traves de su nombre, que es único
        */
        ResultSet rs = bd.consulta("select * from escaladores where nombre='" + name + "'");
        try {
            while (rs.next()) {
                return new Usuario(rs.getInt("p_escaladores"), rs.getString("nombre"), rs.getInt("edad"), rs.getFloat("altura"), rs.getFloat("peso"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList getNombreUsuarios() {
        /*
        * Devuelve un arraylist con los nombres de los usuarios como string
        */
        ArrayList usuarios = new ArrayList();
        ResultSet rs = bd.consulta("select nombre from escaladores");
        try {
            while (rs.next()) {
                usuarios.add(rs.getString("nombre"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usuarios;
    }

    
}
