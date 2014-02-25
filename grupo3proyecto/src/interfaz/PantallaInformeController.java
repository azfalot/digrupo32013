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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logica.Methods;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * FXML Controller class
 *
 * @author Grupo 3
 */
public class PantallaInformeController implements Initializable {
    
    @FXML
    Label label1;
    @FXML
    Label labelFI1;
    @FXML
    Label labelFF1;
    @FXML
    Label label2;
    @FXML
    Label labelFI2;
    @FXML
    Label labelFF2;
    @FXML
    Label label3;
    @FXML
    Label labelAno;
    @FXML
    Label labelMes;
    @FXML
    Label label4;
    @FXML
    Label label5;
    @FXML
    Label labelFI4;
    @FXML
    Label labelFF4;
    @FXML
    ComboBox comboAno;
    @FXML
    ComboBox comboMes;
    @FXML
    TabPane tabPane;
    @FXML
    Tab tab1;
    @FXML
    Tab tab2;
    @FXML
    Tab tab3;
    @FXML
    Tab tab4;
    @FXML
    Tab tab5;
    @FXML
    Button btnGenerar1;
    @FXML
    Button btnGenerar2;
    @FXML
    Button btnGenerar3;
    @FXML
    Button btnGenerar4;
    @FXML
    Button btnGenerar5;
    @FXML
    GridPane gridPane1Informe1;
    @FXML
    GridPane gridPane2Informe1;
    @FXML
    GridPane gridPane1Informe2;
    @FXML
    GridPane gridPane2Informe2;
    @FXML
    GridPane gridPane1Informe4;
    @FXML
    GridPane gridPane2Informe4;
    @FXML
    ComboBox comboTipoEntrenamiento;

    private Methods m;

    DatePicker datePicker1Informe1;
    DatePicker datePicker2Informe1;
    DatePicker datePicker1Informe2;
    DatePicker datePicker2Informe2;
    DatePicker datePicker1Informe3;
    DatePicker datePicker2Informe3;
    DatePicker datePicker1Informe4;
    DatePicker datePicker2Informe4;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public void builder(Methods m) {
        this.m = m;
        setDatePicker1Informe1();
        setDatePicker2Informe1();
        setDatePicker1Informe2();
        setDatePicker2Informe2();
        setDatePicker1Informe4();
        setDatePicker2Informe4();
        setCombos();
        translate();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setPaneSizes();
    }
    
    private void translate(){
        tab1.setText(m.write("report")+" "+1);
        tab2.setText(m.write("report")+" "+2);
        tab3.setText(m.write("report")+" "+3);
        tab4.setText(m.write("report")+" "+4);
        tab5.setText(m.write("report")+" "+5);
        btnGenerar1.setText(m.write("gen"));
        btnGenerar2.setText(m.write("gen"));
        btnGenerar3.setText(m.write("gen"));
        btnGenerar4.setText(m.write("gen"));
        btnGenerar5.setText(m.write("gen"));
        label1.setText(m.write("label1")+":");
        label2.setText(m.write("label2")+":");
        label3.setText(m.write("label3")+":");
        label4.setText(m.write("label4")+":");
        label5.setText(m.write("label5")+":");
        labelFI1.setText(m.write("fecha1")+":");
        labelFI2.setText(m.write("fecha1")+":");
        labelFI4.setText(m.write("fecha1")+":");
        labelFF1.setText(m.write("fecha2")+":");
        labelFF2.setText(m.write("fecha2")+":");
        labelFF4.setText(m.write("fecha2")+":");
        labelAno.setText(m.write("ano")+":");
        labelMes.setText(m.write("mes")+":");
        
    }

