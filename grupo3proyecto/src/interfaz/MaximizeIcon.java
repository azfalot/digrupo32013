/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import jfxtras.labs.scene.control.window.MinimizeIcon;
import jfxtras.labs.scene.control.window.Window;

/**
 *
 * @author Daniel Plaza
 */
public class MaximizeIcon extends MinimizeIcon {
    
    private Window w;
    
    public MaximizeIcon(final Window w) {
        super(w);
        setStyle("-fx-background-image: url('resources" + File.separator + "maximize.png'); -fx-background-position: center center;");

        setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                AnchorPane desktop = (AnchorPane) w.getParent();
                w.toFront();
                w.setLayoutX(0);
                w.setLayoutY(0);
                w.setPrefSize(desktop.getWidth(), desktop.getHeight());
            }
        });
    }
    
    }


