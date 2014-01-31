/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    ImageView ivIco1;
    @FXML
    Button button1;
    @FXML
    ImageView ivIco2;
    @FXML
    Button button2;

    Methods m;
    Stage stage;

    /*
     * contadores de pantallas
     */
    int cEntrenamiento = 0;

    @FXML
    private void handleButton1() {
        //Window wAlta = new Window();
        //addWindow("AltaEjercicio.fxml", "alta entrenamiento", 600, 400, wAlta);
    }

    @FXML
    private void handleButton2() {
        final Window w = new Window();
        addWindow("AltaEjercicio.fxml", "alta entrenamiento " + (++cEntrenamiento), 600, 600, false, "resources" + File.separator + "tick.png", w);

    }

    @FXML
    private void handleBotonFullScreen() {
        if (!stage.isFullScreen()) {
            stage.setFullScreen(true);
        } else {
            stage.setFullScreen(false);
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
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setClock();
        setIcons();
        setButtonsProperties();
        

    }

    private void setClock() {
        /*
         * metodo para poner el marcha el reloj
         */
        final SimpleDateFormat fHora=new SimpleDateFormat("HH:mm");
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

    private void setButtonsProperties() {
        /*
         * este metodo hace que no se pueda tener el focus sobre ciertos botones, como los iconos
         */
        botonExit.setFocusTraversable(false);
        botonFullScreen.setFocusTraversable(false);
        button1.setFocusTraversable(false);
        button2.setFocusTraversable(false);
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
        ivIco1.setImage(new Image("file:resources" + File.separator + "wrong.png"));
        ivIco2.setImage(new Image("file:resources" + File.separator + "tick.png"));

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
        //Se definen las propiedades de la ventana
        w.setTitle(title);
        w.setPrefSize(height, width);
        if (!resizable) {
            w.setMinSize(height, width);
            w.setMaxSize(height, width);
        }
        w.setContentPane(cmdPane);
        w.getRightIcons().add(new MinimizeIcon(w));
        w.getRightIcons().add(new CloseIcon(w));
        //Se agrega al escritorio
        desktop.getChildren().add(w);
        //Se crea el boton de la barra de tareas
        final Button button = new Button(title, new ImageView(new Image("file:" + imgPath, 15, 15, false, false)));
        toolBar.getItems().add(button);
        //Con la propiedad de mover la ventana debajo de el
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                w.setLayoutX(button.getLayoutX());
                w.setLayoutY(0);
            }
        });
        button.setFocusTraversable(false);
        //Se crea un menu con la opcion de cerrar la ventana y el boton
        ContextMenu cm = new ContextMenu();
        MenuItem mi = new MenuItem("Cerrar", new ImageView(new Image("file:resources" + File.separator + "barclose.png")));
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
