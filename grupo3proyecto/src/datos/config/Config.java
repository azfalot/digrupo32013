/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author Daniel Plaza
 */
public class Config implements Serializable {

    private String language;
    private int defaultUser;

    public Config() {
        ObjectInputStream ois = null;
        Config c = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("config.cfg"));
            c = (Config) ois.readObject();
            this.language = c.getLanguage();
            this.defaultUser = c.getDefaultUser();
        } catch (IOException | ClassNotFoundException ex) {
            generate();
        }
    }

    public Config(String language, int defaultUser) {
        this.language = language;
        this.defaultUser = defaultUser;
    }

    private void generate() {
        /*
        * genera un nuevo archivo de configuracion en caso de que no exista o
        * este dañado
        */
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("config.cfg"));
            Config defaultConfig=new Config("spanish", 0);//CAMBIAR POR english
            oos.writeObject(defaultConfig); 
            this.language=defaultConfig.getLanguage();
            this.defaultUser=defaultConfig.getDefaultUser();
        } catch (IOException ex) {
        }

    }

    private void save() {
        /*
        * guarda en el archivo la nueva configuracion
        */
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("config.cfg"));
            oos.writeObject(this);
        } catch (IOException ex) {
        }
    }

    public String getLanguage() {
        return language;
    }

    public int getDefaultUser() {
        return defaultUser;
    }

    public void setLanguage(String language) {
        this.language = language;
        save();
    }

    public void setDefaultUser(int defaultUser) {
        this.defaultUser = defaultUser;
        save();
    }

}
