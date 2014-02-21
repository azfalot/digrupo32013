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
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
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
    Button botonExaminar;
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
    private DatePicker datePickerEntrenamiento;
    private DatePicker datePickerItinerario;
    Methods m;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdfmin = new SimpleDateFormat("mm");
    SimpleDateFormat sdfhor = new SimpleDateFormat("hh");


    /*
     * INITIALIZE
     */
    public void builder(Methods m) {
        this.m = m;
        formatTablaEntrenamiento();
        formatTablaItinerario();
        refreshTabla();
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
        for (Iterator it = m.getEntrenamientos().iterator(); it.hasNext();) {
            Entrenamiento e = (Entrenamiento) it.next();
            tablaEntrenamiento.getItems().add(e);

        }
        for (Iterator it = m.getItinerarios().iterator(); it.hasNext();) {
            Itinerario i = (Itinerario) it.next();
            tablaItinerario.getItems().add(i);

        }
    }

    private void formatTablaEntrenamiento() {
        //borra la tabla (columnas y datos)
        tablaEntrenamiento.getColumns().clear();
        tablaEntrenamiento.getItems().clear();
//
//        //crea las nuevas columnas

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
        tablaEntrenamiento.getColumns().addAll(horaInicioCol, horaFinCol, fechaCol, descripcionCol, tipoCol);
        //hace que las columnas ocupen todo el espacio reservado para la tabla
        tablaEntrenamiento.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //rellena la tabla con los datos
        tablaEntrenamiento.getItems().clear();

        
    }

    private void formatTablaItinerario() {
        //borra la tabla (columnas y datos)
        tablaItinerario.getColumns().clear();
        tablaItinerario.getItems().clear();
//
//        //crea las nuevas columnas

        TableColumn nombreCol = new TableColumn("Nombre");
        nombreCol.setCellValueFactory(new PropertyValueFactory<Entrenamiento, String>("nombre"));
        TableColumn dificultadCol = new TableColumn("Dificultad");
        dificultadCol.setCellValueFactory(new PropertyValueFactory<Entrenamiento, String>("dificultad"));
        TableColumn localizacionCol = new TableColumn("Localización");
        localizacionCol.setCellValueFactory(new PropertyValueFactory<Entrenamiento, String>("localizacion"));
        TableColumn fechaCol = new TableColumn("Fecha");
        fechaCol.setCellValueFactory(new PropertyValueFactory<Entrenamiento, String>("fecha"));
        TableColumn tipoCol = new TableColumn("Tipo");
        tipoCol.setCellValueFactory(new PropertyValueFactory<Entrenamiento, String>("tipoStr"));

//        
//
        //define la tabla como no editable
        tablaItinerario.setEditable(false);
        //añade las columnas creadas
        tablaItinerario.getColumns().addAll(nombreCol, dificultadCol, fechaCol, localizacionCol, tipoCol);
        //hace que las columnas ocupen todo el espacio reservado para la tabla
        tablaItinerario.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //rellena la tabla con los datos
        tablaItinerario.getItems().clear();

        
    }

    private void setDatePickerEntrenamiento() {
        /*
         * Este metodo asigna el componente calendario al gridpane
         * Aparecera un textfield donde haciendo click se desplegara el calendario
         */
        datePickerEntrenamiento = new DatePicker(new Locale(m.write("language"), m.write("language0")));
        datePickerEntrenamiento.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
        datePickerEntrenamiento.setPromptText("-- / -- / ----");
        datePickerEntrenamiento.getCalendarView().todayButtonTextProperty().set(m.write("today"));
        datePickerEntrenamiento.getCalendarView().setShowWeeks(false);
        datePickerEntrenamiento.getStylesheets().add("interfaz/util/DatePicker.css");
        gridPaneEntrenamiento.add(datePickerEntrenamiento, 0, 0);
        ((TextField) datePickerEntrenamiento.getChildren().get(0)).setMaxWidth(73);//se ajusta el tamaño del textfield
        ((TextField) datePickerEntrenamiento.getChildren().get(0)).setEditable(false);//el textfield no sera editable
    }

    private void setDatePickerItinerario() {
        /*
         * Este metodo asigna el componente calendario al gridpane
         * Aparecera un textfield donde haciendo click se desplegara el calendario
         */
        datePickerItinerario = new DatePicker(new Locale(m.write("language"), m.write("language0")));
        datePickerItinerario.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
        datePickerItinerario.setPromptText("-- / -- / ----");
        datePickerItinerario.getCalendarView().todayButtonTextProperty().set(m.write("today"));
        datePickerItinerario.getCalendarView().setShowWeeks(false);
        datePickerItinerario.getStylesheets().add("interfaz/util/DatePicker.css");
        gridPaneItinerario.add(datePickerItinerario, 0, 0);
        ((TextField) datePickerItinerario.getChildren().get(0)).setMaxWidth(73);//se ajusta el tamaño del textfield
        ((TextField) datePickerItinerario.getChildren().get(0)).setEditable(false);//el textfield no sera editable
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
                Itinerario i = (Itinerario) tablaItinerario.getSelectionModel().getSelectedItem();
                textLoca.getTextbox().setText(i.getLocalizacion());
                textNombre.setText(i.getNombre());
                comboTipoItinerario.getSelectionModel().select(i.getTipoStr());
                comboDificultad.getSelectionModel().select(i.getDificultad());
                ((TextField) datePickerItinerario.getChildren().get(0)).setText(sdf.format(i.getFecha()));
                String p = (i.getFoto());
                Image img = new Image("file:" + p);
                imageView.setImage(img);
                
            }
        });

        tablaEntrenamiento.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {

                paneEntrenamiento.setVisible(true);
                Entrenamiento e = (Entrenamiento) tablaEntrenamiento.getSelectionModel().getSelectedItem();
                ((TextField) datePickerEntrenamiento.getChildren().get(0)).setText(sdf.format(e.getFecha()));
                comboHoraInicio.getSelectionModel().select(sdfhor.format(e.getHora_inicio()));
                comboMinInicio.getSelectionModel().select(sdfmin.format(e.getHora_inicio()));
                comboHoraFinal.getSelectionModel().select(sdfhor.format(e.getHora_fin()));
                comboMinFinal.getSelectionModel().select(sdfmin.format(e.getHora_fin()));
                comboTipoEntrenamiento.getSelectionModel().select(e.getTipoStr());
                textAreaDescripcion.setText(e.getDescripcion());

            }
        });

    }
}
