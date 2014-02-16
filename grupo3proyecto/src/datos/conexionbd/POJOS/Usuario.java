package datos.conexionbd.POJOS;

import logica.Queries;

/**
 *
 * @author Grupo 3
 */
public class Usuario {

    private final int p_escaladores;
    private String nombre;
    private String wallpaper;

    Queries q = new Queries();//instancia de Queries necesaria para los updates

    public Usuario() {
        this.p_escaladores = 0;
    }

    /*
     * CONSTRUCTOR
     */
    public Usuario(int id, String nombre, String wallpaper) {
        this.p_escaladores = id;
        this.nombre = nombre;
        this.wallpaper = wallpaper;
    }

    /*
     * GETTERS
     */
    public int getId() {
        return p_escaladores;
    }

    public String getNombre() {
        return nombre;
    }

    public String getWallpaper() {
        return wallpaper;
    }

    /*
     * SETTERS
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
        q.modificarRegistro("escaladores", p_escaladores, "nombre", "'" + nombre + "'");
    }

    public void setWallpaper(String wallpaper) {
        this.wallpaper = wallpaper;
        q.modificarRegistro("escaladores", p_escaladores, "wallpaper", "'" + wallpaper + "'");
    }
    
    
    /*
    * EQUALS
    */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if(((Usuario)obj).getId()==this.getId()){
            return true;
        }else{
            return false;
        }
    }
}
