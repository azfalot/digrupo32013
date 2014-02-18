/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import datos.conexionbd.POJOS.Usuario;
import eu.schudt.javafx.controls.calendar.DatePicker;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import jfxtras.labs.scene.control.window.Window;
import logica.Methods;

/**
 * FXML Controller class
 *
 * @author Grupo 3
 */
public class PantallaConfiguracionController implements Initializable {

    @FXML
    TabPane tabPane;
    @FXML
    TableView tablaUsuarios;
    @FXML
    ComboBox comboIdioma;
    @FXML
    ImageView ivDesktop;
    @FXML
    GridPane gridPane;
    @FXML
    TextField tfUsuario;
    @FXML
    CheckBox checkDefault;
    @FXML
    Tab tabGeneral;
    @FXML
    Tab tabApariencia;
    @FXML
    Tab tabUsuarios;
    @FXML
    Tab tabIdioma;
    @FXML
    Label labelUsuario;
    @FXML
    Label labelCalculo;
    @FXML
    Label labelDia;
    @FXML
    Label labelDia2;
    @FXML
    Label labelFondo;
    @FXML
    Button botonCambiarFondo;
    @FXML
    Button botonCerrar;
    @FXML
    Button botonAplicar;
    @FXML
    Button botonCambiarUsuario;
    @FXML
    Button botonBorrar;
    @FXML
    Label labelIdioma;
    @FXML
    Label labelIdioma2;


    private Methods m;
    private Window w;
    private PantallaPrincipalController ppc;
    private Escalada e;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private File fondoEscritorio;
    private boolean wallpaperChanged = false;

    private DatePicker datePicker;

    public void builder(Methods m, Window w, PantallaPrincipalController ppc, Escalada e) {
        this.m = m;
        this.w = w;
        this.ppc = ppc;
        this.e = e;    
        refreshTabla();
        setComboIdioma();
        setImages();
        setDatePicker();
        setUser();
        translate();
    }
    
    private void translate(){
        tabGeneral.setText(m.write("t_general"));
        tabApariencia.setText(m.write("t_apariencia"));
        tabUsuarios.setText(m.write("t_usuarios"));
        tabIdioma.setText(m.write("t_idioma"));
        labelUsuario.setText(m.write("l_usuario")+":");
        checkDefault.setText(m.write("cb_default"));
        labelCalculo.setText(m.write("l_calculo")+":");
        labelDia.setText(m.write("l_dia")+":");
        labelDia2.setText("("+m.write("l_dia2")+")");
        labelFondo.setText(m.write("l_fondo")+":");
        botonCambiarFondo.setText(m.write("b_cambiarfondo"));
        botonAplicar.setText(m.write("apply"));
        botonCerrar.setText(m.write("close"));
        botonBorrar.setText(m.write("erase"));
        botonCambiarUsuario.setText(m.write("b_cambiarusuario"));
        labelIdioma.setText(m.write("l_idioma")+":");
        labelIdioma2.setText("("+m.write("l_idioma2")+")");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setPaneSizes();

    }

    private void setPaneSizes() {
        tabPane.setTabMinHeight(22);
        tabPane.setTabMaxHeight(22);
    }

    private void setImages() {
        ivDesktop.setImage(new Image("file:" + m.getWallpaper()));
    }

    private void setComboIdioma() {
        comboIdioma.getItems().clear();
        File f = new File("languages" + File.separator + "strings");
        String[] idiomas = f.list();
        for (String nombre : idiomas) {
            if (nombre.contains(".xml")) {
                comboIdioma.getItems().add(nombre.substring(0, nombre.lastIndexOf(".xml")));
            }
        }
        comboIdioma.getSelectionModel().select(m.getLanguage());
    }

    private void setUser() {
        tfUsuario.setText(m.getUserName());
        checkDefault.setSelected(m.isUserDefault());
    }

