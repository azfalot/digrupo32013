
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
    * 
    * Los archivos de la base de datos deben encontrarse en //database/
    */
    private static Datos instance=new Datos();
    private Connection connection;
    private final String DATABASE="escalada";//nombre de la base de datos
    
    //CONSTRUCTOR
    private Datos() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:file:database/"+DATABASE);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    //metodo que devuelve la unica instancia: "constructor" secundario
    public static Datos getInstance(){
        return instance;
    }
    
    public ResultSet consulta(String consulta) {
        /*
        * Este metodo ejecuta una consulta recibida como parametro y devuelve los 
        * datos en un ResulSet
        */
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
