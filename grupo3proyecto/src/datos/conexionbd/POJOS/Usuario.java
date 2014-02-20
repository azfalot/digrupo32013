package datos.conexionbd.POJOS;

import java.text.SimpleDateFormat;
import java.util.Date;
import logica.Queries;

/**
 *
 * @author Grupo 3
 */
public class Usuario {

    private final int p_escaladores;
    private String nombre;
    private String wallpaper;
    private Date periodoInicio;
    private Date periodoFin;

    Queries q = new Queries();//instancia de Queries necesaria para los updates
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

    public Usuario() {
        this.p_escaladores = 0;
    }

    /*
     * CONSTRUCTOR
     */
    public Usuario(int p_escaladores, String nombre, String wallpaper, Date periodoInicio, Date periodoFin) {
        this.p_escaladores = p_escaladores;
        this.nombre = nombre;
        this.wallpaper = wallpaper;
        this.periodoInicio = periodoInicio;
        this.periodoFin = periodoFin;
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

    public Date getPeriodoInicio() {
        return periodoInicio;
    }

    public Date getPeriodoFin() {
        return periodoFin;
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

    public void setPeriodoInicio(Date periodoInicio) {
        
        this.periodoInicio = periodoInicio;
        q.modificarRegistro("escaladores", p_escaladores, "periodo_inicio", "'" + sdf.format(periodoInicio) + "'");
    }

    public void setPeriodoFin(Date periodoFin) {
        this.periodoFin = periodoFin;
        q.modificarRegistro("escaladores", p_escaladores, "periodo_fin", "'" + sdf.format(periodoFin) + "'");
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
