
package interfaz;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logica.Methods;

/**
 *
 * @author Grupo 3
 */

public class Escalada extends Application{
    /*
    * Clase principal de la aplicacion
    */
    
    private Stage stage;
    private Methods m;
    private String applicationName=""; //nombre de la aplicacion
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage=stage;
        m=Methods.getInstance();
        //goToLoginStage();
    }
    
    public static void main(String args[]) {
        /*
        * Metodo main de la aplicacion
        */
        launch(args);
    }

    public void setTitle(String titulo) {
        stage.setTitle(titulo);
    }
    
    private Initializable newSceneContent(String fxml, String title, boolean resizable, boolean modal, Stage stage) {
        FXMLLoader loader = new FXMLLoader();
        URL url = getClass().getResource(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(url);
        try {
            Parent page = (Parent) loader.load();
            Scene scene = new Scene(page);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.getIcons().add(new Image("file:resources\\icon.png"));
            stage.setResizable(resizable);
            if (modal) {
                stage.initModality(Modality.APPLICATION_MODAL);
            }
            stage.show();
            return (Initializable) loader.getController();
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
