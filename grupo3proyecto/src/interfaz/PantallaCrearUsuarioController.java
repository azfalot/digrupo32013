/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logica.Methods;

/**
 * FXML Controller class
 *
 * @author Grupo 3
 */
public class PantallaCrearUsuarioController implements Initializable {

    @FXML
    TextField tfNombre;
    @FXML
    ImageView ivError;
    @FXML
    Label labelError;
    @FXML
    Label labelBienvenido;
    @FXML
    Label labelInstruccion;
    @FXML
    Button botonAceptar;
    @FXML
    Button botonCancelar;
    @FXML
    ImageView ivFondo;

    Methods m;
    Escalada app;

    public void builder(Escalada app, Methods m,boolean noUsers) {
        this.app = app;
        this.m = m;       
        tfNombre.setPromptText(m.write("set_name"));

        if(noUsers){
            labelBienvenido.setText(m.write("welcome")+" "+app.getAppName());
            labelInstruccion.setText(m.write("inst1")+"\n"+m.write("inst2"));
            botonCancelar.setVisible(false);
            labelBienvenido.setVisible(true);
            labelInstruccion.setVisible(true);
        }
        translate();
    }
    
    private void translate(){
        botonAceptar.setText(m.write("acept"));
        botonCancelar.setText(m.write("cancel"));
        tfNombre.setTooltip(new Tooltip(m.write("tt_tfNombre")));
    }
    
    private void setImages() {
        ivFondo.setImage(new Image("file:resources" + File.separator + "w_login.png"));
        ivError.setImage(new Image("file:resources" + File.separator + "wrong.png"));
    }

    @FXML
    private void handleBotonAceptar() {
        showError(false);
        if ("".equals(tfNombre.getText())) {
            showError(true);
            labelError.setText(m.write("set_name"));
        } else {
            int index = m.altaUsuario(tfNombre.getText());
            if (index == 0) {
                showError(true);
                labelError.setText(m.write("err_user_exists"));
            } else {
                app.goToPantallaLogin(index - 1);
            }
        }
    }

    private void showError(boolean b) {
        ivError.setVisible(b);
        labelError.setVisible(b);
    }

    @FXML
    private void handleBotonCancelar() {
        app.goToPantallaLogin(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setImages();
        showError(false);
        labelBienvenido.setVisible(false);
        labelInstruccion.setVisible(false); 
        
        /*
        * Limitamos el texto del TextField a 30
        */
        tfNombre.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (t1.length() > 30) {
                    tfNombre.setText(t);
                }
            }
        });
    }

}
