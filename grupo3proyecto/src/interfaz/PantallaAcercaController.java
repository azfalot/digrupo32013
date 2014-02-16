/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaz;

import interfaz.util.MyWindow;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logica.Methods;

/**
 * FXML Controller class
 *
 * @author plantaNieves
 */
public class PantallaAcercaController implements Initializable {
    
    @FXML
    Label labelAppName;
    @FXML
    ImageView ivIcono;
    
    Methods m;  
    MyWindow w;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelAppName.setText("Escalator 3000");
        ivIcono.setImage(new Image("file:resources"+ File.separator + "icon.png"));
    }    
    
    public void builder(Methods m,MyWindow w){
        this.m=m;
        this.w=w;
    }
    
    @FXML
    private void handleBotonAceptar(){
        w.close();
    }
    
}
