/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaz;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import logica.Methods;

/**
 * FXML Controller class
 *
 * @author Grupo 3
 */
public class PantallaDatosController implements Initializable {
    
    @FXML
    TabPane tabPane;   
    
          
    
    Methods m;
    
    /*
    * INITIALIZE
    */
    public void builder(Methods m){
        this.m=m;
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setPaneSizes();
        
    }
    
    private void setPaneSizes(){
        tabPane.setTabMinHeight(22);
        tabPane.setTabMaxHeight(22);
    }
    
    /*
    * HANDLES
    */
    
    /*
    * TABLA
    */
    public void refreshTabla() {
//        formatTabla();
//        for (Obra obra : metodos.getObras()) {
//            tabla.getItems().add(obra);
//        }
    }
    
    private void formatTabla() {
//        //borra la tabla (columnas y datos)
//        tabla.getColumns().clear();
//        tabla.getItems().clear();
//
//        //crea las nuevas columnas
//        TableColumn tituloCol = new TableColumn("Titulo");
//        tituloCol.setCellValueFactory(new PropertyValueFactory<Obra, String>("titulo"));
//        
//
//        //define la tabla como no editable
//        tabla.setEditable(false);
//        //añade las columnas creadas
//        tabla.getColumns().addAll(tituloCol, autorCol, fechaCol, localizacionCol, valoracionCol, tecnicaCol);
//        //hace que las columnas ocupen todo el espacio reservado para la tabla
//        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
    
    
}
