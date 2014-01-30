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
;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import jfxtras.labs.scene.control.window.CloseIcon;
import jfxtras.labs.scene.control.window.MinimizeIcon;
import jfxtras.labs.scene.control.window.Window;

/**
 * FXML Controller class
 *
 * @author Grupo 3
 */


public class PantallaPrincipalController implements Initializable {

    @FXML
    Button button1;
    @FXML
    AnchorPane desktop;
    @FXML
    ImageView wallpaper;
    @FXML
    Label labelHora;
    
    
    @FXML
    ImageView ivIco1;

    Stage stage;

    @FXML
    private void handleButton1() {
        Window wAlta = new Window();
        addWindow("AltaEjercicio.fxml", "alta entrenamiento", 600, 400, wAlta);
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

    public void builder(Stage stage) {
        this.stage = stage;

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setDesktop();
        setIcons();
        

    }

    private void setDesktop() {
        Image image = new Image("file:resources" + File.separator + "wallpaper01.png");
        wallpaper.setImage(image);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        wallpaper.setFitWidth(screenSize.getWidth() + 1);
        wallpaper.setFitHeight(screenSize.getHeight());
        wallpaper.setPreserveRatio(false);
        wallpaper.setCache(false);
    }

    private void setIcons() {
        ivIco1.setImage(new Image("file:resources" + File.separator + "wrong.png"));

    }

    private Initializable addWindow(String fxml, String title, int height, int width, Window w) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
        Pane cmdPane = null;
        try {
            cmdPane = (Pane) fxmlLoader.load();
        } catch (IOException ex) {
        }
        w.setTitle(title);
        w.setPrefSize(height, width);
        w.setMinSize(height, width);
        w.setContentPane(cmdPane);
        w.getRightIcons().add(new MinimizeIcon(w));
        w.getRightIcons().add(new CloseIcon(w));
        desktop.getChildren().add(w);
        return fxmlLoader.getController();
    }

}
