/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import datos.conexionbd.POJOS.Entrenamiento;
import datos.conexionbd.POJOS.Itinerario;
import eu.schudt.javafx.controls.calendar.DatePicker;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import logica.Methods;
import np.com.ngopal.control.AutoFillTextBox;

/**
 * FXML Controller class
 *
 * @author Grupo 3
 */
public class PantallaDatosController implements Initializable {

    @FXML
    TabPane tabPane;
    @FXML
    TableView tablaEntrenamiento;
    @FXML
    TableView tablaItinerario;
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
    ComboBox comboTipoEntrenamiento;
    @FXML
    ComboBox comboDificultad;
    @FXML
    ComboBox comboTipoItinerario;
    @FXML
    private GridPane gridPaneEntrenamiento;
    @FXML
    private GridPane gridPaneItinerario;
    @FXML
    private GridPane gridPaneAutoFill;
    @FXML
    TextField textNombre;
    @FXML
    ImageView imageView;
    
    @FXML
    Pane paneEntrenamiento;
    @FXML
    Pane paneItinerario;
    private AutoFillTextBox textLoca;
    private DatePicker datePicker;
    Methods m;

    /*
     * INITIALIZE
     */
    public void builder(Methods m) {
        this.m = m;
        formatTablaEntrenamiento();
        formatTablaItinerario();
        setDatePickerItinerario();
        setDatePickerEntrenamiento();
        setCombosEntrenamiento();
        setCombosItinerario();
        setOnClick();
        textLoca = new AutoFillTextBox(m.getLocalizaciones());
        gridPaneAutoFill.getChildren().add(textLoca);
        textLoca.setMaxWidth(140);
        imageView.setPreserveRatio(false);
        paneEntrenamiento.setVisible(false);
        paneItinerario.setVisible(false);



    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setPaneSizes();


    }

    private void setPaneSizes() {
        //Ajusta el tamaño de las pestañas
        tabPane.setTabMinHeight(22);
        tabPane.setTabMaxHeight(22);
    }

    /*
     * HANDLES
     */
    /*
     * TABLA
     */
    //ejemplos sacados de la practica 2 (obras de arte)
    public void refreshTabla() {
//        formatTabla();
//        for (Obra obra : metodos.getObras()) {
//            tabla.getItems().add(obra);
//        }
    }

    private void formatTablaEntrenamiento() {
        //borra la tabla (columnas y datos)
        tablaEntrenamiento.getColumns().clear();
        tablaEntrenamiento.getItems().clear();
//
//        //crea las nuevas columnas
        TableColumn idCol = new TableColumn("Id Entrenamiento");
        idCol.setCellValueFactory(new PropertyValueFactory<Entrenamiento, Integer>("p_entrenamiento"));
        TableColumn horaInicioCol = new TableColumn("Hora Inicio");
        horaInicioCol.setCellValueFactory(new PropertyValueFactory<Entrenamiento, Date>("hora_inicio"));
        TableColumn horaFinCol = new TableColumn("Hora Fin");
        horaFinCol.setCellValueFactory(new PropertyValueFactory<Entrenamiento, Date>("hora_fin"));
        TableColumn fechaCol = new TableColumn("Fecha");
        fechaCol.setCellValueFactory(new PropertyValueFactory<Entrenamiento, Date>("fecha"));
        TableColumn descripcionCol = new TableColumn("Descripcion");
        descripcionCol.setCellValueFactory(new PropertyValueFactory<Entrenamiento, String>("descripcion"));
        TableColumn tipoCol = new TableColumn("Tipo");
        tipoCol.setCellValueFactory(new PropertyValueFactory<Entrenamiento, String>("tipoStr"));
//        
//
        //define la tabla como no editable
        tablaEntrenamiento.setEditable(false);
        //añade las columnas creadas
        tablaEntrenamiento.getColumns().addAll(idCol, horaInicioCol, horaFinCol, fechaCol, descripcionCol, tipoCol);
        //hace que las columnas ocupen todo el espacio reservado para la tabla
        tablaEntrenamiento.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //rellena la tabla con los datos
        tablaEntrenamiento.getItems().clear();

        for (Iterator it = m.getEntrenamientos().iterator(); it.hasNext();) {
            Entrenamiento e = (Entrenamiento) it.next();
            tablaEntrenamiento.getItems().add(e);

        }
    }

