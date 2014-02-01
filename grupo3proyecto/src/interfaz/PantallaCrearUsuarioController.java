/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
        tfNombre.setPromptText("Introduce nombre");

        if(noUsers){
            labelBienvenido.setText("Bienvenido a "+app.getAppName());
            labelInstruccion.setText("Debe crear un usuario\npara continuar:");
            botonCancelar.setVisible(false);
            labelBienvenido.setVisible(true);
            labelInstruccion.setVisible(true);
        }
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
            labelError.setText("Introduzca nombre");
        } else {
            int index = m.altaUsuario(tfNombre.getText());
            if (index == 0) {
                showError(true);
                labelError.setText("El usuario ya existe");
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
    }

}
