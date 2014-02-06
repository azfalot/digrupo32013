/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaz.util;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import jfxtras.labs.scene.control.window.Window;

/**
 *
 * @author Grupo 3
 */
public class MyWindow extends Window{
    
    private boolean maximized;
    private double oldLayoutX,oldLayoutY,oldHeight,oldWidth;

    public MyWindow() {
        this.maximized=false;
        this.setFocusTraversable(true);
    }
      
    public boolean isMaximized() {
        return maximized;
    }
    
    public void maximize() {
        if(!maximized){
            maximized=true;
            oldLayoutX=this.getLayoutX();
            oldLayoutY=this.getLayoutY();
            oldHeight=this.getHeight();
            oldWidth=this.getWidth();
            AnchorPane.setTopAnchor(this,0.0);
            AnchorPane.setBottomAnchor(this,0.0);
            AnchorPane.setRightAnchor(this,0.0);
            AnchorPane.setLeftAnchor(this,0.0);
            this.setLayoutX(0);
            this.setLayoutY(0);
            this.setPrefSize(((AnchorPane)this.getParent()).getWidth(),((Pane)this.getParent()).getHeight());
            this.setMovable(false);
            this.setResizableWindow(false);
        }else{
            maximized=false;
            AnchorPane.clearConstraints(this);
            setResizableWindow(true);
            this.setLayoutX(oldLayoutX);
            this.setLayoutY(oldLayoutY);
            this.setPrefHeight(oldHeight);
            this.setPrefWidth(oldWidth);
            setMovable(true); 
        }
    }
    
    
}
