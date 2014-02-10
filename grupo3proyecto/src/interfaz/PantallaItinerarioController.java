/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import eu.schudt.javafx.controls.calendar.DatePicker;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import jfxtras.labs.scene.control.window.Window;
import logica.Methods;
import np.com.ngopal.control.AutoFillTextBox;

/**
 * FXML Controller class
 *
 * @author Grupo 3
 */
public class PantallaItinerarioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    ImageView ivError;
    @FXML
    Button botonAlta;
    @FXML
    TextField textRutaFoto;
    @FXML
    TextField textNombre;
    @FXML
    ComboBox comboDificultad;
    @FXML
    Button botonExaminar;
    @FXML
    private ImageView imageView;
    @FXML
    ComboBox comboTipo;
    @FXML
    private GridPane gridPane;
    @FXML
    GridPane gridPaneAutoFill;
    File file;

    private DatePicker datePicker;
    private Methods m;
    private Window w;
    private AutoFillTextBox textLoca;

    public void builder(Methods m, Window w) {
        this.m = m;
        this.w = w;
        setDatePicker();
        setCombos();
        textLoca = new AutoFillTextBox(m.getLocalizaciones());
        gridPaneAutoFill.getChildren().add(textLoca);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        /*
         * ivError: Se le asigna una imagen y se oculta
         */
        ivError.setImage(new Image("file:resources" + File.separator + "wrong.png"));
        ivError.setVisible(false);
    }

    @FXML
    private void handleBotonAceptar() {
        try {
            ivError.setVisible(false);
            w.close();
        } catch (NullPointerException e) {
            ivError.setVisible(true);
        }
    }
    @FXML
    public void handleBotonExaminarAction(ActionEvent event) throws FileNotFoundException {
        //Este metodo se encarga de seleccionar la imagen en la ruta especificada y previsualizarla.
        
        FileChooser fileChooser = new FileChooser();
        //Filtro para la extension del archivo
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("*JPEG Image", "*.jpg");
        fileChooser.getExtensionFilters().add(extFilter);
        
        file = fileChooser.showOpenDialog(null);
        textRutaFoto.setText(file.getPath());
        
        Image img = new Image(new FileInputStream(file));
        imageView.setImage(img);
    }

    private void setCombos() {
        /*
         * Este metodo rellena los ComboBox
         */
        
        comboTipo.getItems().clear();
        comboTipo.getItems().addAll(
                "Vía de escalada",
                "Boulder"
                        );
        comboDificultad.getItems().clear();
        comboDificultad.getItems().addAll(
                "1",
                "2",
                "Manu rellena aqui"
        );
        //Se selecciona el primer valor por defecto
        comboDificultad.getSelectionModel().select(0);
        comboTipo.getSelectionModel().select(0);
    }

    private void setDatePicker() {
        /*
         * Este metodo asigna el componente calendario al gridpane
         * Aparecera un textfield donde haciendo click se desplegara el calendario
         */
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
