/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datos.conexionbd;

/**
 *
 * @author Daniel Plaza
 */
public class Usuario {
    private int id;
    private String nombre;

    public Usuario() {
        this.id=0;
    }
    
    public Usuario(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }    

}
