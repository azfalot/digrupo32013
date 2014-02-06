/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.util;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import jfxtras.labs.scene.control.window.WindowIcon;

/**
 *
 * @author Daniel Plaza
 */
public class MaximizeIcon extends WindowIcon {
    /*
    * Esta clase es un icono para maximizar ya que no esta implementado en jfxtras
    */
    private MyWindow w;
    
    public MaximizeIcon(final MyWindow w) {
        this.w=w;
        setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                w.maximize();
            }
        });
    }
    
    }


