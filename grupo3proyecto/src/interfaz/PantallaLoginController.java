package interfaz;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
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
    Button botonAdd;
    @FXML
    Button botonCancelar;
    @FXML
    ComboBox comboUsuario;
    @FXML
    ImageView imagenLogo;
    @FXML
    CheckBox checkRemember;
    Methods m;
    String newUser;
    Stage stage;
    Stage mainStage;
    Escalada app;
    
    public void builder(Escalada app,Methods m,Stage mainStage,Stage stage) {
        this.mainStage=mainStage;
        this.app=app;
        this.m = m;
        this.stage=stage;
        setImage();
        if(m.getRemember()){
            checkRemember.setSelected(true);
        }
        translate();
        fillComboBox();
    }

    private void setImage() {
        imagenLogo.setImage(new Image("file:resources" + File.separator + "login_image.png"));
    }
    
    public void fillComboBox(){
        //rellena el combobox
        comboUsuario.getItems().clear();
        comboUsuario.getItems().addAll(m.getNombreUsuarios());
        comboUsuario.getSelectionModel().select(0);
    }
    
    
    @FXML
    private void handleAceptar(){;
        boolean r;
        if(checkRemember.isSelected()){
            r=true;
        }else{
            r=false;
        }        
        m.setUser(m.getUserFromUsername(comboUsuario.getSelectionModel().getSelectedItem().toString()),r);
        stage.close();
    }
    
    @FXML
    private void handleAdd(){
        //app.goToNewUserStage();
    }
    
    @FXML
    private void handleCancelar(){
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
    }

    public void translate() {
        botonAceptar.setText(m.write("acept"));
        botonCancelar.setText(m.write("cancel"));
        checkRemember.setText(m.write("remember"));
        newUser=m.write("new_user");
    }
    
    

}
