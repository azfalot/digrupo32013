/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import datos.conexionbd.Datos;
import datos.conexionbd.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                return new Usuario(rs.getInt("p_escaladores"), rs.getString("nombre"));
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
                return new Usuario(rs.getInt("p_escaladores"), rs.getString("nombre"));
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
        for(String n:(ArrayList<String>)getNombreUsuarios()){
            if(n.equals(nombre)){
                return 0;
            }
        }
        Statement statement;
        int id=1;
        try {
            statement = bd.getConnection().createStatement();
            while (true) {
                try {
                    statement.executeUpdate("INSERT INTO ESCALADORES(P_ESCALADORES,NOMBRE) VALUES ("+id+",'"+nombre +"')");
                    break;
                } catch (SQLException ex) {
                    id++;
                }
            }
        } catch (SQLException ex) {
        }
        return id;
    }
}
