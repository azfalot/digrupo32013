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
public class AltaEntrenamientoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Button buttonAlta;
    @FXML
    TextField textFieldFecha;
    @FXML
    ComboBox comboHoraInicio;
    @FXML
    ComboBox comboHoraFinal;
    @FXML
    ComboBox comboMinFinal;
    @FXML
    ComboBox comboMinInicio;
    @FXML
    TextArea textAreaDescripcion;
    @FXML
    ComboBox comboTipo;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboTipo.getItems().clear();
        comboTipo.getItems().addAll(
                "Fisico",
                "Rocódromo",
                "Roca"
                );
        comboMinInicio.getItems().clear();
        for(int i=0;i<60;i++){
        comboMinInicio.getItems().add(i);
                }
        comboMinFinal.getItems().clear();
        for(int i=0;i<60;i++){
        comboMinFinal.getItems().add(i);
                }
        comboHoraInicio.getItems().clear();
        for(int i=0;i<24;i++){
        comboHoraInicio.getItems().add(i);
                }
        comboHoraFinal.getItems().clear();
        for(int i=0;i<24;i++){
        comboHoraFinal.getItems().add(i);
                }
        comboHoraInicio.getSelectionModel().select(1);
        comboMinInicio.getSelectionModel().select(1);
        comboHoraFinal.getSelectionModel().select(1);
        comboMinFinal.getSelectionModel().select(1);
        comboTipo.getSelectionModel().select(1);
        
    }

}
