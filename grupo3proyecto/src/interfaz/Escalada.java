package interfaz;

import java.io.File;
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
public class Escalada extends Application {
    /*
     * Clase principal de la aplicacion
     */

    private Stage mainStage;
    private Methods m;
    private final String applicationName = "Escalator 3000"; //nombre de la aplicacion
    private PantallaLoginController wLogin;//ventana de login, sera necesaria para que sea actualizada tras dar de alta un usuario
    private PantallaPrincipalController wPrincipal;
    
    @Override
    public void start(Stage stage) throws Exception {
        this.mainStage = stage;
        m = Methods.getInstance();
        //goToMainStage();
        if(!m.getRemember()){
            goToLoginStage();
        }
    }

    public void goToLoginStage() {
        Stage secondaryStage=new Stage();
        wLogin = (PantallaLoginController) newSceneContent("PantallaLogin.fxml", m.write("login"), false, true, secondaryStage);
        wLogin.builder(this,m,mainStage,secondaryStage);
    }
    
    public void goToPantallaPrincipal(){
         wPrincipal = (PantallaPrincipalController) newSceneContent("PantallaPrincipal.fxml", "Principal", true, false, mainStage);
         
    }
    
    public void goToAltaUsuario(){
        
    }
    
    public void goToAltaEjercicio(){
        
    }
    
    public static void main(String args[]) {
        /*
         * Metodo main de la aplicacion
         */
        launch(args);
    }

    public void setTitle(String titulo) {
        mainStage.setTitle(titulo);
    }

    private Initializable newSceneContent(String fxml, String title, boolean resizable, boolean modal, Stage stage) {
        /*
        * Cambia un stage recibiendo una serie de parametros, el xml, el titulo, si se puede redimensionar,
        * si es modal y el stage que va a cambiar.
        */
        FXMLLoader loader = new FXMLLoader();
        URL url = getClass().getResource(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(url);
        try {
            Parent page = (Parent) loader.load();
            Scene scene = new Scene(page);
            stage.setScene(scene);
            if ("".equals(title)) {
                stage.setTitle(applicationName);
            } else {
                stage.setTitle(applicationName + " | " + title);
            }
            stage.getIcons().add(new Image("file:resources" + File.separator + "icon.png"));
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