    private void setPaneSizes() {
        tabPane.setTabMinHeight(22);
        tabPane.setTabMaxHeight(22);
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
        datePicker2Informe1.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
        datePicker2Informe1.setPromptText("-- / -- / ----");
        datePicker2Informe1.getCalendarView().todayButtonTextProperty().set(m.write("today"));
        datePicker2Informe1.getCalendarView().setShowWeeks(false);
        datePicker2Informe1.getStylesheets().add("interfaz/util/DatePicker.css");
        gridPane2Informe1.add(datePicker2Informe1, 0, 0);
        ((TextField) datePicker2Informe1.getChildren().get(0)).setMaxWidth(73);//se ajusta el tamaño del textfield
        ((TextField) datePicker2Informe1.getChildren().get(0)).setEditable(false);//el textfield no sera editable
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

    private void setCombos() {
        comboTipoEntrenamiento.getItems().clear();
        comboTipoEntrenamiento.getItems().addAll(
                m.write("fisico"),
                m.write("roca"),
                m.write("rocodromo"));
        comboTipoEntrenamiento.getSelectionModel().select(0);

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        comboAno.getItems().clear();
        for (int year = 1980; year <= c.get(Calendar.YEAR); year++) {
            comboAno.getItems().add(year);
        }
        comboAno.getSelectionModel().selectLast();

        comboMes.getItems().clear();
        comboMes.getItems().addAll(
                m.write("enero"),
                m.write("febrero"),
                m.write("marzo"),
                m.write("abril"),
                m.write("mayo"),
                m.write("junio"),
                m.write("julio"),
                m.write("agosto"),
                m.write("septiembre"),
                m.write("octubre"),
                m.write("noviembre"),
                m.write("diciembre"));
        comboMes.getSelectionModel().select(0);

    }

    @FXML
    private void handleBtnGenerar1() {
        Map parametros = new HashMap();
        parametros.put("fechain", datePicker1Informe1.getSelectedDate());
        parametros.put("fechafin", datePicker2Informe1.getSelectedDate());

        File file = saveFile();

        if (file != null) {
            System.out.println("pasa1");
            JasperPrint print;
            try {
                print = JasperFillManager.fillReport("reports" + File.separator + "report1.jasper", parametros, m.getConnection());
                JasperExportManager.exportReportToPdfFile(print, file.getAbsolutePath()+".pdf");
                System.out.println("pasa2");
                System.out.println(file.getAbsolutePath());
            } catch (JRException ex) {
            }
        }

    }

    @FXML
    private void handleBtnGenerar2() {
        Map parametros = new HashMap();
        parametros.put("", "");

        File file = saveFile();

        if (file != null) {
            JasperPrint print;
            try {
                print = JasperFillManager.fillReport("reports" + File.separator + "report2.jasper", parametros, m.getConnection());
                JasperExportManager.exportReportToPdfFile(print, file.getAbsolutePath());
            } catch (JRException ex) {
            }
        }
    }

    @FXML
    private void handleBtnGenerar3() {
        Map parametros = new HashMap();
        parametros.put("", "");

        File file = saveFile();

        if (file != null) {
            JasperPrint print;
            try {
                print = JasperFillManager.fillReport("reports" + File.separator + "report3.jasper", parametros, m.getConnection());
                JasperExportManager.exportReportToPdfFile(print, file.getAbsolutePath());
            } catch (JRException ex) {
            }
        }
    }

    @FXML
    private void handleBtnGenerar4() {
        Map parametros = new HashMap();
        parametros.put("", "");

        File file = saveFile();

        if (file != null) {
            JasperPrint print;
            try {
                print = JasperFillManager.fillReport("reports" + File.separator + "report4.jasper", parametros, m.getConnection());
                JasperExportManager.exportReportToPdfFile(print, file.getAbsolutePath());
            } catch (JRException ex) {
            }
        }
    }

    @FXML
    private void handleBtnGenerar5() {
        File file = saveFile();
        if (file != null) {
            JasperPrint print;
            try {
                print = JasperFillManager.fillReport("reports" + File.separator + "report5.jasper",new HashMap(), m.getConnection());
                JasperExportManager.exportReportToPdfFile(print, file.getAbsolutePath());
            } catch (JRException ex) {
            }
        }
    }

    private File saveFile() {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            return file;
        }
        return null;
    }
}
