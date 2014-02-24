package interfaz;

import datos.conexionbd.POJOS.Itinerario;
import interfaz.util.MaximizeIcon;
import interfaz.util.ModalListener;
import interfaz.util.MyMinimizeIcon;
import interfaz.util.MyWindow;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import jfxtras.labs.scene.control.window.CloseIcon;
import jfxtras.labs.scene.control.window.Window;
import logica.Methods;

/**
 * FXML Controller class
 *
 * @author Grupo 3
 */
public class PantallaPrincipalController implements Initializable {

    @FXML
    AnchorPane desktop;
    @FXML
    ImageView wallpaper;
    @FXML
    Label labelHora;
    @FXML
    ToolBar toolBar;
    @FXML
    Button botonFullScreen;
    @FXML
    Button botonExit;
    @FXML
    Button iconoPerfil;
    @FXML
    Button iconoDatos;
    @FXML
    Button iconoEntrenamiento;
    @FXML
    Button iconoItinerario;
    @FXML
    Button iconoConfiguracion;
    @FXML
    Button iconoAyuda;
    @FXML
    Button iconoAcerca;
    @FXML
    ImageView ivBotonExit;
    @FXML
    ImageView ivBotonFullScreen;
    @FXML
    ImageView ivIconoDatos;
    @FXML
    ImageView ivIconoEntrenamiento;
    @FXML
    ImageView ivIconoItinerario;
    @FXML
    ImageView ivIconoPerfil;
    @FXML
    ImageView ivIconoConfiguracion;
    @FXML
    ImageView ivIconoAyuda;
    @FXML
    ContextMenu barMenu;
    @FXML
    TextField tfRendimiento;
    @FXML
    Label labelRendimiento;
    @FXML
    ToolBar toolBarR;
    @FXML
    Button botonModal;

    Methods m;
    Stage stage;
    SimpleDateFormat tituloConsulta = new SimpleDateFormat("dd/MM/yy hh:mm");
    DecimalFormat df = new DecimalFormat("0.00");
    boolean wConfigOpened = false;
    Escalada e;

    //contadores de pantallas
    int cEntrenamiento = 1;
    int cItinerario = 1;

    /*
     * INTIALIZE
     */
    public void builder(Stage stage, Methods m, Escalada e) {
        this.stage = stage;
        this.m = m;
        this.e = e;
        setDesktop();
        iconoPerfil.setText(m.getUserName());
        setToolBarProperty();
        botonModal.setVisible(false);
        translate();
        
        //calculaRendimiento();
    }

    private void translate() {
        iconoAyuda.setText(m.write("i_ayuda"));
        iconoDatos.setText(m.write("i_datos"));
        iconoEntrenamiento.setText(m.write("i_entrenamiento"));
        iconoItinerario.setText(m.write("i_itinerario"));
        iconoConfiguracion.setText(m.write("i_configuracion"));
        iconoAcerca.setText(m.write("i_acerca"));
        labelRendimiento.setText(m.write("l_rend"));
        botonExit.setTooltip(new Tooltip(m.write("tt_disconect")));
        botonFullScreen.setTooltip(new Tooltip(m.write("tt_fullscreen")));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setClock();
        setIcons();
    }
    
    private void calculaRendimiento(){
        tfRendimiento.setText(df.format(m.calculaRendimiento()));
    }
    
    /*
     * HANDLES
     */
    @FXML
    private void handleIconoDatos() {
        MyWindow w = new MyWindow();
        PantallaDatosController wConsulta = (PantallaDatosController) addWindow("PantallaDatos.fxml", m.write("consulta") + " " + tituloConsulta.format(new Date()), 625, 390, true, getClass().getResourceAsStream("resources/consulta.png"), w);
        wConsulta.builder(m);
    }

    @FXML
    private void handleIconoEntrenamiento() {
        MyWindow w = new MyWindow();
        PantallaEntrenamientoController wEntrenamiento = (PantallaEntrenamientoController) addWindow("PantallaEntrenamiento.fxml", m.write("alta_entrenamiento") + " " + cEntrenamiento++, 310, 265, false, getClass().getResourceAsStream("resources/entrenamiento.png"), w);
        wEntrenamiento.builder(m, w);
    }

    @FXML
    private void handleIconoItinerario() {
        MyWindow w = new MyWindow();
        PantallaItinerarioController wItinerario = (PantallaItinerarioController) addWindow("PantallaItinerario.fxml", m.write("alta_itinerario") + " " + cItinerario++, 370, 350, false, getClass().getResourceAsStream("resources/itinerario.png"), w);
        wItinerario.builder(m, w);
    }

    @FXML
    private void handleIconoPerfil() {
        System.out.println(m.getWallpaper());
    }

