package logica;

import datos.conexionbd.Datos;
import datos.conexionbd.POJOS.Entrenamiento;
import datos.conexionbd.POJOS.Itinerario;
import datos.conexionbd.POJOS.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Grupo 3
 */
public class Queries {

    private final Datos bd = Datos.getInstance();

    public Datos getBd() {
        return bd;
    }

    File copyPicture(File origen, String newName) {
        /*
         * Metodo que copia una imagen al directorio de imagenes
         */
        File destino = new File(newName + ".jpg");
        try {
            InputStream in = new FileInputStream(origen);
            OutputStream out = new FileOutputStream(destino);
            byte[] buf = new byte[2048];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        } catch (IOException e) {
        }
        return new File(destino.getAbsolutePath());
    }

    /*
     * QUERIES Y UPDATES
     */
    public Usuario getUsuario(int id) {
        /*
         * Devuelve los datos del usuario indicado
         */
        ResultSet rs = bd.consulta("select * from escaladores where p_escaladores=" + id);
        try {
            while (rs.next()) {
                return new Usuario(rs.getInt("p_escaladores"), rs.getString("nombre"), rs.getString("wallpaper"), rs.getDate("periodo_inicio"), rs.getDate("periodo_fin"));
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
                return new Usuario(rs.getInt("p_escaladores"), rs.getString("nombre"), rs.getString("wallpaper"), rs.getDate("periodo_inicio"), rs.getDate("periodo_fin"));
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //comprueba que el nombre esta libre
        for (String n : (ArrayList<String>) getNombreUsuarios()) {
            if (n.equals(nombre)) {
                return 0;
            }
        }
        int id = 1;
        while (true) {
            try {
                //bd.update("INSERT INTO ESCALADORES(P_ESCALADORES,NOMBRE,WALLPAPER,PERIODO_INICIO,PERIODO_FIN) VALUES (" + id + ",'" + nombre + "','" + "images" + File.separator + "defaultwallpaper.png" +"','"+sdf.format(new Date())+"','"+sdf.format(new Date())+ "')");
                bd.update("INSERT INTO ESCALADORES(P_ESCALADORES,NOMBRE,WALLPAPER,PERIODO_INICIO,PERIODO_FIN) VALUES (" + id + ",'" + nombre + "','','" + sdf.format(new Date()) + "','" + sdf.format(new Date()) + "')");

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
                        + "VALUES ( " + id + "," + userId + ",'" + horaIni + ":" + minIni + ":00' ,'" + horaFin + ":" + minFin + ":00','" + sdf.format(fechaSesion) + "' , " + tipo + ",'" + descripcion + "')");
                break;
            } catch (SQLException ex) {
                id++;
            }
        }
    }

    public boolean modificarRegistro(String tabla, int id, String campo, String nuevoValor) {
        /*
         * Metodo que modifica un registro en la tabla indicada.
         * En caso de error devolvera false, indicando asi que no se ha podido 
         * realizar el update a la base de datos.
         *
         * Hay que tener en cuenta que hay que pasarle el valor en HSQL, por ejemplo
         * una fecha se pasaria como '0000-00-00' al parametro nuevoValor, y un 
         * varchar se pasaria 'nuevo valor' con las comillas simples incluidas.
         * De este modo se tiene un metodo para cambiar registros estandarizado.
         */
        try {
            bd.update("UPDATE " + tabla + " SET " + campo + "=" + nuevoValor + " WHERE p_" + tabla + "=" + id);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public ObservableList<String> getLocalizaciones(int userId) {
        /*
         * Devuelve un ArrayList con las localizaciones
         */
        ObservableList data = FXCollections.observableArrayList();
        ResultSet rs = bd.consulta("select i.localizacion from itinerario i,esc_it ei where i.p_itinerario=ei.a_itinerario and ei.a_escaladores=" + userId + " group by i.localizacion");
        try {
            while (rs.next()) {
                data.add(rs.getString("localizacion"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public void altaItinerario(String nombre, String dificultad, String localizacion, int tipo, File foto, Date fecha, int userId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int idItinerario = 0;
        String imgName;
        while (true) {
            try {
                imgName = "images" + File.separator + "it-" + idItinerario;
                bd.update(""
                        + "INSERT INTO ITINERARIO"
                        + " (P_ITINERARIO,NOMBRE,DIFICULTAD,LOCALIZACION,TIPO,FOTO)"
                        + " VALUES ( " + idItinerario + ", '" + nombre + "', '" + dificultad + "', '" + localizacion + "'," + tipo + " ,'" + imgName + "')");
                break;
            } catch (SQLException ex) {
                idItinerario++;
            }
        }
        copyPicture(foto, imgName);
        int idIT_esc = 0;
        while (true) {
            try {
                bd.update("INSERT INTO ESC_IT"
                        + " (P_ESC_IT,A_ESCALADORES,A_ITINERARIO,FECHA)"
                        + " VALUES (" + idIT_esc + "," + userId + "," + idItinerario + ",'" + sdf.format(fecha) + "')");
                break;
            } catch (SQLException ex) {
                idIT_esc++;
            }
        }
    }

    public ArrayList getEntrenamientos(int userId) {
        /*
         * Devuelve un ArrayList con los todos los entrenamientos del usuario
         */
        ArrayList<Entrenamiento> entrenamientos = new ArrayList();

        ResultSet rs = bd.consulta("select * from sesion_entrenamientos where a_escaladores=" + userId);
        try {
            while (rs.next()) {
                entrenamientos.add(new Entrenamiento(rs.getInt("p_sesion_entrenamientos"), rs.getInt("a_escaladores"), rs.getTime("hora_inicio"), rs.getTime("hora_fin"), rs.getDate("fecha"), rs.getInt("tipo"), rs.getString("descripcion")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return entrenamientos;
    }

    public ArrayList getItinerarios(int userId) {
        /*
         * Devuelve un ArrayList con los todos los itinerarios del usuario
         */
        ArrayList<Itinerario> itinerarios = new ArrayList();

        ResultSet rs = bd.consulta("select i.*,e.* from itinerario i,esc_it e where i.p_itinerario=e.a_itinerario and e.a_escaladores=" + userId);
        try {
            while (rs.next()) {
                itinerarios.add(new Itinerario(rs.getInt("p_itinerario"), rs.getString("nombre"), rs.getString("dificultad"), rs.getString("localizacion"), rs.getInt("tipo"), rs.getString("foto"), rs.getInt("p_esc_it"), rs.getInt("a_escaladores"), rs.getDate("fecha")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return itinerarios;
    }

    public void deleteEntrenamiento(int p_sesion_entrenamientos) {
        //Borra un entrenamiento pasandole el id
        try {
            bd.update("delete from sesion_entrenamientos where p_sesion_entrenamientos=" + p_sesion_entrenamientos);
        } catch (SQLException ex) {
        }
    }

    public Itinerario getItinerario(int p_itinerario) {
        ResultSet rs = bd.consulta("select i.*,e.* from itinerario i,esc_it e where i.p_itinerario=e.a_itinerario and i.p_itinerario=" + p_itinerario);
        try {
            return new Itinerario(rs.getInt("p_itinerario"), rs.getString("nombre"), rs.getString("dificultad"), rs.getString("localizacion"), rs.getInt("tipo"), rs.getString("foto"), rs.getInt("p_esc_it"), rs.getInt("a_escaladores"), rs.getDate("fecha"));
        } catch (SQLException ex) {
        }
        return null;
    }

    public void deleteItinerario(int p_itinerario) {
        //Borra un itinerario pasandole el id
        try {
            bd.update("delete from itinerario where p_itinerario=" + p_itinerario);
        } catch (SQLException ex) {
        }
    }

    public void deleteUsuario(int p_escaladores) {
        //Borra un usuario pasandole el id
        ArrayList<Itinerario> it = getItinerarios(p_escaladores);
        for (int i = 0; i < it.size(); i++) {
            deleteItinerario(it.get(i).getP_itinerario());
        }
        try {
            bd.update("delete from escaladores where p_escaladores=" + p_escaladores);
        } catch (SQLException ex) {
        }
    }

    public ArrayList getUsuarios() {
        /*
         * Devuelve un ArrayList con todos los usuarios
         */
        ArrayList usuarios = new ArrayList();
        ResultSet rs = bd.consulta("select * from escaladores");
        try {
            while (rs.next()) {
                usuarios.add(new Usuario(rs.getInt("p_escaladores"), rs.getString("nombre"), rs.getString("wallpaper"), rs.getDate("periodo_inicio"), rs.getDate("periodo_fin")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usuarios;
    }

    public int getCantidadItinerarios(int p_escaladores, Date fechaInicio, Date fechaFin) {
        /*
         * Obtiene la cantidad de itinerarios realizados entre 2 fechas
         */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ResultSet rs = bd.consulta("select a_itinerario from esc_it where a_escaladores=" + p_escaladores + " and fecha between '" + sdf.format(fechaInicio) + "' and '" + sdf.format(fechaFin) + "'");
        int c = 0;
        try {
            while (rs.next()) {
                c++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return c;
    }

    public int getHorasEntrenamiento(int p_escaladores, Date fechaInicio, Date fechaFin) {
        /*
         * Devuelve el numero de horas completas de entrenamiento realizadas entre dos
         * fechas pasadas como parametro
         */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat fHora = new SimpleDateFormat("HH:mm:ss");
        double horas = 0;
        ResultSet rs = bd.consulta("select hora_inicio,hora_fin from sesion_entrenamientos where a_escaladores=" + p_escaladores + " and fecha between '" + sdf.format(fechaInicio) + "' and '" + sdf.format(fechaFin) + "'");
        try {
            while (rs.next()) {
                Date horaIni = null;
                Date horaFin = null;
                try {
                    horaIni = fHora.parse(rs.getString("hora_inicio"));
                    horaFin = fHora.parse(rs.getString("hora_fin"));
                } catch (ParseException ex) {
                }

                double dif = (horaFin.getTime() - horaIni.getTime());
                /*
                 * Si la hora de comienzo es mayor que la fecha de inicio el porgrama
                 * deduce que se ha estado entrenando hasta pasada media noche
                 */
                if (dif <= 0) {
                    horas += dif / (1000 * 60 * 60);
                } else {
                    horas += (24 + dif) / (1000 * 60 * 60);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (int) horas;//se hace el casting para coger solo las horas completas
    }
}
