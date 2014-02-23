/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logica;

/**
 *
 * @author Grupo 3
 */
class Semana {
    
    int itinerarios;
    double horasEnt;

    public Semana(int itinerarios, double horasEnt) {
        this.itinerarios = itinerarios;
        this.horasEnt = horasEnt;
    }

    public Semana() {
    }
    
    public void addHoras(double horas){
        horasEnt+=horas;
    }
    
    public void addItinerarios(double it){
        itinerarios+=it;
    }
    
    public int getItinerarios() {
        return itinerarios;
    }

    public void setItinerarios(int itinerarios) {
        this.itinerarios = itinerarios;
    }

    public double getHorasEnt() {
        return horasEnt;
    }

    public void setHorasEnt(double horasEnt) {
        this.horasEnt = horasEnt;
    }
    
    
    
}
