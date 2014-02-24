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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import logica.Methods;
import np.com.ngopal.control.AutoFillTextBox;

/**
 * FXML Controller class
 *
 * @author Rafa
 */
public class PantallaInformeController implements Initializable {

    @FXML
    Button btnGenerar1;
    @FXML
    Button btnGenerar2;
    @FXML
    GridPane gridPane1Informe1;
    @FXML
    GridPane gridPane2Informe1;
    @FXML
    GridPane gridPane1Informe2;
    @FXML
    GridPane gridPane2Informe2;
    @FXML
    GridPane gridPane1Informe3;
    @FXML
    GridPane gridPane2Informe3;
    @FXML
    GridPane gridPane1Informe4;
    @FXML
    GridPane gridPane2Informe4;
    @FXML
    ComboBox  comboTipoEntrenamiento;
    @FXML
    ComboBox  comboDificultad;
    
    private Methods m;
    
    DatePicker datePicker1Informe1;
    DatePicker datePicker2Informe1;
    DatePicker datePicker1Informe2;
    DatePicker datePicker2Informe2;
    DatePicker datePicker1Informe3;
    DatePicker datePicker2Informe3;
    DatePicker datePicker1Informe4;
    DatePicker datePicker2Informe4;
    
public void builder(Methods m) {
        this.m = m;
        setDatePicker1Informe1();
        setDatePicker2Informe1();
        setDatePicker1Informe2();
        setDatePicker2Informe2();
        setDatePicker1Informe3();
        setDatePicker2Informe3();
        setDatePicker1Informe4();
        setDatePicker2Informe4();
        setCombos();

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
 private void setDatePicker1Informe1() {
        /*
         * Este metodo asigna el componente calendario al gridpane
         * Aparecera un textfield donde haciendo click se desplegara el calendario
         */
        datePicker1Informe1 = new DatePicker(new Locale(m.write("language"), m.write("language0")));
        datePicker1Informe1.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
        datePicker1Informe1.setPromptText("-- / -- / ----");
        datePicker1Informe1.getCalendarView().todayButtonTextProperty().set(m.write("today"));
        datePicker1Informe1.getCalendarView().setShowWeeks(false);
        datePicker1Informe1.getStylesheets().add("interfaz/util/DatePicker.css");
        gridPane1Informe1.add(datePicker1Informe1, 0, 0);
        ((TextField) datePicker1Informe1.getChildren().get(0)).setMaxWidth(73);//se ajusta el tamaño del textfield
        ((TextField) datePicker1Informe1.getChildren().get(0)).setEditable(false);//el textfield no sera editable
    }
  private void setDatePicker2Informe1() {
        /*
         * Este metodo asigna el componente calendario al gridpane
         * Aparecera un textfield donde haciendo click se desplegara el calendario
         */
        datePicker2Informe1 = new DatePicker(new Locale(m.write("language"), m.write("language0")));
        datePicker2Informe1 .setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
        datePicker2Informe1 .setPromptText("-- / -- / ----");
        datePicker2Informe1 .getCalendarView().todayButtonTextProperty().set(m.write("today"));
        datePicker2Informe1 .getCalendarView().setShowWeeks(false);
        datePicker2Informe1 .getStylesheets().add("interfaz/util/DatePicker.css");
        gridPane2Informe1.add(datePicker2Informe1 , 0, 0);
        ((TextField) datePicker2Informe1 .getChildren().get(0)).setMaxWidth(73);//se ajusta el tamaño del textfield
        ((TextField) datePicker2Informe1 .getChildren().get(0)).setEditable(false);//el textfield no sera editable
    }
  private void setDatePicker1Informe2() {
        /*
         * Este metodo asigna el componente calendario al gridpane
         * Aparecera un textfield donde haciendo click se desplegara el calendario
         */
        datePicker1Informe2 = new DatePicker(new Locale(m.write("language"), m.write("language0")));
        datePicker1Informe2.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
        datePicker1Informe2.setPromptText("-- / -- / ----");
        datePicker1Informe2.getCalendarView().todayButtonTextProperty().set(m.write("today"));
        datePicker1Informe2.getCalendarView().setShowWeeks(false);
        datePicker1Informe2.getStylesheets().add("interfaz/util/DatePicker.css");
        gridPane1Informe2.add(datePicker1Informe2, 0, 0);
        ((TextField) datePicker1Informe2.getChildren().get(0)).setMaxWidth(73);//se ajusta el tamaño del textfield
        ((TextField) datePicker1Informe2.getChildren().get(0)).setEditable(false);//el textfield no sera editable
    }
  private void setDatePicker2Informe2() {
        /*
         * Este metodo asigna el componente calendario al gridpane
         * Aparecera un textfield donde haciendo click se desplegara el calendario
         */
        datePicker2Informe2 = new DatePicker(new Locale(m.write("language"), m.write("language0")));
        datePicker2Informe2.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
        datePicker2Informe2.setPromptText("-- / -- / ----");
        datePicker2Informe2.getCalendarView().todayButtonTextProperty().set(m.write("today"));
        datePicker2Informe2.getCalendarView().setShowWeeks(false);
        datePicker2Informe2.getStylesheets().add("interfaz/util/DatePicker.css");
        gridPane2Informe2.add(datePicker2Informe2, 0, 0);
        ((TextField) datePicker2Informe2.getChildren().get(0)).setMaxWidth(73);//se ajusta el tamaño del textfield
        ((TextField) datePicker2Informe2.getChildren().get(0)).setEditable(false);//el textfield no sera editable
    }
  private void setDatePicker1Informe4() {
        /*
         * Este metodo asigna el componente calendario al gridpane
         * Aparecera un textfield donde haciendo click se desplegara el calendario
         */
        datePicker1Informe4 = new DatePicker(new Locale(m.write("language"), m.write("language0")));
        datePicker1Informe4.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
        datePicker1Informe4.setPromptText("-- / -- / ----");
        datePicker1Informe4.getCalendarView().todayButtonTextProperty().set(m.write("today"));
        datePicker1Informe4.getCalendarView().setShowWeeks(false);
        datePicker1Informe4.getStylesheets().add("interfaz/util/DatePicker.css");
        gridPane1Informe4.add(datePicker1Informe4, 0, 0);
        ((TextField) datePicker1Informe4.getChildren().get(0)).setMaxWidth(73);//se ajusta el tamaño del textfield
        ((TextField) datePicker1Informe4.getChildren().get(0)).setEditable(false);//el textfield no sera editable
    }
  private void setDatePicker2Informe4() {
        /*
         * Este metodo asigna el componente calendario al gridpane
         * Aparecera un textfield donde haciendo click se desplegara el calendario
         */
        datePicker2Informe4 = new DatePicker(new Locale(m.write("language"), m.write("language0")));
        datePicker2Informe4.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
        datePicker2Informe4.setPromptText("-- / -- / ----");
        datePicker2Informe4.getCalendarView().todayButtonTextProperty().set(m.write("today"));
        datePicker2Informe4.getCalendarView().setShowWeeks(false);
        datePicker2Informe4.getStylesheets().add("interfaz/util/DatePicker.css");
        gridPane2Informe4.add(datePicker2Informe4, 0, 0);
        ((TextField) datePicker2Informe4.getChildren().get(0)).setMaxWidth(73);//se ajusta el tamaño del textfield
        ((TextField) datePicker2Informe4.getChildren().get(0)).setEditable(false);//el textfield no sera editable
    }
  private void setDatePicker1Informe3() {
        /*
         * Este metodo asigna el componente calendario al gridpane
         * Aparecera un textfield donde haciendo click se desplegara el calendario
         */
        datePicker1Informe3 = new DatePicker(new Locale(m.write("language"), m.write("language0")));
        datePicker1Informe3.setDateFormat(new SimpleDateFormat("MM"));
        datePicker1Informe3.setPromptText("--");
        datePicker1Informe3.getCalendarView().todayButtonTextProperty().set(m.write("today"));
        datePicker1Informe3.getCalendarView().setShowWeeks(false);
        datePicker1Informe3.getStylesheets().add("interfaz/util/DatePicker.css");
        gridPane1Informe3.add(datePicker1Informe3, 0, 0);
        ((TextField) datePicker1Informe3.getChildren().get(0)).setMaxWidth(73);//se ajusta el tamaño del textfield
        ((TextField) datePicker1Informe3.getChildren().get(0)).setEditable(false);//el textfield no sera editable
    }
  private void setDatePicker2Informe3() {
        /*
         * Este metodo asigna el componente calendario al gridpane
         * Aparecera un textfield donde haciendo click se desplegara el calendario
         */
        datePicker2Informe3 = new DatePicker(new Locale(m.write("language"), m.write("language0")));
        datePicker2Informe3 .setDateFormat(new SimpleDateFormat("yyyy"));
        datePicker2Informe3 .setPromptText("----");
        datePicker2Informe3 .getCalendarView().todayButtonTextProperty().set(m.write("today"));
        datePicker2Informe3 .getCalendarView().setShowWeeks(false);
        datePicker2Informe3 .getStylesheets().add("interfaz/util/DatePicker.css");
        gridPane2Informe3.add(datePicker2Informe3 , 0, 0);
        ((TextField) datePicker2Informe3 .getChildren().get(0)).setMaxWidth(73);//se ajusta el tamaño del textfield
        ((TextField) datePicker2Informe3 .getChildren().get(0)).setEditable(false);//el textfield no sera editable
    }
  private void setCombos(){
      comboTipoEntrenamiento.getItems().clear();
        comboTipoEntrenamiento.getItems().addAll(
                m.write("fisico"),
                m.write("roca"),
                m.write("rocodromo"));
        comboTipoEntrenamiento.getSelectionModel().select(0);
        
        comboDificultad.getItems().clear();
        comboDificultad.setItems(m.getDificultades());

        //Se selecciona el primer valor por defecto
        comboDificultad.getSelectionModel().select(0);
  }
  private void setCombosItinerario() {
        /*
         * Este metodo rellena los ComboBox
         */

        
        
        
    }
}