    private void setDatePicker() {
        /*
         * Este metodo asigna el componente calendario al gridpane
         * Aparecera un textfield donde haciendo click se desplegara el calendario
         */
        datePicker = new DatePicker(new Locale(m.write("language"), m.write("language0")));
        datePicker.setDateFormat(sdf);
        datePicker.setPromptText("-- / -- / ----");
        datePicker.getCalendarView().todayButtonTextProperty().set(m.write("today"));
        datePicker.getCalendarView().setShowWeeks(false);
        datePicker.getStylesheets().add("interfaz/util/DatePicker.css");
        gridPane.add(datePicker, 0, 0);
        ((TextField) datePicker.getChildren().get(0)).setMaxWidth(73);//se ajusta el tamaño del textfield
        ((TextField) datePicker.getChildren().get(0)).setEditable(false);//el textfield no sera editable
        ((TextField) datePicker.getChildren().get(0)).setText(sdf.format(m.getFechaRendimiento()));
    }

    /*
     * HANDLES    
     */
    @FXML
    private void handleCerrar() {

        w.close();
    }

    @FXML
    private void handleAplicar() {
        /*
         * Aplica los cambios en la configuracion
         */

        //guarda el idioma
        m.setLanguage((String) comboIdioma.getSelectionModel().getSelectedItem());
        //pone el fondo de escritorio
        if (wallpaperChanged) {
            m.setUserWallpaper(fondoEscritorio);
            ppc.setDesktop();
        }
        //nombre y usuario por defecto
        if (!"".equals(tfUsuario.getText())) {
            m.setUserName(tfUsuario.getText());
        }
        m.setUserDefault(checkDefault.isSelected());
        //fecha de calculo de rendimiento
        m.setFechaRendimiento(datePicker.getSelectedDate());
    }

    @FXML
    private void handleUsuariosBorrar() {
        try {
            m.deleteUsuario(((Usuario) tablaUsuarios.getSelectionModel().getSelectedItem()).getId());
            refreshTabla();
        } catch (Throwable e) {
        }
    }

    @FXML
    private void handleUsuariosCambiarUsuario() {
        try {
            m.setUser((Usuario) tablaUsuarios.getSelectionModel().getSelectedItem(), false);
            e.goToPantallaPrincipal();
        } catch (Throwable e) {
        }
    }

    @FXML
    public void handleAparienciaCambiarFondo(ActionEvent event) {
        /*
         * Este metodo se encarga de seleccionar la imagen en la ruta especificada y previsualizarla.
         * Ademas guarda la ruta de la imagen para ser copiada posteriormente al aceptar
         */
        FileChooser fileChooser = new FileChooser();
        //Filtros para la extension del archivo
        FileChooser.ExtensionFilter exjpg = new FileChooser.ExtensionFilter("JPEG (*.jpg)", "*.jpg", "*.jpeg");
        FileChooser.ExtensionFilter expng = new FileChooser.ExtensionFilter("PNG (*.png)", "*.png");
        FileChooser.ExtensionFilter exbmp = new FileChooser.ExtensionFilter(m.write("bitmap") + " (*.bmp)", "*.bmp");
        FileChooser.ExtensionFilter exall = new FileChooser.ExtensionFilter(m.write("image_files"), "*.jpg", "*.png", "*.bmp", "*.jpeg");
        fileChooser.getExtensionFilters().add(exall);
        fileChooser.getExtensionFilters().add(exbmp);
        fileChooser.getExtensionFilters().add(exjpg);
        fileChooser.getExtensionFilters().add(expng);
        //Abre la ventana del filechooser
        fondoEscritorio = fileChooser.showOpenDialog(null);
        if (fondoEscritorio == null) {
            fondoEscritorio = new File(m.getWallpaper());
        } else {
            wallpaperChanged = true;
            ivDesktop.setImage(new Image("file:" + fondoEscritorio));
        }

    }

    /*
     * Formato a tabla de usuarios
     */
    private void refreshTabla() {
        formatTabla();
        for (Usuario u : m.getUsuarios()) {
            if (u.getId() != m.getUserId()) {
                tablaUsuarios.getItems().add(u);
            }
        }
    }

    private void formatTabla() {
        //borra la tabla (columnas y datos)
        tablaUsuarios.getColumns().clear();
        tablaUsuarios.getItems().clear();        
        
        //crea las nuevas columnas
        TableColumn nombreCol = new TableColumn(m.write("name"));
        nombreCol.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nombre"));
        
        //define la tabla como no editable
        tablaUsuarios.setEditable(false);
        //añade las columnas creadas
        tablaUsuarios.getColumns().addAll(nombreCol);
        //hace que las columnas ocupen todo el espacio reservado para la tabla
        tablaUsuarios.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                
    }
}
