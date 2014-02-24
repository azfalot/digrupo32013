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
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import logica.Methods;
import np.com.ngopal.control.AutoFillTextBox;

/**
 * FXML Controller class
 *
 * @author Grupo 3
 */
public class PantallaDatosController implements Initializable {
    @FXML
    private Tab tabEntrenamiento;
    @FXML
    private Tab tabItinerario;
    @FXML
    private Label labeleFecha;
    @FXML
    private Label labeleTipo;
    @FXML
    private Label labeleDescripcion;
    @FXML
    private Label labeleHoraInicio;
    @FXML
    private Label labeleHoraFinal;
    @FXML
    private Label labeliNombre;
    @FXML
    private Label labeliLoc;
    @FXML
    private Label labeliTipo;
    @FXML
    private Label labeliFecha;
    @FXML
    private Label labeliDificultad;
    @FXML
    private Button botonBorrarItinerario;
    @FXML
    private Button botonBorrarEntrenamiento;
    @FXML
    private Label labelSinImagen;
    @FXML
    private Label labelDesdeEntrenamientos;
    @FXML
    private Label labelDesdeItinerarios;
    @FXML
    private Label labelAEntrenamientos;
    @FXML
    private Label labelAItinerarios;
    @FXML
    private Button botonFiltrarEntrenamientos;
    @FXML
    private Button botonFiltrarItinerarios;
    @FXML
    private ToolBar toolBarEntrenamientos;
    @FXML
    private ToolBar toolBarItinerarios;
    @FXML
    private ComboBox comboTipoEntrenamientos;
    @FXML
    private ComboBox comboTipoItinerarios;
    @FXML
    private ComboBox comboDificultadItinerarios;
    @FXML
    private TabPane tabPane;
    @FXML
    private TableView tablaEntrenamiento;
    @FXML
    private TableView tablaItinerario;
    @FXML
    private ImageView ivError;
    @FXML
    private Button botonModificarEntrenamiento;
    @FXML
    private Button botonModificarItinerario;
    @FXML
    private ComboBox comboHoraInicio;
    @FXML
    private ComboBox comboHoraFinal;
    @FXML
    private ComboBox comboMinFinal;
    @FXML
    private ComboBox comboMinInicio;
    @FXML
    private TextArea textAreaDescripcion;
    @FXML
    private ComboBox comboTipoEntrenamiento;
    @FXML
    private ComboBox comboDificultad;
    @FXML
    private ComboBox comboTipoItinerario;
    @FXML
    private GridPane gridPaneEntrenamiento;
    @FXML
    private GridPane gridPaneItinerario;
    @FXML
    private GridPane gridPaneAutoFill;
    @FXML
    private GridPane gridPaneEntrenamientoFiltroInicio;
    @FXML
    private GridPane gridPaneEntrenamientoFiltroFin;
    @FXML
    private GridPane gridPaneItinerarioFiltroInicio;
    @FXML
    private GridPane gridPaneItinerarioFiltroFin;
    @FXML
    private TextField textNombre;
    @FXML
    private ImageView imageView;
    @FXML
    private AnchorPane paneEntrenamiento;
    @FXML
    private AnchorPane paneItinerario;
    
    private AutoFillTextBox textLoca;
    private DatePicker datePickerEntrenamiento;
    private DatePicker datePickerItinerario;
    private Methods m;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private final SimpleDateFormat sdfmin = new SimpleDateFormat("mm");
    private final SimpleDateFormat sdfhor = new SimpleDateFormat("hh");
    private DatePicker datePickerEntrenamientoFiltroInicio;
    private DatePicker datePickerEntrenamientoFiltroFin;
    private DatePicker datePickerItinerarioFiltroInicio;
    private DatePicker datePickerItinerarioFiltroFin;
    private ArrayList<Entrenamiento> entrenamientos = new ArrayList();
    private ArrayList<Itinerario> itinerarios = new ArrayList();

