/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import eu.schudt.javafx.controls.calendar.DatePicker;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import logica.Methods;

/**
 * FXML Controller class
 *
 * @author Grupo 3
 */
public class PantallaEntrenamientoController implements Initializable {

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
    @FXML
    private GridPane gridPane;
    
    private DatePicker datePicker;
    private Methods m;
    
    public void builder(Methods m){
        this.m=m;
        setDatePicker();
        setCombos();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    }
    
    private void setCombos(){
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
    
    private void setDatePicker(){
        datePicker = new DatePicker(new Locale("es",""));
        datePicker.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
        datePicker.setPromptText("-- / -- / ----");
        datePicker.getCalendarView().todayButtonTextProperty().set("Hoy");
        datePicker.getCalendarView().setShowWeeks(false);
        datePicker.getStylesheets().add("interfaz/DatePicker.css");
        gridPane.add(datePicker, 0, 0);
        ((TextField)datePicker.getChildren().get(0)).setMaxWidth(73);//se ajusta el tamaño del textfield
        ((TextField)datePicker.getChildren().get(0)).setEditable(false);//el textfield no sera editable
    }

}
