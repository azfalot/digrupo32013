
package datos.conexionbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Daniel Plaza
 */
public class Datos {
    /*
    * Clase que conecta con la base de datos y permite realizar consultas sobre ella una vez instanciada
    * Esta clase ha sido diseñada con un patrón singleton 
    */
    private static Datos instancia=new Datos();
    private Connection connection;

    private Datos() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:file:database/escalada");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static Datos getInstancia(){
        return instancia;
    }
    
    public ResultSet consulta(String consulta) {
        ResultSet resultado=null;
        try {
            PreparedStatement ps = connection.prepareStatement(consulta);
            resultado = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

}
