/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datos.conexionbd.POJOS;

import java.util.Date;

/**
 *
 * @author Grupo 3
 */
public class Entrenamiento {
    
    private int p_escaladores;
    private int a_escaladores;
    private Date hora_inicio,hora_fin,fecha;
    private int tipo;
    private String descripcion;

    public Entrenamiento(int p_escaladores, int a_escaladores, Date hora_inicio, Date hora_fin, Date fecha, int tipo, String descripcion) {
        this.p_escaladores = p_escaladores;
        this.a_escaladores = a_escaladores;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.fecha = fecha;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public int getP_escaladores() {
        return p_escaladores;
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

    public int getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    
}
