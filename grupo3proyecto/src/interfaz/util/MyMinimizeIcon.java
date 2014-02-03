/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaz.util;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import jfxtras.labs.scene.control.window.MinimizeIcon;
import jfxtras.labs.scene.control.window.Window;

/**
 *
 * @author USUARIO
 */
public class MyMinimizeIcon extends MinimizeIcon {
    
    private Window w;
    
    public MyMinimizeIcon(final Window w) {
        super(w);
        setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                w.setVisible(false);
            }
        });
    }
    
    }
