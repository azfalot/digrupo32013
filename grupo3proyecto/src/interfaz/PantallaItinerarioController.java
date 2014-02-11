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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    ImageView imageView;
    @FXML
    ComboBox comboTipo;
    @FXML
    GridPane gridPane;
    @FXML
    GridPane gridPaneAutoFill;
    
    
    private File file;
    
    private Methods m;
    private Window w;
    
    private DatePicker datePicker;
    private AutoFillTextBox textLoca;
    
    private String imagePath="";

    public void builder(Methods m, Window w) {
        this.m = m;
        this.w = w;
        setDatePicker();
        setCombos();
        textLoca = new AutoFillTextBox(m.getLocalizaciones());
        gridPaneAutoFill.getChildren().add(textLoca);
        textLoca.setMaxWidth(140);
        imageView.setPreserveRatio(false);
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
            //m.altaItinerario();
            w.close();
        } catch (NullPointerException e) {
            ivError.setVisible(true);
        }
    }
    @FXML
    public void handleBotonExaminarAction(ActionEvent event){
        /*
        * Este metodo se encarga de seleccionar la imagen en la ruta especificada y previsualizarla.
        * Ademas guarda la ruta de la imagen para ser copiada posteriormente al aceptar
        */      
        FileChooser fileChooser = new FileChooser();
        //Filtros para la extension del archivo
        FileChooser.ExtensionFilter exjpg = new FileChooser.ExtensionFilter("JPEG (*.jpg)", "*.jpg", "*.jpeg");
        FileChooser.ExtensionFilter expng = new FileChooser.ExtensionFilter("PNG (*.png)", "*.png");
        FileChooser.ExtensionFilter exbmp = new FileChooser.ExtensionFilter("Mapa de bits"+" (*.bmp)", "*.bmp");
        FileChooser.ExtensionFilter exall = new FileChooser.ExtensionFilter("Archivos de imagen", "*.jpg", "*.png", "*.bmp", "*.jpeg");
        fileChooser.getExtensionFilters().add(exall);
        fileChooser.getExtensionFilters().add(exbmp);
        fileChooser.getExtensionFilters().add(exjpg);
        fileChooser.getExtensionFilters().add(expng);
        //Abre la ventana del filechooser
        file = fileChooser.showOpenDialog(null);
        //Guarda la ruta de la imagen en una variable y la escribe en el TextField
        try{
        textRutaFoto.setText(file.getPath());
        imagePath=file.getPath();
        }catch(NullPointerException e){}   
        //Previsualiza la imagen
        Image img=null;
        try {
            img = new Image(new FileInputStream(file));
        } catch (FileNotFoundException ex) {
        }
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
