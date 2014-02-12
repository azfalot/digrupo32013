/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaz;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;

/**
 * FXML Controller class
 *
 * @author Grupo 3
 */
public class PantallaConfiguracionController implements Initializable {

    @FXML
    TabPane tabPane;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setPaneSizes();
    }    
    
    private void setPaneSizes(){
        tabPane.setTabMinHeight(22);
        tabPane.setTabMaxHeight(22);
    }
}