    @FXML
    private void handleIconoConfiguracion() {
        MyWindow w = new MyWindow();
        PantallaConfiguracionController wConfiguracion = (PantallaConfiguracionController) addWindow("PantallaConfiguracion.fxml", m.write("config"), 275, 260, false, getClass().getResourceAsStream("resources/settings.png"), w);
        wConfiguracion.builder(m, w, this, e);
    }

    @FXML
    private void handleIconoAyuda() {
        //SIN HACER

        //prueba de obtencion de entrenamientos
        ArrayList<Itinerario> e = m.getItinerarios();
        for (Itinerario i : e) {
            System.out.println(i.getA_escaladores() + " " + i.getP_itinerario());
        }
    }

    @FXML
    private void handleIconoAcerca() {
        MyWindow w = new MyWindow();
        PantallaAcercaController wAcerca = (PantallaAcercaController) addWindow("PantallaAcerca.fxml", m.write("acerca"), 285, 330, false, getClass().getResourceAsStream("resources/ayuda.png"), w);
        wAcerca.builder(m, w);
    }

    @FXML
    private void handleBotonFullScreen() {
        if (!stage.isFullScreen()) {
            stage.setFullScreen(true);
            ivBotonFullScreen.setImage(new Image(getClass().getResourceAsStream("resources/fullscreen0.png")));
        } else {
            ivBotonFullScreen.setImage(new Image(getClass().getResourceAsStream("resources/fullscreen.png")));
            stage.setFullScreen(false);
        }
    }

    @FXML
    private void handleBotonExit() {
        ModalListener ml = new ModalListener() {
            @Override
            public void onAction() {
                System.exit(0);
            }
        };
        throwModalWindow(m.write("exit"), m.write("exit_message"), ml);

    }

    public void throwModalWindow(String title, String mensaje, ModalListener mAceptar) {
        /*
         * Lanza una ventana modal pasandole el titulo, el mensaje y la funcion del boton aceptar
         */
        final Window w = new Window();
        PantallaModalController wModal = (PantallaModalController) addModalWindow(title, w);
        wModal.builder(m, mensaje, w, mAceptar);
    }

    /*
     * SETTINGS
     */
    private void setModality(boolean modality) {
        /*
         * desactiva las toolbar y tapa el escritorio
         */
        toolBar.setDisable(modality);
        toolBarR.setDisable(modality);
        botonModal.setVisible(modality);
        if (modality == true) {
            botonModal.toFront();
        }
    }

