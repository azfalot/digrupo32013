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
    private Stage loginStage;
    private Methods m;
    static final String applicationName = "Escalator 3000"; //nombre de la aplicacion
    private PantallaLoginController wLogin;//ventana de login, sera necesaria para que sea actualizada tras dar de alta un usuario
    private PantallaPrincipalController wPrincipal;

    @Override
    public void start(Stage stage) throws Exception {
        this.mainStage = stage;
        loginStage = new Stage();
        m = Methods.getInstance();
        if (m.getUserId() == 0) {
            /*
             * la funcion del siguiente if es determinar si hay usuarios en la base de datos,
             * en caso negativo va obligatoriamente a crear uno nuevo
             */
            if (m.getNombreUsuarios().isEmpty()) {
                goToPantallaCrearUsuario(false);
                goToPantallaCrearUsuario(true);
            } else {
                /*
                 * AQUI HAY UN ERROR QUE SALE LA IMAGEN MAL AL PRINCIPIO SI MUESTRO DIRECTAMENTE LA PANTALLA DE LOGIN
                 * LA UNICA FORMA DE ARREGRARLO QUE HE ENCONTRADO DE MOMENTO ES MOSTRAR ANTES LA PANTALLA DE NUEVO USUARIO
                 * Y JUSTO DESPUES PASAR A LA DE LOGIN
                 */
                goToPantallaCrearUsuario(false);//COMENTAR ESTA LINEA PARA VER EL ERROR EN LA IMAGEN
                goToPantallaLogin(0);
            }
        } else {
            goToPantallaPrincipal();
        }
    }

    public void goToPantallaLogin(int index) {
        /*
         * el parametro index es el indice de usuario que va a colocarse en el combo box por defecto
         */
        wLogin = (PantallaLoginController) newSceneContent("PantallaLogin.fxml", m.write("login"), false, false, loginStage);
        wLogin.builder(this, m, loginStage, index);
    }

    public void goToPantallaCrearUsuario(boolean noUsers) {
        /*
         * el parametro noUsers sera true si no hay usuarios en la base de datos
         */
        PantallaCrearUsuarioController wCrear = (PantallaCrearUsuarioController) newSceneContent("PantallaCrearUsuario.fxml", m.write("login") + " > " + m.write("create_user"), false, false, loginStage);
        wCrear.builder(this, m, noUsers);
    }

    public void goToPantallaPrincipal() {
        wPrincipal = (PantallaPrincipalController) newSceneContent("PantallaPrincipal.fxml", m.getUserName(), true, false, mainStage);
        wPrincipal.builder(mainStage, m,this);
        mainStage.setFullScreen(false);

    }

    public String getAppName() {
        return applicationName;
    }

    public static void main(String args[]) {
        /*
         * Metodo main de la aplicacion
         */
        launch(args);
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
            stage.getIcons().add(new Image(getClass().getResourceAsStream("resources/icon.png")));
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
