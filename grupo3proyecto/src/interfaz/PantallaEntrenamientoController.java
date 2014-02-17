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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    ImageView ivError;
    @FXML
    Button botonAceptar;
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
    Label labelFecha;
    @FXML
    Label labelHoraInicio;
    @FXML
    Label labelHoraFin;
    @FXML
    Label labelTipo;
    @FXML
    Label labelDescripcion;
    @FXML
    private GridPane gridPane;

    private DatePicker datePicker;
    private Methods m;
    private Window w;

    public void builder(Methods m, Window w) {
        this.m = m;
        this.w = w;
        setDatePicker();
        setCombos();
        translate();
    }
    
    public void translate(){
        botonAceptar.setText(m.write("acept"));
        labelDescripcion.setText(m.write("description"));
        labelFecha.setText(m.write("l_date_session")+":");
        labelHoraFin.setText(m.write("l_horafin")+":");
        labelHoraInicio.setText(m.write("l_horainicio")+":");
        labelTipo.setText(m.write("l_type")+":");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        /*
        * Limitamos el texto del TextArea a 255
        */
        textAreaDescripcion.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (t1.length() > 255) {
                    textAreaDescripcion.setText(t);
                }
            }
        });
        /*
        * ivError: Se le asigna una imagen y se oculta
        */
        ivError.setImage(new Image("file:resources" + File.separator + "wrong.png"));
        ivError.setVisible(false);
    }
    
    @FXML
    private void handleBotonAceptar() {
        try{
            ivError.setVisible(false);
            m.altaEntrenamiento(comboHoraInicio.getSelectionModel().getSelectedItem().toString(), comboMinInicio.getSelectionModel().getSelectedItem().toString(), comboHoraFinal.getSelectionModel().getSelectedItem().toString(), comboMinFinal.getSelectionModel().getSelectedItem().toString(), datePicker.getSelectedDate(), comboTipo.getSelectionModel().getSelectedIndex(), textAreaDescripcion.getText());
            w.close();
        }catch(NullPointerException e){
            ivError.setVisible(true);
        }
    }
    
    private void setCombos() {
        /*
        * Este metodo rellena los ComboBox
        */
        comboTipo.getItems().clear();
        comboTipo.getItems().addAll(
                m.write("fisico"),
                m.write("roca"),
                m.write("rocodromo")
        );
        comboMinInicio.getItems().clear();
        comboMinFinal.getItems().clear();
        comboHoraInicio.getItems().clear();
        comboHoraFinal.getItems().clear();

        for (int i = 0; i < 24; i++) {
            if (i < 10) {
                comboHoraInicio.getItems().add("0" + i);
                comboHoraFinal.getItems().add("0" + i);
                comboMinInicio.getItems().add("0" + i);
                comboMinFinal.getItems().add("0" + i);
            } else {
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
        //Se selecciona el primer valor por defecto
        comboHoraInicio.getSelectionModel().select(0);
        comboMinInicio.getSelectionModel().select(0);
        comboHoraFinal.getSelectionModel().select(0);
        comboMinFinal.getSelectionModel().select(0);
        comboTipo.getSelectionModel().select(0);
    }

    private void setDatePicker() {
        /*
         * Este metodo asigna el componente calendario al gridpane
         * Aparecera un textfield donde haciendo click se desplegara el calendario
         */
        datePicker = new DatePicker(new Locale(m.write("language"), m.write("language0")));
        datePicker.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
        datePicker.setPromptText("-- / -- / ----");
        datePicker.getCalendarView().todayButtonTextProperty().set(m.write("today"));
        datePicker.getCalendarView().setShowWeeks(false);
        datePicker.getStylesheets().add("interfaz/util/DatePicker.css");
        gridPane.add(datePicker, 0, 0);
        ((TextField) datePicker.getChildren().get(0)).setMaxWidth(73);//se ajusta el tamaño del textfield
        ((TextField) datePicker.getChildren().get(0)).setEditable(false);//el textfield no sera editable
    }

}