    private void setToolBarProperty() {
        /*
         * Agrega a la barra de tareas la opcion de cerrar todas las ventanas abiertas pulsando boton derecho sobre ella
         */
        MenuItem botonCerrarTodo = new MenuItem(m.write("close_all_windows"), new ImageView(new Image(getClass().getResourceAsStream("resources/barclose.png"))));
        botonCerrarTodo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                toolBar.getItems().remove(4, toolBar.getItems().size());//cierra todos los botones
                desktop.getChildren().remove(2, desktop.getChildren().size());//cierra todas las ventanas
            }
        });
        barMenu.getItems().add(botonCerrarTodo);
    }

    private void setClock() {
        /*
         * metodo para poner el marcha el reloj
         */
        final SimpleDateFormat fHora = new SimpleDateFormat("HH:mm");
        final Timeline timeline;
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //cambia la hora del reloj
                final Calendar cal = Calendar.getInstance();
                labelHora.setText(fHora.format(cal.getTime()));
                //comprueba el icono de fullscreen
                if (!stage.isFullScreen()) {
                    ivBotonFullScreen.setImage(new Image(getClass().getResourceAsStream("resources/fullscreen.png")));
                }
                calculaRendimiento();//se calcula el rendimiento cada segundo
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void setDesktop() {
        /*
         * Establece el fondo de pantalla ajustado a la ventana
         * Este metodo se lanza en el builder ya que necesita acceder a m(Methods)
         */
        try {
            Image image = new Image("file:" + m.getWallpaper());
            wallpaper.setImage(image);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            wallpaper.setFitWidth(screenSize.getWidth() + 1);
            wallpaper.setFitHeight(screenSize.getHeight());
            wallpaper.setPreserveRatio(false);
            wallpaper.setCache(false);
        } catch (Throwable e) {
            //Se captura el error por si no se reconoce la pantalla, algo asi paso en el mac de angel
        }

    }

    private void setIcons() {
        /*
         * carga la imagen de los iconos
         */
        ivBotonExit.setImage(new Image(getClass().getResourceAsStream("resources/off.png")));
        ivBotonFullScreen.setImage(new Image(getClass().getResourceAsStream("resources/fullscreen.png")));
        ivIconoConfiguracion.setImage(new Image(getClass().getResourceAsStream("resources/settings.png")));
        ivIconoDatos.setImage(new Image(getClass().getResourceAsStream("resources/consulta.png")));
        ivIconoEntrenamiento.setImage(new Image(getClass().getResourceAsStream("resources/entrenamiento.png")));
        ivIconoAyuda.setImage(new Image(getClass().getResourceAsStream("resources/ayuda.png")));
        ivIconoItinerario.setImage(new Image(getClass().getResourceAsStream("resources/itinerario.png")));
        ivIconoPerfil.setImage(new Image(getClass().getResourceAsStream("resources/perfil.png")));
    }

    /*
     * CARGAR VENTANAS
     */
    private Initializable addWindow(String fxml, String title, int width, int height, boolean resizable, InputStream img, final MyWindow w) {
        /*
         * Este metodo lanza una ventana al escritorio, hay que pasarle el archivo fxml, el titulo que llevara, el tamaño, el icono y la ventana
         * Funciona igual que el newSceneContent de la clase Escalada, pero para ventanas en el escritorio.
         */
        //Se carga el fxml
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
        Pane cmdPane = null;
        try {
            cmdPane = (Pane) fxmlLoader.load();
        } catch (IOException ex) {
        }
        //se suman los valores ya que el size se refiere a la totalidad de la ventana con bordes
        width = width + 4;
        height = height + 30;
        //se establece el contenido
        w.setContentPane(cmdPane);
        //Se definen las propiedades de la ventana
        w.setTitle(title);
        w.setPrefSize(width, height);
        w.setMinSize(width, height);
        //se coloca la ventana en el centro del escritorio
        w.setLayoutX((desktop.getWidth() / 2) - (width / 2));
        w.setLayoutY((desktop.getHeight() / 2) - (height / 2));
        //Si es resizable se agrega el boton de maximizar
        if (resizable) {
            w.setResizableWindow(resizable);
            w.getLeftIcons().add(new MaximizeIcon(w));

        } else {
            w.setResizableWindow(false);
        }
        w.getRightIcons().add(new MyMinimizeIcon(w));//boton que para minimizar hace desparecer la ventana      
        w.getRightIcons().add(new CloseIcon(w));//boton para cerrar

        //Se agrega al escritorio
        desktop.getChildren().add(w);
        //Se crea el boton de la barra de tareas
        final Button button = new Button(title, new ImageView(new Image(img, 15, 15, false, false)));
        toolBar.getItems().add(button);
        /*
         * el boton hara que la ventana se coloque debajo de el en la barra de tareas
         * sobre todas las ventanas (y se muestre si estaba minimizada)
         */
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (!w.isVisible()) {
                    w.setVisible(true);
                    w.setLayoutX(button.getLayoutX());
                    w.setLayoutY(0);
                    w.setMinimized(false);
                    w.toFront();
                } else {
                    w.setVisible(false);
                }
            }
        });
        button.setFocusTraversable(false);
        //Se crea un menu con la opcion de cerrar la ventana y el boton
        ContextMenu cm = new ContextMenu();
        MenuItem mi = new MenuItem(m.write("close_window"), new ImageView(new Image(getClass().getResourceAsStream("resources/barclose.png"))));
        mi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                toolBar.getItems().remove(button);
                w.close();
            }
        });
        cm.getItems().add(mi);
        //Se le añade el menu al boton derecho sobre el boton
        button.setContextMenu(cm);
        //Se indica el estilo del boton
        button.getStyleClass().add("taskbar");
        //Se hace que la ventana quite el boton de la barra de tareas al ser cerrada
        w.setOnClosedAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                toolBar.getItems().remove(button);
            }
        });
        return fxmlLoader.getController();
    }

    private Initializable addModalWindow(String title, Window w) {
        /*
         * Crea una ventana modal
         */
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PantallaModal.fxml"));
        Pane cmdPane = null;
        try {
            cmdPane = (Pane) fxmlLoader.load();
        } catch (IOException ex) {
        }
        //se suman los valores ya que el size se refiere a la totalidad de la ventana con bordes
        int width = 254;//+4
        int height = 155;//+30
        //se establece el contenido
        w.setContentPane(cmdPane);
        //Se definen las propiedades de la ventana
        w.setTitle(title);
        w.setPrefSize(width, height);
        //se coloca la ventana en el centro del escritorio
        w.setLayoutX((desktop.getWidth() / 2) - (width / 2));
        w.setLayoutY((desktop.getHeight() / 2) - (height / 2));
        w.setOnCloseAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                setModality(false);
            }
        });
        w.setResizableWindow(false);//no sera resizable
        //w.setMovable(false);//no sera movable
        w.getRightIcons().add(new CloseIcon(w));//boton para cerrar
        //Se agrega al escritorio
        desktop.getChildren().add(w);
        setModality(true);//hace la ventana modal
        w.toFront();//la mueve al frente
        return fxmlLoader.getController();
    }

}
