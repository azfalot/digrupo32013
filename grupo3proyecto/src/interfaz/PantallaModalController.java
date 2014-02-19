/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaz;

import interfaz.util.ModalListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import jfxtras.labs.scene.control.window.Window;
import logica.Methods;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class PantallaModalController implements Initializable {
    
    @FXML
    ImageView ivHelp;
    @FXML
    Label labelTexto;
    @FXML
    Button botonAceptar;
    @FXML
    Button botonCancelar;
    
    private Window w;
    ModalListener mAceptar;
    
    public void builder(Methods m,String mensaje,Window w,ModalListener mAceptar){
        labelTexto.setText(mensaje);
        botonAceptar.setText(m.write("acept"));
        botonCancelar.setText(m.write("cancel"));
        this.w=w;
        this.mAceptar=mAceptar;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    private void setImage(){
        ivHelp.setImage(new Image(getClass().getResourceAsStream("resources/help.png")));
    }
    
    /*
    * handles
    */
    @FXML
    private void handleCancelar(){
        w.close();
    }
    
    @FXML
    private void handleAceptar(){
        mAceptar.onAction();
        w.close();
    }
    
}