    /*
     * INITIALIZE
     */
    public void builder(Methods m) {
        this.m = m;
        entrenamientos = m.getEntrenamientos();
        itinerarios = m.getItinerarios();
        formatTablaEntrenamiento();
        formatTablaItinerario();
        refreshTablaEntrenamiento();
        refreshTablaItinerario();
        setDatePickerItinerario();
        setDatePickerEntrenamiento();
        setDatePickerEntrenamientoFiltroFin();
        setDatePickerEntrenamientoFiltroInicio();
        setDatePickerItinerarioFiltroFin();
        setDatePickerItinerarioFiltroInicio();
        setCombosEntrenamiento();
        setCombosItinerario();
        setCombosFiltrosEntrenamientos();
        setCombosFiltrosItinerarios();
        setOnClick();
        setToolBarsContextMenu();
        translate();
        textLoca = new AutoFillTextBox(m.getLocalizaciones());
        gridPaneAutoFill.getChildren().add(textLoca);
        textLoca.setMaxWidth(140);
        imageView.setPreserveRatio(false);
        paneEntrenamiento.setDisable(true);
        paneItinerario.setDisable(true);

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
    
    private void translate(){
        botonFiltrarEntrenamientos.setText(m.write("filtrar"));
        botonFiltrarItinerarios.setText(m.write("filtrar"));
        labelAEntrenamientos.setText(m.write("to"));
        labelAItinerarios.setText(m.write("to"));
        labelDesdeEntrenamientos.setText(m.write("from"));
        labelDesdeItinerarios.setText(m.write("from"));
        labelSinImagen.setText(m.write("l_sin_imagen"));
        botonModificarEntrenamiento.setText(m.write("modificar"));
        botonModificarItinerario.setText(m.write("modificar"));
        botonBorrarEntrenamiento.setText(m.write("erase"));
        botonBorrarItinerario.setText(m.write("erase"));
        labeliDificultad.setText(m.write("l_dificultad")+":");
        labeliFecha.setText(m.write("date")+":");
        labeliLoc.setText(m.write("loc")+":");
        labeliNombre.setText(m.write("name")+":");
        labeliTipo.setText(m.write("l_tipo")+":");
        labeleDescripcion.setText(m.write("description")+":");
        labeleFecha.setText(m.write("l_date_session")+":");
        labeleHoraFinal.setText(m.write("l_horafin")+":");
        labeleHoraInicio.setText(m.write("l_horainicio")+":");
        labeleTipo.setText(m.write("l_type")+":");
        //tabEntrenamiento.setText(m.write("entrenamientos"));
        //tabItinerario.setText(m.write("itinerarios"));
    }
    
    /*
     * TABLA
     */
    public void refreshTablaEntrenamiento() {
        formatTablaEntrenamiento();
        for (Entrenamiento e : entrenamientos) {
            tablaEntrenamiento.getItems().add(e);
        }
    }

    public void refreshTablaItinerario() {
        formatTablaItinerario();
        for (Itinerario e : itinerarios) {
            tablaItinerario.getItems().add(e);
        }
    }

    private void formatTablaEntrenamiento() {
        //borra la tabla (columnas y datos)
        tablaEntrenamiento.getColumns().clear();
        tablaEntrenamiento.getItems().clear();
//
//        //crea las nuevas columnas

        TableColumn horaInicioCol = new TableColumn(m.write("l_horainicio"));
        horaInicioCol.setCellValueFactory(new PropertyValueFactory<Entrenamiento, Date>("hora_inicioStr"));
        TableColumn horaFinCol = new TableColumn(m.write("l_horafin"));
        horaFinCol.setCellValueFactory(new PropertyValueFactory<Entrenamiento, Date>("hora_finStr"));
        TableColumn fechaCol = new TableColumn(m.write("l_date_session"));
        fechaCol.setCellValueFactory(new PropertyValueFactory<Entrenamiento, Date>("dateStr"));
        TableColumn descripcionCol = new TableColumn(m.write("description"));
        descripcionCol.setCellValueFactory(new PropertyValueFactory<Entrenamiento, String>("descripcion"));
        TableColumn tipoCol = new TableColumn(m.write("l_type"));
        tipoCol.setCellValueFactory(new PropertyValueFactory<Entrenamiento, String>("tipoStr"));
//        
//
        //define la tabla como no editable
        tablaEntrenamiento.setEditable(false);
        //añade las columnas creadas
        tablaEntrenamiento.getColumns().addAll(tipoCol, horaInicioCol, horaFinCol, descripcionCol, fechaCol);
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

        TableColumn nombreCol = new TableColumn(m.write("name"));
        nombreCol.setCellValueFactory(new PropertyValueFactory<Entrenamiento, String>("nombre"));
        TableColumn dificultadCol = new TableColumn(m.write("l_dificultad"));
        dificultadCol.setCellValueFactory(new PropertyValueFactory<Entrenamiento, String>("dificultad"));
        TableColumn localizacionCol = new TableColumn(m.write("loc"));
        localizacionCol.setCellValueFactory(new PropertyValueFactory<Entrenamiento, String>("localizacion"));
        TableColumn fechaCol = new TableColumn(m.write("l_fecharesolucion"));
        fechaCol.setCellValueFactory(new PropertyValueFactory<Entrenamiento, String>("fechaStr"));
        TableColumn tipoCol = new TableColumn(m.write("l_tipo"));
        tipoCol.setCellValueFactory(new PropertyValueFactory<Entrenamiento, String>("tipoStr"));

//        
//
        //define la tabla como no editable
        tablaItinerario.setEditable(false);
        //añade las columnas creadas
        tablaItinerario.getColumns().addAll(nombreCol, tipoCol, dificultadCol, localizacionCol, fechaCol);
        //hace que las columnas ocupen todo el espacio reservado para la tabla
        tablaItinerario.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //rellena la tabla con los datos
        tablaItinerario.getItems().clear();

    }

    /*
     * DATEPICKERS
     */
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

    private void setDatePickerEntrenamientoFiltroInicio() {
        /*
         * Este metodo asigna el componente calendario al gridpane
         * Aparecera un textfield donde haciendo click se desplegara el calendario
         */
        datePickerEntrenamientoFiltroInicio = new DatePicker(new Locale(m.write("language"), m.write("language0")));
        datePickerEntrenamientoFiltroInicio.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
        datePickerEntrenamientoFiltroInicio.setPromptText("-- / -- / ----");
        datePickerEntrenamientoFiltroInicio.getCalendarView().todayButtonTextProperty().set(m.write("today"));
        datePickerEntrenamientoFiltroInicio.getCalendarView().setShowWeeks(false);
        datePickerEntrenamientoFiltroInicio.getStylesheets().add("interfaz/util/DatePicker.css");
        gridPaneEntrenamientoFiltroInicio.add(datePickerEntrenamientoFiltroInicio, 0, 0);
        ((TextField) datePickerEntrenamientoFiltroInicio.getChildren().get(0)).setMaxWidth(73);//se ajusta el tamaño del textfield
        ((TextField) datePickerEntrenamientoFiltroInicio.getChildren().get(0)).setEditable(false);//el textfield no sera editable
    }

    private void setDatePickerEntrenamientoFiltroFin() {
        /*
         * Este metodo asigna el componente calendario al gridpane
         * Aparecera un textfield donde haciendo click se desplegara el calendario
         */
        datePickerEntrenamientoFiltroFin = new DatePicker(new Locale(m.write("language"), m.write("language0")));
        datePickerEntrenamientoFiltroFin.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
        datePickerEntrenamientoFiltroFin.setPromptText("-- / -- / ----");
        datePickerEntrenamientoFiltroFin.getCalendarView().todayButtonTextProperty().set(m.write("today"));
        datePickerEntrenamientoFiltroFin.getCalendarView().setShowWeeks(false);
        datePickerEntrenamientoFiltroFin.getStylesheets().add("interfaz/util/DatePicker.css");
        gridPaneEntrenamientoFiltroFin.add(datePickerEntrenamientoFiltroFin, 0, 0);
        ((TextField) datePickerEntrenamientoFiltroFin.getChildren().get(0)).setMaxWidth(73);//se ajusta el tamaño del textfield
        ((TextField) datePickerEntrenamientoFiltroFin.getChildren().get(0)).setEditable(false);//el textfield no sera editable
    }

    private void setDatePickerItinerarioFiltroInicio() {
        /*
         * Este metodo asigna el componente calendario al gridpane
         * Aparecera un textfield donde haciendo click se desplegara el calendario
         */
        datePickerItinerarioFiltroInicio = new DatePicker(new Locale(m.write("language"), m.write("language0")));
        datePickerItinerarioFiltroInicio.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
        datePickerItinerarioFiltroInicio.setPromptText("-- / -- / ----");
        datePickerItinerarioFiltroInicio.getCalendarView().todayButtonTextProperty().set(m.write("today"));
        datePickerItinerarioFiltroInicio.getCalendarView().setShowWeeks(false);
        datePickerItinerarioFiltroInicio.getStylesheets().add("interfaz/util/DatePicker.css");
        gridPaneItinerarioFiltroInicio.add(datePickerItinerarioFiltroInicio, 0, 0);
        ((TextField) datePickerItinerarioFiltroInicio.getChildren().get(0)).setMaxWidth(73);//se ajusta el tamaño del textfield
        ((TextField) datePickerItinerarioFiltroInicio.getChildren().get(0)).setEditable(false);//el textfield no sera editable
    }

    private void setDatePickerItinerarioFiltroFin() {
        /*
         * Este metodo asigna el componente calendario al gridpane
         * Aparecera un textfield donde haciendo click se desplegara el calendario
         */
        datePickerItinerarioFiltroFin = new DatePicker(new Locale(m.write("language"), m.write("language0")));
        datePickerItinerarioFiltroFin.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
        datePickerItinerarioFiltroFin.setPromptText("-- / -- / ----");
        datePickerItinerarioFiltroFin.getCalendarView().todayButtonTextProperty().set(m.write("today"));
        datePickerItinerarioFiltroFin.getCalendarView().setShowWeeks(false);
        datePickerItinerarioFiltroFin.getStylesheets().add("interfaz/util/DatePicker.css");
        gridPaneItinerarioFiltroFin.add(datePickerItinerarioFiltroFin, 0, 0);
        ((TextField) datePickerItinerarioFiltroFin.getChildren().get(0)).setMaxWidth(73);//se ajusta el tamaño del textfield
        ((TextField) datePickerItinerarioFiltroFin.getChildren().get(0)).setEditable(false);//el textfield no sera editable
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

    /*
     * COMBOS
     */
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

    private void setCombosFiltrosEntrenamientos() {
        /*
         * Este metodo rellena los ComboBox
         */

        comboTipoEntrenamientos.getItems().clear();
        comboTipoEntrenamientos.getItems().addAll(
                m.write("tipos"),
                m.write("fisico"),
                m.write("roca"),
                m.write("rocodromo"));
        comboTipoEntrenamientos.getSelectionModel().selectFirst();
    }

    private void setCombosFiltrosItinerarios() {
        comboTipoItinerarios.getItems().clear();
        comboTipoItinerarios.getItems().addAll(
                m.write("tipos"),
                m.write("via"),
                m.write("boulder"));
        comboTipoItinerarios.getSelectionModel().selectFirst();

        comboDificultadItinerarios.getItems().clear();
        comboDificultadItinerarios.getItems().add("> "+m.write("dificultad"));
        comboDificultadItinerarios.getItems().addAll(m.getDificultades());
        comboDificultadItinerarios.getSelectionModel().selectFirst();
    }

    /*ESTE METODO PERMITE ENLAZAR CON OTRA VENTANA DANDO DOBLE CLICK SOBRE UNA TABLA
     por ejemplo para ir a los detalles de un entrenamiento concreto*/
    private void setOnClick() {
        tablaItinerario.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                try {
                    paneItinerario.setDisable(false);
                    Itinerario i = (Itinerario) tablaItinerario.getSelectionModel().getSelectedItem();
                    textLoca.getTextbox().setText(i.getLocalizacion());
                    textNombre.setText(i.getNombre());
                    comboTipoItinerario.getSelectionModel().select(i.getTipoStr());
                    comboDificultad.getSelectionModel().select(i.getDificultad());
                    ((TextField) datePickerItinerario.getChildren().get(0)).setText(sdf.format(i.getFecha()));
                    String p = (i.getFoto());
                    Image img = new Image("file:" + p);
                    imageView.setImage(img);
                } catch (NullPointerException e) {
                    paneItinerario.setDisable(true);
                }
            }
        });

