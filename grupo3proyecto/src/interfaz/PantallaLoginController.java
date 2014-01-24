
package interfaz;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Manu
 */
public class PantallaLoginController implements Initializable {

    @FXML Button botonAceptar;
    @FXML ComboBox comboUsuario;
    @FXML ImageView imagenLogo;
    
    @FXML public void setImage(){
        String ruta=null;
            File fichero=new File(".");
        try {
            ruta=fichero.getCanonicalPath()+File.separator+"resources"+File.separator+"escalada-sombria.png";
        } catch (IOException ex) {
            ex.printStackTrace();
        }
            Image img=new Image(ruta);
        //muestro en el imageview la foto seleccionada
            imagenLogo.setImage(img);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
