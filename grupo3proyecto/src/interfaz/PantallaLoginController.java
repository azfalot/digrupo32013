package interfaz;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import logica.Methods;

/**
 * FXML Controller class
 *
 * @author Grupo 3
 */
public class PantallaLoginController implements Initializable {

    @FXML
    Button botonAceptar;
    @FXML
    Button botonCancelar;
    @FXML
    ComboBox comboUsuario;
    @FXML
    ImageView ivFondo;
    @FXML
    CheckBox checkRemember;
    @FXML
    Button botonNuevoUsuario;

    Methods m;
    Stage stage;
    Escalada app;
    
    
    public void builder(Escalada app, Methods m, Stage stage, int index) {
        this.app = app;
        this.m = m;
        this.stage = stage;
        fillComboBox(index);
        
        translate();
    }
    
    private void translate(){
        //textos
        botonAceptar.setText(m.write("acept"));
        botonCancelar.setText(m.write("cancel"));
        botonNuevoUsuario.setText(m.write("new_user"));
        checkRemember.setText(m.write("remember"));
        
        //tooltips
        botonNuevoUsuario.setTooltip(new Tooltip(m.write("tt_new_user")));
        checkRemember.setTooltip(new Tooltip(m.write("tt_remember")));
        comboUsuario.setTooltip(new Tooltip(m.write("tt_combo_user")));
    }

    private void setImages() {
        ivFondo.setImage(new Image("file:resources" + File.separator + "w_login.png"));
    }

    public void fillComboBox(int index) {
        //rellena el combobox
        comboUsuario.getItems().clear();
        comboUsuario.getItems().addAll(m.getNombreUsuarios());
        comboUsuario.getSelectionModel().select(index);
    }

    @FXML
    private void handleBotonAceptar() {
        m.setUser(m.getUserFromUsername(comboUsuario.getSelectionModel().getSelectedItem().toString()), checkRemember.isSelected());
        app.goToPantallaPrincipal();
        stage.close();
    }

    @FXML
    private void handleBotonCancelar() {
        stage.close();
    }

    @FXML
    private void handleBotonNuevoUsuario() {
        app.goToPantallaCrearUsuario(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setImages();
    }

}
