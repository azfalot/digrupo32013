package interfaz;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logica.Methods;

/**
 * FXML Controller class
 *
 * @author Manu
 */
public class PantallaLoginController implements Initializable {

    @FXML
    Button botonAceptar;
    @FXML
    ComboBox comboUsuario;
    @FXML
    ImageView imagenLogo;
    Methods m;

    public void builder(Methods m) {
        this.m = m;
        setImage();
    }

    private void setImage() {
        imagenLogo.setImage(new Image("file:resources" + File.separator + "escalada-sombria.png"));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

}
