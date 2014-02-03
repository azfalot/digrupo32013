/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import interfaz.util.MyMinimizeIcon;
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
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import jfxtras.labs.scene.control.window.CloseIcon;
import jfxtras.labs.scene.control.window.MinimizeIcon;
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
    ImageView ivBotonExit;
    @FXML
    ImageView ivBotonFullScreen;
    @FXML
    ImageView ivIconoConsulta;
    @FXML
    ImageView ivIconoEntrenamiento;
    @FXML
    ImageView ivIconoItinerario;
    @FXML
    ImageView ivIconoPerfil;
    @FXML
    ImageView ivIconoConfiguracion;
    @FXML
    ImageView ivIconoImprimir;

    Methods m;
    Stage stage;
    SimpleDateFormat tituloConsulta = new SimpleDateFormat("dd/MM/yy hh:mm");

    String ttNotFullscreen = "Pantalla completa";
    String ttFullscreen = "Salir del modo pantalla completa";

    /*
     * contadores de pantallas
     */
    int cEntrenamiento = 0;
    int cItinerario = 0;

    /*
     * Iconos, handles
     */
    @FXML
    private void handleIconoConsulta() {
        addWindow("PantallaConsulta.fxml", "Consulta " + tituloConsulta.format(new Date()), 600, 400, true, "resources" + File.separator + "icons" + File.separator + "consulta.png", new Window());
    }

    @FXML
    private void handleIconoEntrenamiento() {

    }

    @FXML
    private void handleIconoItinerario() {

    }

    @FXML
    private void handleIconoPerfil() {

    }

    @FXML
    private void handleIconoConfiguracion() {

    }

    @FXML
    private void handleIconoImprimir() {

    }

    @FXML
    private void handleBotonFullScreen() {
        if (!stage.isFullScreen()) {
            stage.setFullScreen(true);
            botonFullScreen.setTooltip(new Tooltip(ttFullscreen));
        } else {
            stage.setFullScreen(false);
            botonFullScreen.setTooltip(new Tooltip(ttNotFullscreen));
        }
    }

    @FXML
    private void handleBotonExit() {
        System.exit(0);
    }

    public void builder(Stage stage, Methods m) {
        this.stage = stage;
        this.m = m;
        setDesktop();
        iconoPerfil.setText(m.getUserName());
        setToolTips();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setClock();
        setIcons();
    }

    private void setToolTips() {
        botonExit.setTooltip(new Tooltip("Desconectar"));
        botonFullScreen.setTooltip(new Tooltip(ttNotFullscreen));
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
        ivBotonExit.setImage(new Image("file:resources" + File.separator + "icons" + File.separator + "off.png"));
        ivBotonFullScreen.setImage(new Image("file:resources" + File.separator + "icons" + File.separator + "fullscreen.png"));
        ivIconoConfiguracion.setImage(new Image("file:resources" + File.separator + "icons" + File.separator + "settings.png"));
        ivIconoConsulta.setImage(new Image("file:resources" + File.separator + "icons" + File.separator + "consulta.png"));
        ivIconoEntrenamiento.setImage(new Image("file:resources" + File.separator + "icons" + File.separator + "entrenamiento.png"));
        ivIconoImprimir.setImage(new Image("file:resources" + File.separator + "icons" + File.separator + "imprimir.png"));
        ivIconoItinerario.setImage(new Image("file:resources" + File.separator + "icons" + File.separator + "itinerario.png"));
        ivIconoPerfil.setImage(new Image("file:resources" + File.separator + "icons" + File.separator + "perfil.png"));
    }

    private Initializable addWindow(String fxml, String title, int height, int width, boolean resizable, String imgPath, final Window w) {
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
        //se establece el contenido
        w.setContentPane(cmdPane);
        //Se definen las propiedades de la ventana
        w.setTitle(title);
        w.setPrefSize(height + 4, width + 30);//se suman los valores ya que el size se refiere a la totalidad de la ventana con bordes
        if (resizable) {
            w.setResizableWindow(true);
            //w.getLeftIcons().add(new MaximizeIcon(w));//boton para maximizar
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
         * sobre todas las ventanas (y se maximize si estaba minimizada)
         */
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                w.setVisible(true);
                w.setLayoutX(button.getLayoutX());
                w.setLayoutY(0);
                w.setMinimized(false);
                w.toFront();
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
