
package logica;

import datos.conexionbd.Datos;
import datos.conexionbd.POJOS.Usuario;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    public void altaEntrenamiento(int userId,String horaIni,String minIni,String horaFin,String minFin,Date fechaSesion,int tipo,String descripcion) {
        /*
        * Alta de entrenamiento
        * Las horas y minutos se reciben como string
        */
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        int id = 0;
        
        while (true) {
            try {
                bd.update("INSERT INTO SESION_ENTRENAMIENTOS( P_SESION_ENTRENAMIENTOS, A_ESCALADORES, HORA_INICIO, HORA_FIN, FECHA, TIPO,DESCRIPCION) "
                        + "VALUES ( "+id+","+userId+",TIME'"+horaIni+":"+minIni+":00' ,TIME'"+horaFin+":"+minFin+":00',DATE'"+sdf.format(fechaSesion)+"' , "+tipo+",'"+descripcion+"')");
                break;
            } catch (SQLException ex) {
                id++;
            }
        }
    }
}
