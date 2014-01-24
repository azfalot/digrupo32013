

package logica;

import datos.conexionbd.Datos;
import datos.strings.Language;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
    private Language lang;
    private final Datos bd=Datos.getInstance();
    
    private Methods(){
        getConfig();//configura los valores iniciales
    }
    
    public static Methods getInstance(){
        instance=new Methods();
        return instance;
    }
    
    public String write(String text){
        /*
        * Metodo que conecta la interfaz con los archivos de idioma
        */
        return lang.write(text);
    }
    
    private void getConfig(){
        /*
        * Metodo que explora el archivo de configuracion config.cfg para seleccionar
        * las opciones iniciales
        */
        try {
            FileReader fr=new FileReader(new File("config.cfg"));
            BufferedReader br = new BufferedReader(fr);
            
            /*
            * Lee la configuracion de idioma. Sera de la forma:
            *   Language=english
            */
            String line;
            while(true){
                line=br.readLine();
                if(line.contains("Language=")){
                    lang=Language.getInstance((line.substring(line.indexOf("=")+1)).toLowerCase());
                    break;
                }
            }
            br = new BufferedReader(fr);
            //Fin de la lectura de idioma
            
            /*
            * Mas configuraciones: default user, ...
            *
            *   String line;
            *   while(true){
            *      line=br.readLine();
            *      if(line.contains("Opcion=")){
            *          VALOR: line.substring(line.indexOf("=")+1)).toLowerCase()
            *          break;
            *      }
            *   }
            *   br = new BufferedReader(fr);
            */
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /*
    * Consultas
    */
    public ArrayList getUsuarios(){
        ArrayList usuarios=new ArrayList();
        
        ResultSet rs=bd.consulta("select nombre as nombreusuario from escaladores");
        try {
            while(rs.next()){
                usuarios.add(rs.getString("nombreusuario"));
            }
        } catch (SQLException ex) { 
            ex.printStackTrace();
        }
        return usuarios;
    }
}
