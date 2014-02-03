/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaz.util;

import javafx.scene.layout.Pane;
import jfxtras.labs.scene.control.window.Window;

/**
 *
 * @author Grupo 3
 */
public class MyWindow extends Window{
    
    
    /*
    * EN CONSTRUCCION
    *
    * ESTOY HACIENDO ESTA CLASE PARA PODER MAXIMIZAR VENTANAS EN CASO DE QUE NOS INTERESE
    */
    
    private boolean maximized;
    private final Pane parent;

    public MyWindow(Pane parent) {
        this.parent=parent;
    }
      

    public boolean isMaximized() {
        return maximized;
    }

    public void setMaximized(boolean maximized) {
        if(maximized){
            this.setLayoutX(0);
            this.setLayoutY(0);
            this.setPrefSize(parent.getHeight(),parent.getWidth());
        }else{
            
        }
    }
    
    
}
