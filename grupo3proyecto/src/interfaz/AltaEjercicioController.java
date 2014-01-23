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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Angel
 */
public class AltaEjercicioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Button buttonAlta;
    @FXML
    TextField textFieldFecha;
    @FXML
    TextField textFieldHoraO;
    @FXML
    TextArea textAreaDescripcion;
    @FXML
    ComboBox comboTipo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
