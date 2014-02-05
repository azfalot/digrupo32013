/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import eu.schudt.javafx.controls.calendar.DatePicker;
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
import jfxtras.labs.scene.control.window.Window;
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
    Button botonAlta;
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
    private Window w;

    public void builder(Methods m,Window w) {
        this.m = m;
        this.w=w;
        setDatePicker();
        setCombos();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
    @FXML
    private void handleBotonAceptar(){
        m.altaEntrenamiento(Integer.parseInt(comboHoraInicio.getSelectionModel().getSelectedItem().toString()), Integer.parseInt(comboMinInicio.getSelectionModel().getSelectedItem().toString()), Integer.parseInt(comboHoraFinal.getSelectionModel().getSelectedItem().toString()), Integer.parseInt(comboMinFinal.getSelectionModel().getSelectedItem().toString()),datePicker.getSelectedDate(), comboTipo.getSelectionModel().getSelectedItem().toString());
        w.close();
    }

    private void setCombos() {
        comboTipo.getItems().clear();
        comboTipo.getItems().addAll(
                "Fisico",
                "Rocódromo",
                "Roca"
        );
        comboMinInicio.getItems().clear();
        comboMinFinal.getItems().clear();
        comboHoraInicio.getItems().clear();
        comboHoraFinal.getItems().clear();
        
        for (int i = 0; i < 24; i++) {
            if(i<10){
                comboHoraInicio.getItems().add("0"+i);
                comboHoraFinal.getItems().add("0"+i);
                comboMinInicio.getItems().add("0"+i);
                comboMinFinal.getItems().add("0"+i);
            }else{
                comboHoraInicio.getItems().add(i);
                comboHoraFinal.getItems().add(i);
                comboMinInicio.getItems().add(i);
                comboMinFinal.getItems().add(i);
            }
        }
        
        for (int i = 24; i < 60; i++) {
            comboMinInicio.getItems().add(i);
            comboMinFinal.getItems().add(i);
        } 
        
        comboHoraInicio.getSelectionModel().select(0);
        comboMinInicio.getSelectionModel().select(0);
        comboHoraFinal.getSelectionModel().select(0);
        comboMinFinal.getSelectionModel().select(0);
        comboTipo.getSelectionModel().select(0);
    }

    private void setDatePicker() {
        datePicker = new DatePicker(new Locale("es", ""));
        datePicker.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
        datePicker.setPromptText("-- / -- / ----");
        datePicker.getCalendarView().todayButtonTextProperty().set("Hoy");
        datePicker.getCalendarView().setShowWeeks(false);
        datePicker.getStylesheets().add("interfaz/util/DatePicker.css");
        gridPane.add(datePicker, 0, 0);
        ((TextField) datePicker.getChildren().get(0)).setMaxWidth(73);//se ajusta el tamaño del textfield
        ((TextField) datePicker.getChildren().get(0)).setEditable(false);//el textfield no sera editable
    }

}
