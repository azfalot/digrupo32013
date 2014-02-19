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
public class Entrenamiento {

    private int p_entrenamiento;
    private int a_escaladores;
    private Date hora_inicio, hora_fin, fecha;
    private int tipo;
    private String descripcion;
    private String tipoStr;
    
    private Queries q = new Queries();//instancia necesaria para hacer updates
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//formato de fechas
    private SimpleDateFormat fhora = new SimpleDateFormat("hh:mm:ss");//formato de horas
    private Methods m=Methods.getInstance();
    
    /*
    * CONSTRUCTOR
    */
    
    public Entrenamiento(int p_entrenamiento, int a_escaladores, Date hora_inicio, Date hora_fin, Date fecha, int tipo, String descripcion) {
        this.p_entrenamiento = p_entrenamiento;
        this.a_escaladores = a_escaladores;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.fecha = fecha;
        this.tipo = tipo;
        this.descripcion = descripcion;
        switch(tipo){
            case 0:tipoStr=m.write("fisico");break;
            case 1:tipoStr=m.write("rocodromo");break;
            case 2:tipoStr=m.write("roca");break;
        }
    }
    
    /*
    * GETTERS
    */

    public int getP_entrenamiento() {
        return p_entrenamiento;
    }

    public int getA_escaladores() {
        return a_escaladores;
    }

    public Date getHora_inicio() {
        return hora_inicio;
    }

    public Date getHora_fin() {
        return hora_fin;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getTipoStr() {
        return tipoStr;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    /*
     * SETTERS
     */
    public void setHora_inicio(Date hora_inicio) {
        this.hora_inicio = hora_inicio;
        q.modificarRegistro("sesion_entrenamientos", this.p_entrenamiento, "hora_inicio", "'" + String.valueOf(fhora.format(hora_inicio)) + "'");
    }

    public void setHora_fin(Date hora_fin) {
        this.hora_fin = hora_fin;
        q.modificarRegistro("sesion_entrenamientos", this.p_entrenamiento, "hora_fin", "'" + String.valueOf(fhora.format(hora_fin)) + "'");
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
        q.modificarRegistro("sesion_entrenamientos", this.p_entrenamiento, "fecha", "'" + String.valueOf(sdf.format(fecha)) + "'");
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
        q.modificarRegistro("sesion_entrenamientos", this.p_entrenamiento, "tipo", String.valueOf(tipo));
        switch(tipo){
            case 0:tipoStr=m.write("fisico");break;
            case 1:tipoStr=m.write("rocodromo");break;
            case 2:tipoStr=m.write("roca");break;
        }
    }

    public void setTipoStr(String tipoStr) {
        /*
        * NO USAR ESTE SETTER
        */
        this.tipoStr = tipoStr;
    }
    
    
    

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        q.modificarRegistro("sesion_entrenamientos", this.p_entrenamiento, "descripcion", descripcion);
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
        if(((Entrenamiento)obj).getP_entrenamiento()==this.p_entrenamiento){
            return true;
        }else{
            return false;
        }
    }

}
