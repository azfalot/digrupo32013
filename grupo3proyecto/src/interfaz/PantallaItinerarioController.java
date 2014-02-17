/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import eu.schudt.javafx.controls.calendar.DatePicker;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
import javafx.scene.control.Label;
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
    ImageView ivErrorNombre;
    @FXML
    ImageView ivErrorLoc;
    @FXML
    ImageView ivErrorFecha;
    @FXML
    Button botonAlta;
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
    @FXML
    Label labelNombre;
    @FXML
    Label labelLocalizacion;
    @FXML
    Label labelTipo;
    @FXML
    Label labelDificultad;
    @FXML
    Label labelFechaResolucion;
    @FXML
    Label labelFotografia;
    @FXML
    Label labelSinImagen;
    
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
        translate();
    }
    
    private void translate(){
        labelNombre.setText(m.write("name")+":");
        labelLocalizacion.setText(m.write("loc")+":");
        labelTipo.setText(m.write("l_tipo")+":");
        labelDificultad.setText(m.write("l_dificultad")+":");
        labelFechaResolucion.setText(m.write("l_fecharesolucion")+":");
        labelFotografia.setText(m.write("l_fotografia"));
        botonExaminar.setText(m.write("b_examinar"));
        labelSinImagen.setText(m.write("l_sin_imagen"));
        botonAlta.setText(m.write("acept"));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setErrors();
        /*
        * Limitamos el texto del TextField a 30
        */
        textNombre.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (t1.length() > 30) {
                    textNombre.setText(t);
                }
            }
        });
    }

    @FXML
    private void handleBotonAceptar() {
        /*
        * Controla los campos vacios y si no hay errores da de alta el itinerario
        */
            boolean err=false;
            hideErrors();
            if("".equals(textNombre.getText())){
                ivErrorNombre.setVisible(true);
                err=true;
            }
            if("".equals(textLoca.getText())){
                ivErrorLoc.setVisible(true);
                err=true;
            }
            if("".equals(((TextField) datePicker.getChildren().get(0)).getText())){
                ivErrorFecha.setVisible(true);
                err=true;
            }
            if(err==false){
                m.altaItinerario(textNombre.getText(),comboDificultad.getSelectionModel().getSelectedItem().toString(),textLoca.getText(), comboTipo.getSelectionModel().getSelectedIndex(), imagePath, datePicker.getSelectedDate());
                w.close();
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
        FileChooser.ExtensionFilter exbmp = new FileChooser.ExtensionFilter(m.write("bitmap")+" (*.bmp)", "*.bmp");
        FileChooser.ExtensionFilter exall = new FileChooser.ExtensionFilter(m.write("image_files"), "*.jpg", "*.png", "*.bmp", "*.jpeg");
        fileChooser.getExtensionFilters().add(exall);
        fileChooser.getExtensionFilters().add(exbmp);
        fileChooser.getExtensionFilters().add(exjpg);
        fileChooser.getExtensionFilters().add(expng);
        //Abre la ventana del filechooser
        file = fileChooser.showOpenDialog(null);
        //Guarda la ruta de la imagen en una variable y la escribe en el TextField
        try{
            imagePath=file.getAbsolutePath();
        }catch(NullPointerException e){}   
        //Previsualiza la imagen
        Image img=null;
        try {
            img = new Image(new FileInputStream(file));
        } catch (IOException e) {
        }
        imageView.setImage(img);

    }
    
    private void setErrors(){
        /*
         * Se les asigna una imagen a los errores y se ocultan
         */
        ivErrorNombre.setImage(new Image("file:resources" + File.separator + "wrong.png"));
        ivErrorLoc.setImage(new Image("file:resources" + File.separator + "wrong.png"));
        ivErrorFecha.setImage(new Image("file:resources" + File.separator + "wrong.png"));
        hideErrors();
    }
    
    private void hideErrors(){
        ivErrorNombre.setVisible(false);
        ivErrorLoc.setVisible(false);
        ivErrorFecha.setVisible(false);
    }

    private void setCombos() {
        /*
         * Este metodo rellena los ComboBox
         */
        
        comboTipo.getItems().clear();
        comboTipo.getItems().addAll(
                m.write("via"),
                m.write("boulder")
                        );
        comboDificultad.getItems().clear();
        comboDificultad.setItems(m.getDificultades());
        
        //Se selecciona el primer valor por defecto
        comboDificultad.getSelectionModel().select(0);
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
