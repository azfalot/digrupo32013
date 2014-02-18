/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datos.conexionbd.POJOS;

import java.text.SimpleDateFormat;
import java.util.Date;
import logica.Methods;
import logica.Queries;

/**
 *
 * @author Grupo 3
 */
public class Itinerario {
    private int p_itinerario;
    private String nombre;
    private String dificultad;
    private String localizacion;
    private int tipo;
    private String foto;
    private String tipoStr;
    
    private int p_esc_it;
    private int a_escaladores;
    private Date fecha;
    
    private Queries q=new Queries();
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    private Methods m=Methods.getInstance();
    
    /*
    * CONSTRUCTOR
    */
    public Itinerario(int p_itinerario, String nombre, String dificultad, String localizacion, int tipo, String foto, int p_esc_it, int a_escaladores, Date fecha) {
        this.p_itinerario = p_itinerario;
        this.nombre = nombre;
        this.dificultad = dificultad;
        this.localizacion = localizacion;
        this.tipo = tipo;
        this.foto = foto;
        this.p_esc_it = p_esc_it;
        this.a_escaladores = a_escaladores;
        this.fecha = fecha;
        switch(tipo){
            case 0:tipoStr=m.write("via");break;
            case 1:tipoStr=m.write("boulder");break;
        }
    }
    
    /*
    * GETTERS
    */

    public int getP_itinerario() {
        return p_itinerario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDificultad() {
        return dificultad;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public String getTipoStr() {
        return tipoStr;
    }

    public String getFoto() {
        return foto;
    }

    public int getP_esc_it() {
        return p_esc_it;
    }

    public int getA_escaladores() {
        return a_escaladores;
    }

    public Date getFecha() {
        return fecha;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }
    
    /*
     * SETTERS
     */

    public void setNombre(String nombre) {
        this.nombre = nombre;
        q.modificarRegistro("itinerario", this.p_itinerario, "nombre", "'"+nombre+"'");
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
        q.modificarRegistro("itinerario", this.p_itinerario, "dificultad", "'"+dificultad+"'");
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
        q.modificarRegistro("itinerario", this.p_itinerario, "localizacion", "'"+localizacion+"'");
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
        q.modificarRegistro("itinerario", this.p_itinerario, "tipo", String.valueOf(tipo));
        switch(tipo){
            case 0:tipoStr=m.write("via");break;
            case 1:tipoStr=m.write("boulder");break;
        }
    }
    
    public void setTipoStr(String tipoStr) {
        /*
        * NO USAR ESTE SETTER
        */
        this.tipoStr = tipoStr;
    }
    
    public void setFoto(String foto) {
        this.foto = foto;
        q.modificarRegistro("itinerario", this.p_itinerario, "foto", "'"+foto+"'");
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
        q.modificarRegistro("esc_it", this.p_esc_it, "fecha", "'"+sdf.format(fecha)+"'");
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
        if(((Itinerario)obj).getP_itinerario()==this.p_itinerario){
            return true;
        }else{
            return false;
        }
    }
}