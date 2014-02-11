package interfaz;

import interfaz.util.MaximizeIcon;
import interfaz.util.MyMinimizeIcon;
import interfaz.util.MyWindow;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import jfxtras.labs.scene.control.window.CloseIcon;
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

    Methods m;
    Stage stage;
    SimpleDateFormat tituloConsulta = new SimpleDateFormat("dd/MM/yy hh:mm");

    //contadores de pantallas
    int cEntrenamiento = 1;
    int cItinerario = 1;

    /*
     * INTIALIZE
     */
    public void builder(Stage stage, Methods m) {
        this.stage = stage;
        this.m = m;
        setDesktop();
        iconoPerfil.setText(m.getUserName());
        setToolTips();
        setToolBarProperty();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setClock();
        setIcons();
    }

    /*
     * HANDLES
     */
    @FXML
    private void handleIconoDatos() {
        MyWindow w = new MyWindow();
        PantallaDatosController wConsulta = (PantallaDatosController) addWindow("PantallaDatos.fxml", "Consulta " + tituloConsulta.format(new Date()), 500, 350, true, "resources" + File.separator + "icons" + File.separator + "consulta.png", w);
        wConsulta.builder(m);
    }

    @FXML
    private void handleIconoEntrenamiento() {
        MyWindow w = new MyWindow();
        PantallaEntrenamientoController wEntrenamiento = (PantallaEntrenamientoController) addWindow("PantallaEntrenamiento.fxml", "Alta entrenamiento " + cEntrenamiento++, 310, 265, false, "resources" + File.separator + "icons" + File.separator + "entrenamiento.png", w);
        wEntrenamiento.builder(m, w);
    }

    @FXML
    private void handleIconoItinerario() {
        MyWindow w = new MyWindow();
        PantallaItinerarioController wItinerario = (PantallaItinerarioController) addWindow("PantallaItinerario.fxml", "Alta itinerario " + cItinerario++, 400, 350, false, "resources" + File.separator + "icons" + File.separator + "itinerario.png", w);
        wItinerario.builder(m, w);
    }

    @FXML
    private void handleIconoPerfil() {

    }

    @FXML
    private void handleIconoConfiguracion() {

    }

    @FXML
    private void handleIconoAyuda() {

    }

    @FXML
    private void handleBotonFullScreen() {
        if (!stage.isFullScreen()) {
            stage.setFullScreen(true);
            ivBotonFullScreen.setImage(new Image("file:resources" + File.separator + "icons" + File.separator + "fullscreen0.png"));
        } else {
            ivBotonFullScreen.setImage(new Image("file:resources" + File.separator + "icons" + File.separator + "fullscreen.png"));
            stage.setFullScreen(false);
        }
    }

    @FXML
    private void handleBotonExit() {
        System.exit(0);
    }

    /*
     * SETTINGS
     */
    private void setToolBarProperty() {
        /*
         * Agrega a la barra de tareas la opcion de cerrar todas las ventanas abiertas pulsando boton derecho sobre ella
         */
        MenuItem botonCerrarTodo = new MenuItem("Cerrar todas las ventanas", new ImageView(new Image("file:resources" + File.separator + "barclose.png")));
        botonCerrarTodo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                toolBar.getItems().remove(4, toolBar.getItems().size());//cierra todos los botones
                desktop.getChildren().remove(2, desktop.getChildren().size());//cierra todas las ventanas
            }
        });
        barMenu.getItems().add(botonCerrarTodo);
    }

    private void setToolTips() {
        /*
         * Se establecen los tooltips para todos los elementos
         */
        botonExit.setTooltip(new Tooltip("Desconectar"));
        botonFullScreen.setTooltip(new Tooltip("Modo pantalla completa"));
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
                final Calendar cal = Calendar.getInstance();
                labelHora.setText(fHora.format(cal.getTime()));
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void setDesktop() {
        /*
         * Establece el fondo de pantalla ajustado a la ventana
         * Este metodo se lanza en el builder ya que necesita acceder a m(Methods)
         */
        Image image = new Image("file:" + m.getWallpaper());
        wallpaper.setImage(image);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        wallpaper.setFitWidth(screenSize.getWidth() + 1);
        wallpaper.setFitHeight(screenSize.getHeight());
        wallpaper.setPreserveRatio(false);
        wallpaper.setCache(false);
    }

    private void setIcons() {
        /*
         * carga la imagen de los iconos
         */
        //getClass().getClassLoader().getResource("interfaz/util/off.png"); //obtener la imagen de un paquete
        ivBotonExit.setImage(new Image("file:resources" + File.separator + "icons" + File.separator + "off.png"));
        ivBotonFullScreen.setImage(new Image("file:resources" + File.separator + "icons" + File.separator + "fullscreen.png"));
        ivIconoConfiguracion.setImage(new Image("file:resources" + File.separator + "icons" + File.separator + "settings.png"));
        ivIconoDatos.setImage(new Image("file:resources" + File.separator + "icons" + File.separator + "consulta.png"));
        ivIconoEntrenamiento.setImage(new Image("file:resources" + File.separator + "icons" + File.separator + "entrenamiento.png"));
        ivIconoAyuda.setImage(new Image("file:resources" + File.separator + "icons" + File.separator + "ayuda.png"));
        ivIconoItinerario.setImage(new Image("file:resources" + File.separator + "icons" + File.separator + "itinerario.png"));
        ivIconoPerfil.setImage(new Image("file:resources" + File.separator + "icons" + File.separator + "perfil.png"));
    }

    /*
     * CARGAR VENTANAS
     */
    private Initializable addWindow(String fxml, String title, int width, int height, boolean resizable, String imgPath, final MyWindow w) {
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
        //w.getRightIcons().add(new MinimizeIcon(w));//boton que para minimizar deja la barra superior de la ventana
        w.getRightIcons().add(new MyMinimizeIcon(w));//boton que para minimizar hace desparecer la ventana      
        w.getRightIcons().add(new CloseIcon(w));//boton para cerrar

        //Se agrega al escritorio
        desktop.getChildren().add(w);
        //Se crea el boton de la barra de tareas
        final Button button = new Button(title, new ImageView(new Image("file:" + imgPath, 15, 15, false, false)));
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
        MenuItem mi = new MenuItem("Cerrar ventana", new ImageView(new Image("file:resources" + File.separator + "barclose.png")));
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

}
