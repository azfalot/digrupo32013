package interfaz;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    TextField tfNombre;
    @FXML
    Button botonAceptar;
    @FXML
    Button botonAdd;
    @FXML
    Button botonCancelar;
    @FXML
    ComboBox comboUsuario;
    @FXML
    ImageView imagenLogo;
    @FXML
    ImageView ivError;
    @FXML
    CheckBox checkRemember;
    @FXML
    Label labelNombre;
    Methods m;
    Stage stage;
    Stage mainStage;
    Escalada app;
    String toolTipError1, toolTipError2, labelNombreTooltip;

    public void builder(Escalada app, Methods m, Stage mainStage, Stage stage) {
        this.mainStage = mainStage;
        this.app = app;
        this.m = m;
        this.stage = stage;
        setImages();
        translate();
        fillComboBox();
    }

    private void setImages() {
        imagenLogo.setImage(new Image("file:resources" + File.separator + "login_image.png"));
        ivError.setImage(new Image("file:resources" + File.separator + "wrong.png"));
        ivError.setVisible(false);
    }

    public void fillComboBox() {
        //rellena el combobox
        comboUsuario.getItems().clear();
        comboUsuario.getItems().addAll(m.getNombreUsuarios());
        comboUsuario.getSelectionModel().select(0);
    }

    @FXML
    private void handleAceptar() {;
        m.setUser(m.getUserFromUsername(comboUsuario.getSelectionModel().getSelectedItem().toString()), checkRemember.isSelected());
        stage.close();
    }

    @FXML
    private void handleAdd() {
        //reseteamos todo
        tfNombre.setTooltip(new Tooltip(labelNombreTooltip));
        ivError.setVisible(false);
        if ("".equals(tfNombre.getText())) {
            //si el texto esta vacio lanza error
            ivError.setVisible(true);
            labelNombre.setTooltip(new Tooltip(toolTipError2));
        } else {
            int id = m.altaUsuario(tfNombre.getText());
            if (id == 0) { 
                //si el nombre ya esta cogido lanza error
                ivError.setVisible(true);
                tfNombre.setTooltip(new Tooltip(toolTipError1));
            }else{
                //si es todo correcto refresca el combobox y vacia el textfield
                fillComboBox();
                tfNombre.setText("");
                comboUsuario.getSelectionModel().select(id-1);//deja seleccionado el nuevo usuario
            }
        }
    }

    @FXML
    private void handleCancelar() {
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void translate() {
        botonAceptar.setText(m.write("acept"));
        botonCancelar.setText(m.write("cancel"));
        checkRemember.setText(m.write("remember"));
        toolTipError1 = m.write("name_error_repeated");
        toolTipError2 = m.write("name_error_space");
        labelNombre.setText(m.write("name")+":");
        botonAdd.setText(m.write("new"));
        botonAdd.setTooltip(new Tooltip(m.write("tt_botonAdd")));
        tfNombre.setTooltip(new Tooltip(m.write("tt_tfNombre")));
        checkRemember.setTooltip(new Tooltip(m.write("tt_checkRemember")));
        comboUsuario.setTooltip(new Tooltip(m.write("tt_comboUsuario")));
        
    }

}
