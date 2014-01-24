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
    private int edad;
    private float altura,peso;

    public Usuario() {
        this.id=0;
    }
    
    public Usuario(int id, String nombre, int edad, float altura, float peso) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.altura = altura;
        this.peso = peso;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public float getAltura() {
        return altura;
    }

    public float getPeso() {
        return peso;
    }
    
    

}
