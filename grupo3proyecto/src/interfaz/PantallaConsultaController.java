/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaz;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import logica.Methods;

/**
 * FXML Controller class
 *
 * @author Grupo 3
 */
public class PantallaConsultaController implements Initializable {

    Methods m;
    
    
    public void builder(Methods m){
        this.m=m;
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
