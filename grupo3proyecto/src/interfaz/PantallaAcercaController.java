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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logica.Methods;

/**
 * FXML Controller class
 *
 * @author Grupo 3
 */
public class PantallaAcercaController implements Initializable {
    
    @FXML
    Label labelAppName;
    @FXML
    ImageView ivIcono;
    @FXML
    Button botonAceptar;
    @FXML
    Label labelGrupo;
    
    Methods m;  
    MyWindow w;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelAppName.setText(Escalada.applicationName);
        ivIcono.setImage(new Image("file:resources"+ File.separator + "icon.png"));
    }    
    
    public void builder(Methods m,MyWindow w){
        this.m=m;
        this.w=w;
        translate();
    }
    
    private void translate(){
        botonAceptar.setText(m.write("acept"));
        labelGrupo.setText(m.write("group")+":");
    }
    
    @FXML
    private void handleBotonAceptar(){
        w.close();
    }
    
}