    private void formatTablaItinerario() {
        //borra la tabla (columnas y datos)
        tablaItinerario.getColumns().clear();
        tablaItinerario.getItems().clear();
//
//        //crea las nuevas columnas
        TableColumn idCol = new TableColumn("Id Itinerario");
        idCol.setCellValueFactory(new PropertyValueFactory<Entrenamiento, Integer>("p_itinerario"));
        TableColumn nombreCol = new TableColumn("Nombre");
        nombreCol.setCellValueFactory(new PropertyValueFactory<Entrenamiento, String>("nombre"));
        TableColumn dificultadCol = new TableColumn("Dificultad");
        dificultadCol.setCellValueFactory(new PropertyValueFactory<Entrenamiento, String>("dificultad"));
        TableColumn localizacionCol = new TableColumn("Localización");
        localizacionCol.setCellValueFactory(new PropertyValueFactory<Entrenamiento, String>("localizacion"));
        TableColumn tipoCol = new TableColumn("Tipo");
        tipoCol.setCellValueFactory(new PropertyValueFactory<Entrenamiento, String>("tipoStr"));
        TableColumn fotoCol = new TableColumn("Foto");
        fotoCol.setCellValueFactory(new PropertyValueFactory<Entrenamiento, String>("foto"));
//        
//
        //define la tabla como no editable
        tablaItinerario.setEditable(false);
        //añade las columnas creadas
        tablaItinerario.getColumns().addAll(idCol, nombreCol, dificultadCol, localizacionCol, tipoCol, fotoCol);
        //hace que las columnas ocupen todo el espacio reservado para la tabla
        tablaItinerario.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //rellena la tabla con los datos
        tablaItinerario.getItems().clear();

        for (Iterator it = m.getItinerarios().iterator(); it.hasNext();) {
            Itinerario i = (Itinerario) it.next();
            tablaItinerario.getItems().add(i);

        }
    }

    private void setDatePickerEntrenamiento() {
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
        gridPaneEntrenamiento.add(datePicker, 0, 0);
        ((TextField) datePicker.getChildren().get(0)).setMaxWidth(73);//se ajusta el tamaño del textfield
        ((TextField) datePicker.getChildren().get(0)).setEditable(false);//el textfield no sera editable
    }

    private void setDatePickerItinerario() {
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
        gridPaneItinerario.add(datePicker, 0, 0);
        ((TextField) datePicker.getChildren().get(0)).setMaxWidth(73);//se ajusta el tamaño del textfield
        ((TextField) datePicker.getChildren().get(0)).setEditable(false);//el textfield no sera editable
    }

    private void setCombosEntrenamiento() {
        /*
         * Este metodo rellena los ComboBox
         */
        comboTipoEntrenamiento.getItems().clear();
        comboTipoEntrenamiento.getItems().addAll(
                m.write("fisico"),
                m.write("roca"),
                m.write("rocodromo"));
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
        comboTipoEntrenamiento.getSelectionModel().select(0);
    }

    private void setCombosItinerario() {
        /*
         * Este metodo rellena los ComboBox
         */

        comboTipoItinerario.getItems().clear();
        comboTipoItinerario.getItems().addAll(
                m.write("via"),
                m.write("boulder"));
        comboDificultad.getItems().clear();
        comboDificultad.setItems(m.getDificultades());

        //Se selecciona el primer valor por defecto
        comboDificultad.getSelectionModel().select(0);
        comboTipoItinerario.getSelectionModel().select(0);
    }
    /*ESTE METODO PERMITE ENLAZAR CON OTRA VENTANA DANDO DOBLE CLICK SOBRE UNA TABLA
     por ejemplo para ir a los detalles de un entrenamiento concreto*/

    private void setOnClick() {
        tablaItinerario.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                
                    paneItinerario.setVisible(true);
                    textLoca.getTextbox().setText(((Itinerario)(tablaItinerario.getSelectionModel().getSelectedItem())).getLocalizacion());
                    textNombre.setText(((Itinerario)(tablaItinerario.getSelectionModel().getSelectedItem())).getNombre());
                    
                    
                
            }
        });




        tablaEntrenamiento.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                
                    paneEntrenamiento.setVisible(true);
                
            }
        });

    }
}