        tablaEntrenamiento.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                try {
                    paneEntrenamiento.setDisable(false);
                    Entrenamiento e = (Entrenamiento) tablaEntrenamiento.getSelectionModel().getSelectedItem();
                    ((TextField) datePickerEntrenamiento.getChildren().get(0)).setText(sdf.format(e.getFecha()));
                    comboHoraInicio.getSelectionModel().select(sdfhor.format(e.getHora_inicio()));
                    comboMinInicio.getSelectionModel().select(sdfmin.format(e.getHora_inicio()));
                    comboHoraFinal.getSelectionModel().select(sdfhor.format(e.getHora_fin()));
                    comboMinFinal.getSelectionModel().select(sdfmin.format(e.getHora_fin()));
                    comboTipoEntrenamiento.getSelectionModel().select(e.getTipoStr());
                    textAreaDescripcion.setText(e.getDescripcion());
                } catch (NullPointerException e) {
                    paneEntrenamiento.setDisable(true);
                }
            }
        });

    }

    private void setToolBarsContextMenu() {
        ContextMenu cm = new ContextMenu();
        MenuItem mi = new MenuItem(m.write("clean"));
        mi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                setDatePickerEntrenamientoFiltroInicio();
                setDatePickerEntrenamientoFiltroFin();
                comboTipoEntrenamientos.getSelectionModel().select(0);
                entrenamientos = m.getEntrenamientos();
                refreshTablaEntrenamiento();
            }
        });
        cm.getItems().add(mi);
        toolBarEntrenamientos.setContextMenu(cm);

        ContextMenu cm1 = new ContextMenu();
        MenuItem mi1 = new MenuItem(m.write("clean"));
        mi1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                setDatePickerItinerarioFiltroInicio();
                setDatePickerItinerarioFiltroFin();
                comboTipoItinerarios.getSelectionModel().select(0);
                comboDificultadItinerarios.getSelectionModel().select(0);
                itinerarios = m.getItinerarios();
                refreshTablaItinerario();
            }
        });
        cm1.getItems().add(mi1);
        toolBarItinerarios.setContextMenu(cm1);

    }

    /*
     * HANDLES
     */
    @FXML
    private void handleFiltrarItinerarios() {
        /*
         * FILTRA LOS ITINERARIOS
         */
        itinerarios = m.getItinerarios();
        ArrayList<Itinerario> newItinerarios;
        if (!"".equals(((TextField) datePickerItinerarioFiltroInicio.getChildren().get(0)).getText())) {
            newItinerarios = new ArrayList();
            for (Itinerario i : itinerarios) {
                if (i.getFecha().getTime() >= datePickerItinerarioFiltroInicio.getSelectedDate().getTime()) {
                    newItinerarios.add(i);
                }
            }
            itinerarios = newItinerarios;
        }
        if (!"".equals(((TextField) datePickerItinerarioFiltroFin.getChildren().get(0)).getText())) {
            newItinerarios = new ArrayList();
            for (Itinerario i : itinerarios) {
                if (i.getFecha().getTime() <= datePickerItinerarioFiltroFin.getSelectedDate().getTime()) {
                    newItinerarios.add(i);
                }
            }
            itinerarios = newItinerarios;
        }
        if (comboTipoItinerarios.getSelectionModel().getSelectedIndex() != 0) {
            newItinerarios = new ArrayList();
            for (Itinerario i : itinerarios) {
                if (comboTipoItinerarios.getSelectionModel().getSelectedItem() == i.getTipoStr()) {
                    newItinerarios.add(i);
                }
            }
            itinerarios = newItinerarios;
        }
        if (comboDificultadItinerarios.getSelectionModel().getSelectedIndex() != 0) {
            newItinerarios = new ArrayList();
            for (Itinerario i : itinerarios) {
                if (comboDificultadItinerarios.getSelectionModel().getSelectedIndex()-1 <= m.getDificultades().indexOf(i.getDificultad())) {
                    newItinerarios.add(i);
                }
            }
            itinerarios = newItinerarios;
        }
        refreshTablaItinerario();
    }

    @FXML
    private void handleFiltrarEntrenamientos() {
        /*
         * FILTRA LOS ENTRENAMIENTOS
         */
        entrenamientos = m.getEntrenamientos();
        ArrayList<Entrenamiento> newEntrenamientos;
        if (!"".equals(((TextField) datePickerEntrenamientoFiltroInicio.getChildren().get(0)).getText())) {
            newEntrenamientos = new ArrayList();
            for (Entrenamiento e : entrenamientos) {
                if (e.getFecha().getTime() >= datePickerEntrenamientoFiltroInicio.getSelectedDate().getTime()) {
                    newEntrenamientos.add(e);
                }
            }
            entrenamientos = newEntrenamientos;
        }
        if (!"".equals(((TextField) datePickerEntrenamientoFiltroFin.getChildren().get(0)).getText())) {
            newEntrenamientos = new ArrayList();
            for (Entrenamiento e : entrenamientos) {
                if (e.getFecha().getTime() <= datePickerEntrenamientoFiltroFin.getSelectedDate().getTime()) {
                    newEntrenamientos.add(e);
                }
            }
            entrenamientos = newEntrenamientos;
        }
        if (comboTipoEntrenamientos.getSelectionModel().getSelectedIndex() != 0) {
            newEntrenamientos = new ArrayList();
            for (Entrenamiento e : entrenamientos) {
                if (comboTipoEntrenamientos.getSelectionModel().getSelectedItem() == e.getTipoStr()) {
                    newEntrenamientos.add(e);
                }
            }
            entrenamientos = newEntrenamientos;
        }
        refreshTablaEntrenamiento();
    }

    @FXML
    private void handleBorrarItinerario() {
        try {
            int index=tablaItinerario.getSelectionModel().getSelectedIndex();
            m.deleteItinerario(((Itinerario) tablaItinerario.getSelectionModel().getSelectedItem()).getP_itinerario());
            itinerarios.remove(index);
            paneItinerario.setDisable(true);
            refreshTablaItinerario();
        } catch (Throwable e) {

        }
    }

    @FXML
    private void handleBorrarEntrenamiento() {
        try {
            int index=tablaEntrenamiento.getSelectionModel().getSelectedIndex();
            m.deleteEntrenamiento(((Entrenamiento) tablaEntrenamiento.getSelectionModel().getSelectedItem()).getP_entrenamiento());
            entrenamientos.remove(index);
            paneEntrenamiento.setDisable(true);
            refreshTablaEntrenamiento();
        } catch (Throwable e) {

        }
    }
    
    @FXML
    private void handleModificarEntrenamiento(){
        SimpleDateFormat hf=new SimpleDateFormat("hh:mm");
        try{
            ((Entrenamiento)tablaEntrenamiento.getSelectionModel().getSelectedItem()).setFecha(datePickerEntrenamiento.getSelectedDate());
            Date horaInicio=hf.parse((String)comboHoraInicio.getSelectionModel().getSelectedItem()+":"+(String)comboMinInicio.getSelectionModel().getSelectedItem());            
            ((Entrenamiento)tablaEntrenamiento.getSelectionModel().getSelectedItem()).setHora_inicio(horaInicio);
            Date horaFin=hf.parse((String)comboHoraFinal.getSelectionModel().getSelectedItem()+":"+(String)comboMinFinal.getSelectionModel().getSelectedItem());
            ((Entrenamiento)tablaEntrenamiento.getSelectionModel().getSelectedItem()).setHora_fin(horaFin);
            ((Entrenamiento)tablaEntrenamiento.getSelectionModel().getSelectedItem()).setTipo(comboTipoEntrenamiento.getSelectionModel().getSelectedIndex());
            ((Entrenamiento)tablaEntrenamiento.getSelectionModel().getSelectedItem()).setDescripcion(textAreaDescripcion.getText());
            refreshTablaEntrenamiento();
        }catch(Throwable e){
            
        }
    }
    
    @FXML
    private void handleModificarItinerario(){
        try{
            ((Itinerario)tablaItinerario.getSelectionModel().getSelectedItem()).setNombre(textNombre.getText());
            ((Itinerario)tablaItinerario.getSelectionModel().getSelectedItem()).setFecha(datePickerItinerario.getSelectedDate());
            ((Itinerario)tablaItinerario.getSelectionModel().getSelectedItem()).setTipo(comboTipoItinerario.getSelectionModel().getSelectedIndex());
            ((Itinerario)tablaItinerario.getSelectionModel().getSelectedItem()).setDificultad((String)comboDificultad.getSelectionModel().getSelectedItem());
            ((Itinerario)tablaItinerario.getSelectionModel().getSelectedItem()).setLocalizacion(textLoca.getTextbox().getText());
            refreshTablaItinerario();
        }catch(Throwable e){
            
        }
    }
}
