
package datos.conexionbd.POJOS;

/**
 *
 * @author Grupo 3
 */
public class Usuario {
    private final int p_escaladores;
    private String nombre;
    private String wallpaper;

    public Usuario() {
        this.p_escaladores=0;
    }
    
    public Usuario(int id, String nombre,String wallpaper) {
        this.p_escaladores = id;
        this.nombre = nombre;
        this.wallpaper=wallpaper;
    }

    public int getId() {
        return p_escaladores;
    }

    public String getNombre() {
        return nombre;
    }

    public String getWallpaper() {
        return wallpaper;
    }

}
