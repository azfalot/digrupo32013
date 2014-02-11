package logica;

import datos.conexionbd.Datos;
import datos.conexionbd.POJOS.Usuario;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Grupo 3
 */
class Queries {

    private final Datos bd = Datos.getInstance();

    public Usuario getUsuario(int id) {
        /*
         * Devuelve los datos del usuario indicado
         */
        ResultSet rs = bd.consulta("select * from escaladores where p_escaladores=" + id);
        try {
            while (rs.next()) {
                return new Usuario(rs.getInt("p_escaladores"), rs.getString("nombre"), rs.getString("wallpaper"));
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
                return new Usuario(rs.getInt("p_escaladores"), rs.getString("nombre"), rs.getString("wallpaper"));
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

    public int altaUsuario(String nombre) {
        /*
         * Crea un usuario con el nombre indicado como parametro y devuelve la clave 
         * asignada.
         * Si devuelve 0 es que el nombre ya esta cogido
         */

        //comprueba que el nombre esta libre
        for (String n : (ArrayList<String>) getNombreUsuarios()) {
            if (n.equals(nombre)) {
                return 0;
            }
        }
        int id = 1;
        while (true) {
            try {
                bd.update("INSERT INTO ESCALADORES(P_ESCALADORES,NOMBRE,WALLPAPER) VALUES (" + id + ",'" + nombre + "','" + "resources" + File.separator + "defaultwallpaper.png" + "')");
                break;
            } catch (SQLException ex) {
                id++;
            }
        }
        return id;
    }

    public void altaEntrenamiento(int userId, String horaIni, String minIni, String horaFin, String minFin, Date fechaSesion, int tipo, String descripcion) {
        /*
         * Alta de entrenamiento
         * Las horas y minutos se reciben como string
         */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int id = 0;

        while (true) {
            try {
                bd.update("INSERT INTO SESION_ENTRENAMIENTOS( P_SESION_ENTRENAMIENTOS, A_ESCALADORES, HORA_INICIO, HORA_FIN, FECHA, TIPO,DESCRIPCION) "
                        + "VALUES ( " + id + "," + userId + ",TIME'" + horaIni + ":" + minIni + ":00' ,TIME'" + horaFin + ":" + minFin + ":00',DATE'" + sdf.format(fechaSesion) + "' , " + tipo + ",'" + descripcion + "')");
                break;
            } catch (SQLException ex) {
                id++;
            }
        }
    }

    public boolean modificarRegistro(String tabla,int id,String campo,String nuevoValor) {
        /*
        * Metodo que modifica un registro en la tabla indicada.
        * En caso de error devolvera false, indicando asi que no se ha podido 
        * realizar el update a la base de datos.
        *
        * Hay que tener en cuenta que hay que pasarle el valor en HSQL, por ejemplo
        * una fecha se pasaria como DATE'0000-00-00' al parametro nuevoValor, y un 
        * varchar se pasaria 'nuevo valor' con las comillas simples incluidas.
        * De este modo se tiene un metodo para cambiar registros estandarizado.
        */
        try {
            bd.update("UPDATE "+tabla+" SET "+campo+"="+nuevoValor+" WHERE p_"+tabla+"="+id);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public ObservableList<String> getLocalizaciones(int userId){
        /*
        * Devuelve un ArrayList con las localizaciones
        */
        ObservableList data = FXCollections.observableArrayList();
        ResultSet rs = bd.consulta("select i.localizacion from itinerario i,esc_it ei where i.p_itinerario=ei.a_itinerario and ei.a_escaladores="+userId);
        try {
            while (rs.next()) {
                data.add(rs.getString("localizacion"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
    }
}
