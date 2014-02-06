/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datos.conexionbd.POJOS;

/**
 *
 * @author Daniel Plaza
 */
public class Usuario {
    private final int id;
    private String nombre;
    private String wallpaper;

    public Usuario() {
        this.id=0;
    }
    
    public Usuario(int id, String nombre,String wallpaper) {
        this.id = id;
        this.nombre = nombre;
        this.wallpaper=wallpaper;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getWallpaper() {
        return wallpaper;
    }

}
