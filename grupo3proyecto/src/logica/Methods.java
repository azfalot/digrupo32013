package logica;

import datos.conexionbd.POJOS.Usuario;
import datos.config.Config;
import datos.strings.Language;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    private final Queries q = new Queries();
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

    public int getUserId() {
        return user.getId();
    }

    public String getWallpaper() {
        return user.getWallpaper();
    }

    public String getUserName() {
        return user.getNombre();
    }

    public void setUser(Usuario user, boolean makeDefault) {
        /*
         * Permite seleccionar un usuario y ponerlo como por defecto o no
         */
        this.user = user;
        if (makeDefault) {
            cfg.setDefaultUser(user.getId());
        } else {
            cfg.setDefaultUser(0);
        }
    }

    private void getConfig() {
        /*
         * Obtener configuración del archivo config.cfg, se ejecutara al principio
         */
        cfg = new Config();
        lang = Language.getInstance(cfg.getLanguage());
        if (cfg.getDefaultUser() != 0) {
            this.user = getUsuario(cfg.getDefaultUser());
        }
    }

    public ObservableList getDificultades() {
        ObservableList data = FXCollections.observableArrayList();
        data.add("1");
        data.add("2");
        data.add("3");
        data.add("4");
        data.add("5a");
        data.add("5b");
        data.add("5c");
        data.add("6a");
        data.add("6a+");
        data.add("6b");
        data.add("6b+");
        data.add("6c");
        data.add("6c+");
        data.add("7a");
        data.add("7a+");
        data.add("7b");
        data.add("7b+");
        data.add("7c");
        data.add("7c+");
        data.add("8a");
        data.add("8a+");
        data.add("8b");
        data.add("8b+");
        data.add("8c");
        data.add("8c+");
        data.add("9a");
        data.add("9a+");
        data.add("9b");
        data.add("9b+");
        return data;
    }

    /*
     * CONSULTAS
     *
     * Las consultas estan en la clase Queries, esta clase solo actua como comunicador.
     * Esta forma de trabajar es por cuestion de orden.
     */
    public Usuario getUsuario(int id) {
        return q.getUsuario(id);
    }

    public Usuario getUserFromUsername(String name) {
        return q.getUserFromUsername(name);
    }

    public ArrayList getNombreUsuarios() {
        return q.getNombreUsuarios();
    }

    public int altaUsuario(String nombre) {
        return q.altaUsuario(nombre);
    }

    public void altaEntrenamiento(String horaIni, String minIni, String horaFin, String minFin, Date fechaSesion, int tipo, String descripcion) {
        q.altaEntrenamiento(getUserId(), horaIni, minIni, horaFin, minFin, fechaSesion, tipo, descripcion);
    }

    public void modificarRegistro(String tabla, int id, String campo, String nuevoValor) {
        q.modificarRegistro(tabla, id, campo, nuevoValor);
    }

    public ObservableList<String> getLocalizaciones() {
        return q.getLocalizaciones(getUserId());
    }

    public void altaItinerario(String nombre, String dificultad, String localizacion, int tipo, String foto, Date fecha) {
        q.altaItinerario(nombre, dificultad, localizacion, tipo, new File(foto), fecha, getUserId());
    }

}
