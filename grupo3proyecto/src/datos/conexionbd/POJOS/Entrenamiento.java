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

    private final int p_entrenamiento;
    private final int a_escaladores;
    private Date hora_inicio, hora_fin, fecha;
    private int tipo;
    private String descripcion;
    
    private String tipoStr;
    private String dateStr;
    private String hora_inicioStr;
    private String hora_finStr;
    
    private final Queries q = new Queries();//instancia necesaria para hacer updates
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//formato de fechas
    private final SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");//formato de fechas
    private final SimpleDateFormat fhora = new SimpleDateFormat("hh:mm:ss");//formato de horas
    private final SimpleDateFormat fhora2 = new SimpleDateFormat("HH:mm");//formato de horas
    private final Methods m=Methods.getInstance();
    
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
    }
    
    /*
     * GETTERS
     */
    public String getDateStr() {
        return sdf2.format(fecha);
    }

    public String getHora_inicioStr() {
        return fhora2.format(hora_inicio);
    }

    public String getHora_finStr() {
        return fhora2.format(hora_fin);
    }

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
        switch(tipo){
            case 0:return m.write("fisico");
            case 1:return m.write("roca");
            case 2:return m.write("rocodromo");
        }
        return null;
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
